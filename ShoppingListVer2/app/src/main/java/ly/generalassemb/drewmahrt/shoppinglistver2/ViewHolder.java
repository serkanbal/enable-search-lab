package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Serkan on 25/10/16.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextName, mTextDesc, mTextPrice, mTextType;

    public ViewHolder(View itemView) {
        super(itemView);
        mTextName = (TextView) itemView.findViewById(R.id.text_name);
        mTextDesc = (TextView) itemView.findViewById(R.id.text_desc);
        mTextPrice = (TextView) itemView.findViewById(R.id.text_price);
        mTextType = (TextView) itemView.findViewById(R.id.text_type);

    }
}
