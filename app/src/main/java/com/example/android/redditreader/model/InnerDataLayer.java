package com.example.android.redditreader.model;

import com.google.gson.annotations.SerializedName;

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

    public long getCreated() {
        return created;
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