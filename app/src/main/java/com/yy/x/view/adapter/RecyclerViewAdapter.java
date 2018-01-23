package com.yy.x.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.x.R;
import com.yy.x.model.Type1ItemBean;

import java.util.List;

/**
 * date:   2018/1/23 19:01 <br/>
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolders> {

    private List<Type1ItemBean> itemBeanList;
    private Context context;

    public RecyclerViewAdapter(List<Type1ItemBean> itemBeanList, Context context) {
        this.itemBeanList = itemBeanList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_type, null);
        RecyclerViewHolders recyclerViewHolders = new RecyclerViewHolders(layoutView);
        return recyclerViewHolders;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.tv_type_name.setText(itemBeanList.get(position).getName());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return itemBeanList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder{
        private TextView tv_type_name;

        public RecyclerViewHolders(View itemView) {
            super(itemView);

            tv_type_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
