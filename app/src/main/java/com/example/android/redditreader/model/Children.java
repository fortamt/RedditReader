package com.example.android.redditreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Children {

    @SerializedName("data")
    private InnerDataLayer data;

    public void setData(InnerDataLayer data) {
        this.data = data;
    }

    public InnerDataLayer getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Children children = (Children) o;
        return Objects.equals(data, children.data);
    }
}