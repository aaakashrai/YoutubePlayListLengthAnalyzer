package com.raitech.youtubeplaylistlengthcalculator;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Displayer {
    List<DataPack> data;
    private RecyclerView rc;
    private TextView time;
    ItemAdaptor adapt;
    public Displayer(RecyclerView tx, TextView ti){
            rc= tx;
            time=ti;
    }

    public void setData(List<DataPack> data) {
        this.data = data;
    }

    public void display(String tim){
        adapt= new ItemAdaptor(data);
        rc.setAdapter(adapt);
        time.setText("Total time: "+ data.size()+"  "+tim);
    }

    public ItemAdaptor getAdapt() {
        return adapt;
    }
}
