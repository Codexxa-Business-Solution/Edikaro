package com.readymadedata.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.readymadedata.app.MyApplication;
import com.readymadedata.app.databinding.ItemPostBinding;
import com.readymadedata.app.items.PostItem;
import com.readymadedata.app.listener.ClickListener;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {

    Context context;
    ClickListener<Integer> listener;



    List<PostItem> postItemList;
    private int itemWidth = 0;
    int column;
    float width;

    public DetailAdapter(Context context, ClickListener<Integer> listener, int column, float width) {
        this.context = context;
        this.listener = listener;
        this.column = column;
        this.width = width;
        itemWidth = MyApplication.getColumnWidth(column, width);
    }

    public void filterList(List<PostItem> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        postItemList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public void setData(List<PostItem> postItemList) {
        this.postItemList = postItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setPostdata(postItemList.get(position));
        holder.itemView.setOnClickListener(v -> {
            listener.onClick(position);
        });
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) holder.binding.cvBase.getLayoutParams();
        params.width = itemWidth;
        params.height = itemWidth;
        holder.binding.cvBase.setLayoutParams(params);
    }


    @Override
    public int getItemCount() {
        if (postItemList != null && postItemList.size() > 0) {
            return postItemList.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemPostBinding binding;

        public MyViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
