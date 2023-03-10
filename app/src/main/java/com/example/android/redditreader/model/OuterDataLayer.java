package com.example.android.redditreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OuterDataLayer {

    @SerializedName("after")
    private String after;

    @SerializedName("children")
    private List<Children> children;

    @SerializedName("before")
    private String before;


    public void setAfter(String after) {
        this.after = after;
    }

    public String getAfter() {
        return after;
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