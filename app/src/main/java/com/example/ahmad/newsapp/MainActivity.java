package com.example.ahmad.newsapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ahmad.newsapp.api.Api;
import com.example.ahmad.newsapp.model.NewsArticle;
import com.example.ahmad.newsapp.model.article.GetArticle;
import com.example.ahmad.newsapp.recyclerview.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private NewsArticleAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<NewsArticle> newsArticleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new NewsArticleAdapter(MainActivity.this);
        newsArticleList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        recyclerView = (RecyclerView) findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
//                swipeRefreshLayout.setRefreshing(false);
                if (NewsArticle.getAllArticle().size() == 0) {
                    getNewsArticle();
                } else {
                    adapter.addAll(NewsArticle.getAllArticle());
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(false);
                getNewsArticle();
            }
        });
    }

    public void getNewsArticle() {
        swipeRefreshLayout.setRefreshing(true);
        Call<ResponseBody> call = Api.getService().getArticle();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    try {
                        newsArticleList.clear();
                        adapter.clear();
                        String body = response.body().string();
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray article = jsonObject.getJSONArray("articles");
                        for (int i = 0; i < article.length(); i++) {
                            JSONObject c = article.getJSONObject(i);

                            String title = c.getString("title");
                            String publishAt = c.getString("publishedAt");
                            String image = c.getString("urlToImage");

                            newsArticleList.add(new NewsArticle(title, image, publishAt));
                            Log.d(TAG, "News Article: " + newsArticleList.get(i).toString());
                            NewsArticle newsArticle = new NewsArticle(title, image, publishAt);
                            newsArticle.save();
                            Log.d(TAG, "Data base: " + newsArticle);
                        }
                        adapter.addAll(newsArticleList);
                        Log.d(TAG, "adapter size: " + adapter.getItemCount());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else Log.d(TAG, "onResponse not succes!: " + response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d(TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void getArticle() {
        Call<GetArticle> call = Api.getService().getArticles();

        call.enqueue(new Callback<GetArticle>() {
                         @Override
                         public void onResponse(Call<GetArticle> call, Response<GetArticle> response) {

                         }

                         @Override
                         public void onFailure(Call<GetArticle> call, Throwable t) {

                         }
                     }
        );
    }
}
