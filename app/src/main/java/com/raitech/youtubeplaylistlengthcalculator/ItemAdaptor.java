package com.raitech.youtubeplaylistlengthcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdaptor extends
        RecyclerView.Adapter<ListViewHolder> {

    List<DataPack> data;

    public ItemAdaptor(List<DataPack> data) {
        this.data = data;
    }

    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.listelement, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.getDur().setText(data.get(position).getLength());
        holder.getPos().setText(data.get(position).getPos() + "");
        holder.getName().setText(data.get(position).getName());
        Picasso.get().load(data.get(position).getImageurl()).into(holder.getImg());
        holder.getImg().setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.getVideourl().setText(data.get(position).getVideourl());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
