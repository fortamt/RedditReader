package com.example.android.redditreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.android.redditreader.model.Children;
import com.example.android.redditreader.model.Root;
import com.example.android.redditreader.service.RedditApiInstance;
import com.example.android.redditreader.service.TopPostService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Children> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPosts();
    }

    private void getPosts() {

        TopPostService topPostService = RedditApiInstance.getService();
        Call<Root> call = topPostService.getTopPosts();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();

                if (root != null && root.getData() != null) {
                    postsList = (ArrayList<Children>) root.getData().getChildren();

                    for(Children children : postsList) {

                        Log.d("postsList: ", children.getData().getAuthor());
                    }

                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });

    }
}