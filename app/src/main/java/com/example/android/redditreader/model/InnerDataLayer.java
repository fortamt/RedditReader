package com.example.android.redditreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.concurrent.TimeUnit;

public class InnerDataLayer {

    @SerializedName("thumbnail")
    String thumbnail;

    @SerializedName("created")
    long created;

    @SerializedName("author")
    String author;

    @SerializedName("num_comments")
    int numComments;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreated() {
        long different = System.currentTimeMillis() - created * 1000;
        String postedAgo = Long.toString(TimeUnit.MILLISECONDS.toHours(different));
        return postedAgo + " hours ago";
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }
}