package com.raitech.youtubeplaylistlengthcalculator;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Extractor {
    private String url;
    private String resp;
    public Displayer show;
    private List<DataPack> data;
    RequestQueue rq;
    public Extractor(String url, RequestQueue rq, Displayer show){
        this.url= url;
        this.rq= rq;
        resp="";
        this.show=show;
        data= new ArrayList<>();
    }

    public void Requester() {
        StringRequest stringRequest = new myRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("INFOOO", url);
                        resp= response;
                        infoGetter(response);
                        show.setData(data);
                        show.display(totalSize());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("APPLI", "Invalid URL");
                finish();
            }
        });
        rq.add(stringRequest);

    }

    public List<DataPack> getData() {
        return data;
    }

    private void infoGetter(String s){
        Document doc= Jsoup.parse(s);
        Log.v("INFOOO", "TRYING");
        Elements e= doc.getElementsByClass("pl-video-title");
        int i=0;
        for(Element x : e){
            DataPack t= new DataPack();
            t.setName(x.text());
            data.add(t);
        }
        e= doc.getElementsByClass("timestamp");
        i=0;
        for(Element x : e){
            DataPack t= data.get(i);
            t.setLength(x.text());
            data.set(i,t);
            i++;
        }
        e= doc.getElementsByClass("pl-video-title-link yt-uix-tile-link yt-uix-sessionlink  spf-link ");
        i=0;
        for(Element x : e){
            DataPack t= data.get(i);
            t.setVideourl("https://youtube.com/"+x.attr("href"));
            data.set(i,t);
            i++;
        }
        e= doc.getElementsByTag("img");
        i=0;
        for(Element x: e){
            if(x.attr("width").equals("72")){
                DataPack t= data.get(i);
                t.setImageurl(x.attr("data-thumb"));
                data.set(i,t);
                i++;
            }
        }
        Log.v("INFOOO", e.size()+"");
    }

    public String getResp(){
        return resp;
    }

    public void finish(){
        this.finish();
    }

    String totalSize(){
        long ans=0;
        for(DataPack x: data){
            ans += timefromstrtolong(x);
        }
        long hour= ans/3600;
        long min= (ans%3600)/60;
        long sec= ans-3600*hour-60*min;
        String respstring="";
        if(hour!=0)
            respstring=hour+":";
        if(min<=9)
            respstring+="0"+min+":";
        else respstring+=min+":";
        if(sec<=9)
            respstring+="0"+sec;
        else respstring+=sec+"";
        return respstring;
    }

    long timefromstrtolong(DataPack x){
        String f[]=x.getLength().split(":");
        long contribution=0;
        if(f.length==1)
            contribution= Integer.parseInt(f[0]);
        if(f.length==2)
            contribution = Integer.parseInt(f[0])*60+ Integer.parseInt(f[1]);
        else{
            contribution = Integer.parseInt(f[0])*3600+Integer.parseInt(f[1])*60+ Integer.parseInt(f[2]);
        }
        return contribution;
    }
}
