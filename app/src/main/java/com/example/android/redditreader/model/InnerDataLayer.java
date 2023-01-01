package com.example.android.redditreader.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.example.android.redditreader.R;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

public class InnerDataLayer extends BaseObservable {

    @SerializedName("thumbnail")
    private String thumbnail;

    @BindingAdapter({"thumbnail"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get().load(imageUrl)
                .placeholder(R.drawable.loading)
                .fit()
                .centerInside()
                .into(imageView);
    }

    @SerializedName("created")
    private long created;

    @SerializedName("author")
    private String author;

    @SerializedName("num_comments")
    private int numComments;

    @Bindable
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        notifyPropertyChanged(BR.thumbnail);
    }

    @Bindable
    public String getCreated() {
        long different = System.currentTimeMillis() - created * 1000;
        String postedAgo = Long.toString(TimeUnit.MILLISECONDS.toHours(different));
        return postedAgo + " hours ago";
    }

    public void setCreated(long created) {
        this.created = created;
        notifyPropertyChanged(BR.created);
    }

    @Bindable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        notifyPropertyChanged(BR.author);
    }

    @Bindable
    public String getNumComments() {
        return String.valueOf(numComments);
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
        notifyPropertyChanged(BR.numComments);
    }
}