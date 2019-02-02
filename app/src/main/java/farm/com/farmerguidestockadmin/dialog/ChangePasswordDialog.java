package farm.com.farmerguidestockadmin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.admin.stockadminfarmapp.R;


public class ChangePasswordDialog extends Dialog implements OnClickListener {
    private Context context;
    /* renamed from: d */
    private Dialog f27d;
    private LinearLayout no;
    private farm.com.farmerguidestockadmin.dialog.ChangePasswordDialog.OnDialogResult onDialogResult;
    private String password;
    private TextView txtCropName;
    private LinearLayout yes;

    public interface OnDialogResult {
        void finish();
    }

    public ChangePasswordDialog(Context context, String pass, farm.com.farmerguidestockadmin.dialog.ChangePasswordDialog.OnDialogResult result) {
        super(context);
        this.context = context;
        this.onDialogResult = result;
        this.password = pass;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.change_password_dialog);
        this.yes = (LinearLayout) findViewById(R.id.edt_delete_crop_delete);
        this.no = (LinearLayout) findViewById(R.id.edt_delete_crop_cancel);
        this.txtCropName = (TextView) findViewById(R.id.txt_ask_question);
        TextView textView = this.txtCropName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Change  Stock Admin Password to ");
        stringBuilder.append(this.password);
        textView.setText(stringBuilder.toString());
        this.yes.setOnClickListener(this);
        this.no.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == this.no) {
            cancel();
        }
        if (v == this.yes) {
            this.onDialogResult.finish();
            cancel();
        }
    }
}
