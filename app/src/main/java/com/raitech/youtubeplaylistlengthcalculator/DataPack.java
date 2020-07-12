package com.raitech.youtubeplaylistlengthcalculator;

public class DataPack {
    static int counter=0;
    private String length;
    private String name;
    private int pos;
    private String imageurl;
    private String videourl;

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public DataPack(){
        pos= ++counter;
        length="";
        name="";
        imageurl="";
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }


}
