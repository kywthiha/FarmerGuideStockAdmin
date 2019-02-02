package farm.com.farmerguidestockadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.admin.stockadminfarmapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import farm.com.farmerguidestockadmin.security.MD5Helper;
import farm.com.farmerguidestockadmin.sharepref.SharePrefHelper;
import farm.com.farmerguidestockadmin.statics.RefStatic;
import farm.com.farmerguidestockadmin.utils.UiUtil;

public class EnterPassword extends AppCompatActivity {
    private static String pass = "";
    EditText editPass;
    MD5Helper md5Helper = new MD5Helper();
    DatabaseReference reference = RefStatic.stockPass;
    SharePrefHelper sharePrefHelper = null;

    /* renamed from: com.admin.stockadminfarmapp.EnterPassword$1 */
    class PassListener implements ValueEventListener {
        PassListener() {
        }

        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            EnterPassword.pass = (String) dataSnapshot.child("pass").getValue();
        }

        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.sign_in);
        this.sharePrefHelper = new SharePrefHelper(getApplicationContext());
        this.editPass = (EditText) findViewById(R.id.edt_password);
        this.reference.addListenerForSingleValueEvent(new PassListener());
        final TextInputLayout floatingUsernameLabel = (TextInputLayout) findViewById(R.id.username_text_input_layout);
        floatingUsernameLabel.getEditText().addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() <= 0 || text.length() > 4) {
                    floatingUsernameLabel.setErrorEnabled(false);
                    return;
                }
                floatingUsernameLabel.setError("password required");
                floatingUsernameLabel.setErrorEnabled(true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void onOkClick(View view) {
        if (this.editPass.getText().toString().equals("")) {
            UiUtil.showToast(getApplicationContext(), "Invalid password");
        } else if (this.md5Helper.decryptMD(this.editPass.getText().toString()).equals(pass)) {
            UiUtil.showToast(getApplicationContext(), "Logged in as admin");
            this.sharePrefHelper.setPassword(this.editPass.getText().toString());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            UiUtil.showToast(getApplicationContext(), "Invalid password");
        }
    }
}
