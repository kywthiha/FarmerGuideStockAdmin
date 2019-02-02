package farm.com.farmerguidestockadmin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.admin.stockadminfarmapp.R;

import java.util.List;

import farm.com.farmerguidestockadmin.model.Crop;
import farm.com.farmerguidestockadmin.viewholder.CropViewHolder;

public class CropAdapter extends Adapter<CropViewHolder> {
    private Context context;
    private List<Crop> crops;

    public CropAdapter(Context context, List<Crop> crops) {
        this.context = context;
        this.crops = crops;
    }

    @NonNull
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CropViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item, parent, false));
    }

    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        holder.onBind((Crop) this.crops.get(position), position);
    }

    public int getItemCount() {
        return this.crops.size();
    }
}
