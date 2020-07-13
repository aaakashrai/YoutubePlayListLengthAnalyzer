package com.raitech.youtubeplaylistlengthcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc;
    Button btn;
    EditText edt;
    TextView time;
    Displayer dis;
    long ans;
    int turn=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        rc= findViewById(R.id.listview);
        rc.setLayoutManager(new LinearLayoutManager(this));
        edt = findViewById(R.id.editText);
        time= findViewById(R.id.editTextTime);
        final RequestQueue rq = Volley.newRequestQueue(this);
        dis= new Displayer(rc,time);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn==1)
                    reset();
                String url=edt.getText().toString()+"&disable_polymer=true";
                Extractor ex=  new Extractor(url,rq, dis);
                ex.Requester();
                turn=1;
            }
        });
        rc.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, rc ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        watchYoutubeVideo(MainActivity.this, ((EditText)view.findViewById(R.id.videourl)).getText().toString());
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this, ((EditText)view.findViewById(R.id.videourl)).getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    private void reset(){
        time.setText("");
        DataPack.counter=0;
        rc.setAdapter(new ItemAdaptor(new ArrayList<DataPack>()));
        ans=0;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

}