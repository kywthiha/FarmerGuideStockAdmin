package farm.com.farmerguidestockadmin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.admin.stockadminfarmapp.R;

import farm.com.farmerguidestockadmin.model.Crop;


public class EditCropDialog extends Dialog implements OnClickListener {
    private Crop cc;
    private Context context;
    private String crop;
    /* renamed from: d */
    private Dialog f29d;
    private EditText edCrop;
    private LinearLayout no;
    private farm.com.farmerguidestockadmin.dialog.EditCropDialog.OnDialogResult onDialogResult;
    private TextView txtCropName;
    private LinearLayout yes;

    public interface OnDialogResult {
        void finish(String str);
    }

    public EditCropDialog(Context context, Crop crop, farm.com.farmerguidestockadmin.dialog.EditCropDialog.OnDialogResult result) {
        super(context);
        this.context = context;
        this.onDialogResult = result;
        this.cc = crop;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.edit_crop_dialog);
        this.yes = (LinearLayout) findViewById(R.id.edt_edit_crop_save);
        this.no = (LinearLayout) findViewById(R.id.edt_edit_crop_cancel);
        this.edCrop = (EditText) findViewById(R.id.edt_crop_price);
        this.txtCropName = (TextView) findViewById(R.id.txt_edt_crop_name);
        this.txtCropName.setText(this.cc.getC_name());
        this.edCrop.setText(this.cc.getC_price());
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
