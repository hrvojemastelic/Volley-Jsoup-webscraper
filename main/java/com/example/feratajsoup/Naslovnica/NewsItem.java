package com.example.feratajsoup.Naslovnica;

import android.graphics.Bitmap;
import android.graphics.DashPathEffect;

public class NewsItem {
    String title;
    String link;
    String imgurl;
    String date;
    String tag;


    public NewsItem(String title, String link, String imgurl,String date,String tag) {
        this.title = title;
        this.tag=tag;
        this.link = link;
        this.imgurl = imgurl;
        this.date=date;
    }




    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link=link;
    }
    public String getImgurl(){
        return imgurl;
    }
    public void setImgurl(String imgurl){
        this.imgurl=imgurl;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date= date;
    }
    public String getTag(){
        return tag;
    }
    public void setTag(String tag){
        this.tag=tag;
    }

}
