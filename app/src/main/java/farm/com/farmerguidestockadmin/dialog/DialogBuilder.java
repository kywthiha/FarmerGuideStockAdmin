package farm.com.farmerguidestockadmin.dialog;

import android.content.Context;

class DialogBuilder {
    public int bgCancelColor;
    public int bgOkColor;
    public Context context;
    public int dialogBackgroundColor;
    public StockDialog.OnDialogResult onDialogResult;
    public int txtCancelColor;
    public int txtOkColor;
    public String txt_message;

    DialogBuilder() {
    }

    DialogBuilder Context(Context context) {
        this.context = context;
        return this;
    }

    DialogBuilder Result(StockDialog.OnDialogResult result) {
        this.onDialogResult = result;
        return this;
    }

    DialogBuilder txtCancelColor(int color) {
        this.txtCancelColor = color;
        return this;
    }

    DialogBuilder txtOkColor(int color) {
        this.txtOkColor = color;
        return this;
    }

    DialogBuilder cancelBgColor(int color) {
        this.txtCancelColor = color;
        return this;
    }

    DialogBuilder okBgColor(int color) {
        this.txtCancelColor = color;
        return this;
    }

    DialogBuilder background(int color) {
        this.dialogBackgroundColor = color;
        return this;
    }

    DialogBuilder message(String mess) {
        this.txt_message = mess;
        return this;
    }

    StockDialog Build() {
        return new StockDialog(this);
    }
}
