package farm.com.farmerguidestockadmin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.admin.stockadminfarmapp.R;


public class AddCropDialog extends Dialog implements OnClickListener {
    private Context context;
    private String crop;
    /* renamed from: d */
    private Dialog f26d;
    private EditText edCrop;
    private LinearLayout no;
    private OnDialogResult onDialogResult;
    private LinearLayout yes;

    public interface OnDialogResult {
        void finish(String str);
    }

    public AddCropDialog(Context context, OnDialogResult result) {
        super(context);
        this.context = context;
        this.onDialogResult = result;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.new_crop_dialog);
        this.yes = (LinearLayout) findViewById(R.id.add_crop_add);
        this.no = (LinearLayout) findViewById(R.id.add_crop_cancel);
        this.edCrop = (EditText) findViewById(R.id.edt_crop);
        this.yes.setOnClickListener(this);
        this.no.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == this.no) {
            cancel();
        }
        if (v == this.yes) {
            this.crop = this.edCrop.getText().toString();
            this.onDialogResult.finish(this.crop);
            cancel();
        }
    }
}
