package com.example.ahmad.newsapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmad.newsapp.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ahmad on 24/08/17.
 */

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.ViewHolder> {

    private List<NewsArticle> dataSet;
    private Context context;
    private static String TAG = NewsArticleAdapter.class.getSimpleName();

    public NewsArticleAdapter(Context context) {
        this.dataSet = new ArrayList<>();
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, publishAt;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.iv_image);
            title = itemView.findViewById(R.id.tv_title);
            publishAt = itemView.findViewById(R.id.tv_publish_at);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsArticle newsArticle = dataSet.get(position);

        holder.title.setText(newsArticle.getTitle());
        holder.publishAt.setText(newsArticle.getPublishAt());
        Glide.with(context).load(newsArticle.getImage()).into(holder.image);
        Log.d(TAG, "Glide: " + newsArticle.getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addAll(List<NewsArticle> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    public void clear() {
        dataSet.clear();
        notifyDataSetChanged();
    }
}
