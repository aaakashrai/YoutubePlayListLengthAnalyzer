package com.raitech.youtubeplaylistlengthcalculator;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {

    private TextView pos;
    private ImageView img;

    public TextView getPos() {
        return pos;
    }

    public ImageView getImg() {
        return img;
    }

    public TextView getName() {
        return name;
    }

    public TextView getDur() {
        return dur;
    }

    private TextView name;
    private TextView dur;

    public ListViewHolder(View view){
        super(view);
        pos= view.findViewById(R.id.index);
        img= view.findViewById(R.id.imagesrc);
        name= view.findViewById(R.id.name);
        dur = view.findViewById(R.id.length);
    }
}
