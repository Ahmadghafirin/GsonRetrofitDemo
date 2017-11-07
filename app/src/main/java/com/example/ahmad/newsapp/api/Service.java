package com.example.ahmad.newsapp.api;

import android.telecom.Call;

import com.example.ahmad.newsapp.model.article.GetArticle;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ahmad on 24/08/17.
 */

public interface Service {


    @GET("articles?source=the-next-web&sortBy=latest&apiKey=f68ae83d88834ae6804da5c5ac9bf169")
    retrofit2.Call<ResponseBody> getArticle();

    @GET("articles?source=the-next-web&sortBy=latest&apiKey=f68ae83d88834ae6804da5c5ac9bf169")
    retrofit2.Call<GetArticle> getArticles();
}
