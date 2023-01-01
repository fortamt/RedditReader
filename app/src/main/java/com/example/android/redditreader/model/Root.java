package com.example.android.redditreader.model;

import com.google.gson.annotations.SerializedName;

public class Root {

    @SerializedName("data")
    OuterDataLayer data;

    public void setData(OuterDataLayer data) {
        this.data = data;
    }
    public OuterDataLayer getData() {
        return data;
    }

}