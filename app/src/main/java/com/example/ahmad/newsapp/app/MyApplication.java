package com.example.ahmad.newsapp.app;

import android.app.Application;
import android.content.res.Configuration;

import com.activeandroid.ActiveAndroid;
import com.example.ahmad.newsapp.model.NewsArticle;

/**
 * Created by ahmad on 25/08/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        com.activeandroid.Configuration.Builder builder =
                new com.activeandroid.Configuration.Builder(this);
        builder.addModelClass(NewsArticle.class);
        ActiveAndroid.initialize(this);
    }
}
