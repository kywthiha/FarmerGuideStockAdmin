package farm.com.farmerguidestockadmin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.admin.stockadminfarmapp.R;


public class LogOutDialog extends Dialog implements OnClickListener {
    private Context context;
    /* renamed from: d */
    private Dialog f30d;
    private LinearLayout no;
    private farm.com.farmerguidestockadmin.dialog.LogOutDialog.OnDialogResult onDialogResult;
    private TextView txtCropName;
    private LinearLayout yes;

    public interface OnDialogResult {
        void finish();
    }

    public LogOutDialog(Context context, farm.com.farmerguidestockadmin.dialog.LogOutDialog.OnDialogResult result) {
        super(context);
        this.context = context;
        this.onDialogResult = result;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.log_out_dialog);
        this.yes = (LinearLayout) findViewById(R.id.edt_delete_crop_delete);
        this.no = (LinearLayout) findViewById(R.id.edt_delete_crop_cancel);
        this.txtCropName = (TextView) findViewById(R.id.txt_ask_question);
        this.txtCropName.setText("Are you sure you want to Log Out?");
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
