package com.example.android.redditreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OuterDataLayer {

    @SerializedName("after")
    String after;

    @SerializedName("dist")
    int dist;

    @SerializedName("children")
    List<Children> children;

    @SerializedName("before")
    String before;


    public void setAfter(String after) {
        this.after = after;
    }
    public String getAfter() {
        return after;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
    public int getDist() {
        return dist;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }
    public List<Children> getChildren() {
        return children;
    }

    public void setBefore(String before) {
        this.before = before;
    }
    public String getBefore() {
        return before;
    }

}