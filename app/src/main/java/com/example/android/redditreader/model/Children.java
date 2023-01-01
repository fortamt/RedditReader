package com.example.android.redditreader.model;

import com.google.gson.annotations.SerializedName;

public class Children {

    @SerializedName("data")
    InnerDataLayer data;

    public void setData(InnerDataLayer data) {
        this.data = data;
    }

    public InnerDataLayer getData() {
        return data;
    }
}