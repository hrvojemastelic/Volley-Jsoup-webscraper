package com.example.feratajsoup.FullArticle;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.feratajsoup.Naslovnica.MySingleton;
import com.example.feratajsoup.R;
import com.flaviofaria.kenburnsview.KenBurnsView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FullNews extends AppCompatActivity {
    private RequestQueue mQueue;


    KenBurnsView imageView;
    TextView textView;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    String imgurl,title,link,date;
    TextView tit;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingbtn);




        imageView=(KenBurnsView) findViewById(R.id.fullimageview);
        textView=(TextView)findViewById(R.id.fulltext);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.ctool);



        imgurl=getIntent().getStringExtra("imgurl");
        title=getIntent().getStringExtra("title");
        link=getIntent().getStringExtra("link");
        date=getIntent().getStringExtra("date");

        tit=(TextView)findViewById(R.id.fulltitletext);
       // toolbar.setTitle(title);


        Glide.with(this).load(imgurl).into(imageView);



        mQueue = MySingleton.getInstance(this).getRequestQueue();

        tit.setText(title);


        StringRequest mRequest = new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Document document = Jsoup.parse(response);
                Elements list=document.select("div[id=post-area]");
                for (int i = 0 ;i< list.size();i++){
                    Element item = list.get(i);

                    String paragraph = String.valueOf(item.select("p"));
                   // Log.d("ItemParagraph",paragraph);

                    textView.setText(paragraph.replace("<p>","").
                            replace("</p>","\n").trim().
                            replace("<p class=\"wp-caption-text\">","").
                            replace("<strong>","").
                            replace("</strong>","").
                            replace("<br>","").
                            replace("</kg","").
                            replace("&nbsp","")
                            .replace("<p style=\"text-align: center;\">","")
                            .replace("<p style=\"text-align: right;\">","").replace("<em>","").replace("</em>",""));



                }




            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });

        MySingleton.getInstance(this).addToRequestQueue(mRequest);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = link;
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });




        }

    }

