package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Serkan on 25/10/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
    List<ItemObject> mItemObjectList;

    public RecyclerAdapter(List<ItemObject> itemObjectList) {
        mItemObjectList = itemObjectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout,
                parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextName.setText(mItemObjectList.get(position).getItemName());
        holder.mTextDesc.setText(mItemObjectList.get(position).getDescription());
        holder.mTextPrice.setText(mItemObjectList.get(position).getPrice());
        holder.mTextType.setText(mItemObjectList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mItemObjectList.size();
    }

    public void replaceData(List<ItemObject> newList){
        mItemObjectList = newList;
        notifyDataSetChanged();
    }

}
