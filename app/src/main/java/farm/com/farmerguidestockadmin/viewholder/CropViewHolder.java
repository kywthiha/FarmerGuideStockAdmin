package farm.com.farmerguidestockadmin.viewholder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.admin.stockadminfarmapp.R;

import farm.com.farmerguidestockadmin.dialog.AddCropDialog;
import farm.com.farmerguidestockadmin.dialog.DeleteCropDialog;
import farm.com.farmerguidestockadmin.dialog.EditCropDialog;
import farm.com.farmerguidestockadmin.model.Crop;
import farm.com.farmerguidestockadmin.statics.RefStatic;
import farm.com.farmerguidestockadmin.utils.UiUtil;

public class CropViewHolder extends RecyclerView.ViewHolder implements  CropBinder{
    TextView crop_name,crop_price,cropCount;
    private Context context;
    LinearLayout btn_edit,btn_delete;
    ImageView menu;
    RelativeLayout linearEdit;
    public CropViewHolder(View itemView) {
        super(itemView);

        context=itemView.getContext();
        crop_name=(TextView)itemView.findViewById(R.id.txt_crop_name);
        crop_price=(TextView)itemView.findViewById(R.id.txt_crop_price);
        cropCount=(TextView)itemView.findViewById(R.id.txt_crop_count);

        menu=(ImageView)itemView.findViewById(R.id.img_menu);


    }

    @Override
    public void onBind(Crop c, int position) {

        this.crop_name.setText(c.getC_name());
        this.crop_price.setText(c.getC_price()+" Ks");
        this.cropCount.setText(c.getCount());

        CropListener listener=new CropListener(c);
        menu.setOnClickListener(listener);


    }

    public class CropListener implements View.OnClickListener{
        Crop c;
        public CropListener(Crop c) {
            this.c = c;
        }

        @Override
        public void onClick(final View v) {
            if(v==menu){
                PopupMenu popup = new PopupMenu(v.getContext(), CropViewHolder.this.menu);
                popup.getMenuInflater().inflate(R.menu.stock_item_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("delete")) {
                            new DeleteCropDialog(v.getContext(), CropListener.this.c, new DeleteCropDialog.OnDialogResult() {
                                @Override
                                public void finish() {

                                    RefStatic.stockRef.child(c.getC_Id()).setValue(null);
                                    if (v.getContext() instanceof Activity) {
                                        Window window = ((Activity) v.getContext()).getWindow();
                                        UiUtil.showToast(context,"stock item deleted");
                                    }

                                }
                            }).show();
                        } else {
                            new EditCropDialog(v.getContext(), CropListener.this.c, new EditCropDialog.OnDialogResult() {
                                @Override
                                public void finish(String str) {
                                    Log.i("edit","edit reached");
                                    RefStatic.stockRef.child(c.getC_Id()).child("c_price").setValue(str);
                                    if (v.getContext() instanceof Activity) {
                                        Window window = ((Activity) v.getContext()).getWindow();
                                        UiUtil.showToast(context,"stock edited");
                                    }
                                }
                            }).show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        }
    }


}
