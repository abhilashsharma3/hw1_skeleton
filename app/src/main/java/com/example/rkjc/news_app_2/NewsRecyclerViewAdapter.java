package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {

    Context newsContext;
    ArrayList<NewsItem> newsItems;


    public NewsRecyclerViewAdapter(Context context,ArrayList<NewsItem> newsItem){
        this.newsContext=context;
        this.newsItems=newsItem;
    }

    @NonNull
    @Override
    public NewsRecyclerViewAdapter.NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        boolean atp=false;
        View view=layoutInflater.inflate(R.layout.news_item,parent,atp);
        NewsItemViewHolder newsItemViewHolder=new NewsItemViewHolder(view);
        return newsItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView Title;
        TextView desc;
        TextView date;
        TextView url;

        public NewsItemViewHolder(View itemView){
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.Title);
            desc=(TextView) itemView.findViewById(R.id.desc);
            date= (TextView) itemView.findViewById(R.id.date);

        }
        public void bind(final int index){
            Title.setText(newsItems.get(index).getTitle());
            desc.setText(newsItems.get(index).getDescription());
            date.setText(newsItems.get(index).getDate());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String url1=newsItems.get(getAdapterPosition()).getUrl();
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url1));
            newsContext.startActivity(intent);
        }
    }
}
