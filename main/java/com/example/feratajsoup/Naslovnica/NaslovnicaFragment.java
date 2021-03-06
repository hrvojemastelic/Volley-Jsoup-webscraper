package com.example.feratajsoup.Naslovnica;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.feratajsoup.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NaslovnicaFragment extends Fragment {


    private View v;
    private RecyclerView recyclerView;
    private ArrayList<NewsItem> newsItems=new ArrayList<>();
    private MyRecyclerAdapter myRecyclerAdapter;
    private TabLayout tabLayout;
    private TextView textView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RequestQueue mQueue;

    public NaslovnicaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_naslovnica, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v=view;

        init();


        mQueue = MySingleton.getInstance(getActivity()).getRequestQueue();

        String url = "http://www.ferata.hr/";

        StringRequest mRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {





                //parsing the respoense from url
                Document document = Jsoup.parse(response);
                // creating list or searching for main html tag in our document , where our child tags are located
                Elements list=document.select("div[class=home-story-cat]");

                //iterating trough child  elements in list of elements in  main tag
                for (int i = 0 ;i< list.size();i++){
                    // creating element in wich we store  tags from iterator in  list
                    Element item = list.get(i);

                    // selecting tags we want from list



                    String tag= String.valueOf(item.select("ul.post-categories").select("a").text());


                    String link =item.select("a").attr("href");
                       Log.d("itemLink", link);
                    String title= String.valueOf(item.select("a").attr("title"));
                     Log.d("Itemtitle", title);
                    String description= String.valueOf(item.select("p").text());
                    Log.d("Itemdes", description);
                    String imgurl= String.valueOf(item.select("img").attr("data-lazy-src"));
                     Log.d("Itemimg", imgurl);
                    String date=String.valueOf(item.select("div[class=cat-small-date]").text());


                    //creating new object of NewsItem class and passing selected (String values) tags in constructor  grouping them in to one object
                    NewsItem model=new NewsItem(title,link,imgurl,date,tag);
                    //adding  new object to our array list -as one
                    newsItems.add(model);




                }









                myRecyclerAdapter.notifyDataSetChanged();
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(mRequest);




        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                newsItems.clear();

                mQueue = MySingleton.getInstance(getActivity()).getRequestQueue();

                //url of web pagge which we want to scrape

                String url = "http://www.ferata.hr/";

                StringRequest mRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {





                        //parsing the respoense from url
                        Document document = Jsoup.parse(response);
                        // creating list or searching for main html tag in our document , where our child tags are located
                        Elements list=document.select("div[class=home-story-cat]");

                        //iterating trough child  elements in list of elements in  main tag
                        for (int i = 0 ;i< list.size();i++){
                            // creating element in wich we store  tags from iterator in  list
                            Element item = list.get(i);

                            // selecting tags we want from list
                            String tag= String.valueOf(item.select("ul.post-categories").select("a").text());


                            String link =item.select("a").attr("href");
                            Log.d("itemLink", link);
                            String title= String.valueOf(item.select("a").attr("title"));
                            Log.d("Itemtitle", title);
                            String description= String.valueOf(item.select("p").text());
                            Log.d("Itemdes", description);
                            String imgurl= String.valueOf(item.select("img").attr("data-lazy-src"));
                            Log.d("Itemimg", imgurl);
                            String date=String.valueOf(item.select("div[class=cat-small-date]").text());

                            //creating new object of NewsItem class and passing selected (String values) tags in constructor making grouping them in to one object
                            NewsItem model=new NewsItem(title,link,imgurl,date,tag);
                            //adding  new object to our array list -as one
                            newsItems.add(model);

                        }






                        myRecyclerAdapter.notifyDataSetChanged();



                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }


                });



                MySingleton.getInstance(getActivity()).addToRequestQueue(mRequest);



                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });


    }

    private void init() {
        recyclerView=(RecyclerView)v.findViewById(R.id.rc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        myRecyclerAdapter=new MyRecyclerAdapter(getActivity(),newsItems);

        recyclerView.setAdapter(myRecyclerAdapter);

        swipeRefreshLayout =(SwipeRefreshLayout)v.findViewById(R.id.swipe);
    }
}
