package com.example.feratajsoup.Naslovnica;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.feratajsoup.FullArticle.FullNews;
import com.example.feratajsoup.R;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList<NewsItem> mList;
    private MySingleton mySingleton;
    private ImageLoader imageLoader;

    public MyRecyclerAdapter(Context ctx, ArrayList<NewsItem> mList) {
        this.ctx = ctx;
        this.mList = mList;
        mySingleton=MySingleton.getInstance(ctx);
        imageLoader=mySingleton.getImageLoader();




    }

    public MyRecyclerAdapter(ArrayList<NewsItem> newsList, Main2Activity mainActivity) {

    }

    @NonNull
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(ctx.getApplicationContext()).inflate(R.layout.news_item_layout,viewGroup,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);



        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerAdapter.MyViewHolder myViewHolder, int position) {






        final NewsItem newsItem=mList.get(position);
        myViewHolder.title.setText(newsItem.getTitle());
        myViewHolder.link.setText(newsItem.getLink());
        myViewHolder.date.setText(newsItem.getDate());
        myViewHolder.tag.setText(newsItem.getTag());

        //set /apply animation

      //  myViewHolder.imgurl.setAnimation(AnimationUtils.loadAnimation(ctx,R.anim.item_fall_bottom_anim));
        myViewHolder.cardView.setAnimation(AnimationUtils.loadAnimation(ctx,R.anim.item_fall_bottom_anim));


    final String imgurl=newsItem.getImgurl();
    if (imgurl!=null){
        imageLoader.get(imgurl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                myViewHolder.imgurl.setImageBitmap(response.getBitmap());

            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }





    myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



                Intent intent=new Intent(ctx, FullNews.class);
                intent.putExtra("imgurl",newsItem.imgurl);
                intent.putExtra("title",newsItem.title);
                intent.putExtra("link",newsItem.link);
                intent.putExtra("date",newsItem.date);


            ctx.startActivity(intent);







        }
    });






    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {

        private ImageView imgurl;
        private TextView title;
        private TextView description;
        private TextView link;
        private CardView cardView;
        private TextView date;
        private Typeface myfont;
        private TextView tag;








        public MyViewHolder(@NonNull View itemView) {
            super(itemView);









            cardView=(CardView)itemView.findViewById(R.id.cardviewitem);

            tag=(TextView)itemView.findViewById(R.id.tagtext);
            imgurl=(ImageView)itemView.findViewById(R.id.imageview);
            title=(TextView) itemView.findViewById(R.id.titleText);
            link=(TextView)itemView.findViewById(R.id.linktext);
            date=(TextView)itemView.findViewById(R.id.date);









        }
        public  ImageView getImgurl(){
            return this.imgurl;
        }
    }
}
