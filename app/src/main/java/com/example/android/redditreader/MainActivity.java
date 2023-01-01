package com.example.android.redditreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.android.redditreader.adapter.PostAdapter;
import com.example.android.redditreader.model.Children;
import com.example.android.redditreader.model.Root;
import com.example.android.redditreader.service.RedditApiInstance;
import com.example.android.redditreader.service.TopPostService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Children> childrenList;
    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPosts();

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this::getPosts);
    }

    private void getPosts() {
        TopPostService topPostService = RedditApiInstance.getService();
        Call<Root> call = topPostService.getTopPosts();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                if (root != null && root.getData() != null) {
                    childrenList = (ArrayList<Children>) root.getData().getChildren();
                    fillRecycleReview();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
            }
        });
    }

    private void fillRecycleReview() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PostAdapter(childrenList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}