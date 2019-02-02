package farm.com.farmerguidestockadmin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.admin.stockadminfarmapp.R;


public class StockDialog extends Dialog implements OnClickListener {
    private int bgCancelColor;
    private int bgOkColor;
    private Context context;
    /* renamed from: d */
    private Dialog f31d;
    private RelativeLayout dialog;
    private int dialogBackgroundColor;
    private LinearLayout no;
    private farm.com.farmerguidestockadmin.dialog.StockDialog.OnDialogResult onDialogResult;
    private int txtCancelColor;
    private TextView txtMessage;
    private int txtOkColor;
    private String txt_message;
    private LinearLayout yes;

    public interface OnDialogResult {
        void finish();
    }

    public StockDialog(DialogBuilder dialogBuilder) {
        super(dialogBuilder.context);
        this.context = dialogBuilder.context;
        this.onDialogResult = dialogBuilder.onDialogResult;
        this.txt_message = dialogBuilder.txt_message;
        this.txtCancelColor = dialogBuilder.txtCancelColor;
        this.txtOkColor = dialogBuilder.txtOkColor;
        this.bgCancelColor = dialogBuilder.bgCancelColor;
        this.bgOkColor = dialogBuilder.bgOkColor;
        this.dialogBackgroundColor = dialogBuilder.dialogBackgroundColor;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.stock_dialog);
        this.dialog = (RelativeLayout) findViewById(R.id.dialog);
        this.yes = (LinearLayout) findViewById(R.id.edt_delete_crop_delete);
        this.no = (LinearLayout) findViewById(R.id.edt_delete_crop_cancel);
        this.txtMessage = (TextView) findViewById(R.id.txt_ask_question);
        this.txtMessage.setText(this.txt_message);
        this.yes.setBackgroundColor(ContextCompat.getColor(this.context, this.bgOkColor));
        this.no.setBackgroundColor(ContextCompat.getColor(this.context, this.bgCancelColor));
        this.dialog.setBackgroundColor(ContextCompat.getColor(this.context, this.dialogBackgroundColor));
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
