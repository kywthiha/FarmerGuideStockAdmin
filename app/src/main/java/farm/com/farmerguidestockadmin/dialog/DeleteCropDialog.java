package farm.com.farmerguidestockadmin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.admin.stockadminfarmapp.R;
import farm.com.farmerguidestockadmin.model.Crop;


public class DeleteCropDialog extends Dialog implements OnClickListener {
    private Crop cc;
    private Context context;
    private String crop;
    /* renamed from: d */
    private LinearLayout no;
    private OnDialogResult onDialogResult;
    private TextView txtCropName;
    private LinearLayout yes;

    public interface OnDialogResult {
        void finish();
    }

    public DeleteCropDialog(Context context, Crop crop, OnDialogResult result) {
        super(context);
        this.context = context;
        this.onDialogResult = result;
        this.cc = crop;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.admin.stockadminfarmapp.R.layout.delete_crop_dialog);
        this.yes = (LinearLayout) findViewById(R.id.edt_delete_crop_delete);
        this.no = (LinearLayout) findViewById(R.id.edt_delete_crop_cancel);
        this.txtCropName = (TextView) findViewById(R.id.txt_ask_question);
        TextView textView = this.txtCropName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Are you sure you want to delete ");
        stringBuilder.append(this.cc.getC_name());
        stringBuilder.append(" from stock list?");
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
