package com.example.ahmad.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by ahmad on 24/08/17.
 */

@Table(name = "NewsArticle")
public class NewsArticle extends Model implements Parcelable {

    @Column(name = "Title")
    private String title;

    @Column(name = "Image")
    private String image;

    @Column(name = "PublishAt")
    private String publishAt;

    public NewsArticle(String title, String image, String publishAt) {
        super();
        this.title = title;
        this.image = image;
        this.publishAt = publishAt;
    }

    public NewsArticle() {
        super();
    }

    protected NewsArticle(Parcel in) {
        super();
        title = in.readString();
        image = in.readString();
        publishAt = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(String publishAt) {
        this.publishAt = publishAt;
    }

    public static final Creator<NewsArticle> CREATOR = new Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel in) {
            return new NewsArticle(in);
        }

        @Override
        public NewsArticle[] newArray(int size) {
            return new NewsArticle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeString(publishAt);
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", publishAt='" + publishAt + '\'' +
                '}';
    }

    public static List<NewsArticle> getAllArticle() {
        return new Select().from(NewsArticle.class)
                .orderBy("Id Desc").execute();
    }
}
