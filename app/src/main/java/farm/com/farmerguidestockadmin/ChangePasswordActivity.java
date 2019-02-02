package farm.com.farmerguidestockadmin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.admin.stockadminfarmapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import farm.com.farmerguidestockadmin.dialog.ChangePasswordDialog;
import farm.com.farmerguidestockadmin.dialog.DeleteCropDialog;
import farm.com.farmerguidestockadmin.dialog.StockDialog;
import farm.com.farmerguidestockadmin.security.MD5Helper;
import farm.com.farmerguidestockadmin.sharepref.SharePrefHelper;
import farm.com.farmerguidestockadmin.statics.RefStatic;
import farm.com.farmerguidestockadmin.utils.UiUtil;

public class ChangePasswordActivity extends AppCompatActivity {
    public static String confirm;
    public static String newpassword;
    public static String oldpassword;
    public static String refpassword;
    EditText confirmPass;
    MD5Helper helper = new MD5Helper();
    EditText newPass;
    EditText oldPass;
    DatabaseReference reference = RefStatic.stockPass;
    SharePrefHelper sharePrefHelper = null;

    class PassListener implements ValueEventListener {
        PassListener() {
        }

        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            ChangePasswordActivity.refpassword = (String) dataSnapshot.child("pass").getValue();
        }

        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_change_password);
        this.sharePrefHelper = new SharePrefHelper(getApplicationContext());
        this.oldPass = (EditText) findViewById(R.id.edt_old_password);
        this.newPass = (EditText) findViewById(R.id.edt_new_password);
        this.confirmPass = (EditText) findViewById(R.id.edt_confirm_password);
        this.reference.addListenerForSingleValueEvent(new PassListener());
    }

    public void onChangePassword(View view) {
        newpassword = this.newPass.getText().toString();
        oldpassword = this.oldPass.getText().toString();
        confirm = this.confirmPass.getText().toString();
        if (!(newpassword.equals("") || oldpassword.equals(""))) {
            if (!confirm.equals("")) {
                newpassword = this.helper.decryptMD(this.newPass.getText().toString());
                oldpassword = this.helper.decryptMD(this.oldPass.getText().toString());
                confirm = this.helper.decryptMD(this.confirmPass.getText().toString());
                if (!oldpassword.equals(refpassword)) {
                    UiUtil.showToast(getApplicationContext(), "Failed,Incorrect old password");
                    return;
                } else if (newpassword.equals(confirm)) {
                    ChangePasswordDialog changePasswordDialog=new ChangePasswordDialog(this, newpassword, new ChangePasswordDialog.OnDialogResult() {
                        @Override
                        public void finish() {
                            ChangePasswordActivity.this.reference.child("pass").setValue(ChangePasswordActivity.newpassword);
                            ChangePasswordActivity.this.sharePrefHelper.setPassword(ChangePasswordActivity.this.newPass.getText().toString());
                            UiUtil.showToast(ChangePasswordActivity.this.getApplicationContext(), "Success,changed password");
                            ChangePasswordActivity.this.newPass.setText("");
                            ChangePasswordActivity.this.confirmPass.setText("");
                            ChangePasswordActivity.this.oldPass.setText("");
                        }
                    });
                    changePasswordDialog.show();
                    return;
                } else {
                    UiUtil.showToast(getApplicationContext(), "Please confirm new password");
                    return;
                }
            }
        }
        UiUtil.showToast(getApplicationContext(), "Please Enter passwords correctyl");
    }
}
