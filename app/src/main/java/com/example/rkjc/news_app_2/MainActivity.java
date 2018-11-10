package com.example.rkjc.news_app_2;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.news_recyclerview);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, newsItems);
        recyclerView.setAdapter(newsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsQueryTask task = new NewsQueryTask();
        task.execute();
    }

    public void populateRecyclerView(String jstring) {
     Log.d("mycode", jstring);
        newsItems = JsonUtils.parseNews(jstring);
        newsRecyclerViewAdapter.newsItems.addAll(newsItems);
        newsRecyclerViewAdapter.notifyDataSetChanged();
    }


    class NewsQueryTask extends AsyncTask<String, Void, String> {
        private final String TAG = "MainActivity Async";
        private String jstring;

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "Async task do in background");
            String jstring = null;
            try {
                jstring = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jstring;
        }

        @Override
        protected void onPostExecute(String newsResult) {
            populateRecyclerView(newsResult);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int newsclicked=item.getItemId();
        if(newsclicked==R.id.action_search){
            newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, newsItems);
            recyclerView.setAdapter(newsRecyclerViewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            NewsQueryTask task = new NewsQueryTask();
            task.execute();
        }

        return super.onOptionsItemSelected(item);
    }

}

