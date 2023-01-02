package com.example.android.redditreader.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.redditreader.R;
import com.example.android.redditreader.adapter.PostAdapter;
import com.example.android.redditreader.adapter.RecyclerViewClickInterface;
import com.example.android.redditreader.databinding.ActivityMainBinding;
import com.example.android.redditreader.model.Children;
import com.example.android.redditreader.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    private List<Children> childrenList;
    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        getPosts();
        swipeRefreshLayout = activityMainBinding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this::getPosts);
    }

    private void getPosts() {

        mainActivityViewModel.getAllPosts().observe(this, children -> {
            childrenList = children;
            fillRecycleReview();
        });
    }

    private void fillRecycleReview() {
        recyclerView = activityMainBinding.recyclerView;
        adapter = new PostAdapter(childrenList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onImageClick(int position) {

        Intent fullScreenIntent = new Intent(this, FullScreenImageActivity.class);
        fullScreenIntent.setData(Uri.parse(childrenList.get(position).getData().getBetterImageUrl()));
        startActivity(fullScreenIntent);
    }

    @Override
    public void onDownloadButtonClick(int position) {
        Toast.makeText(this, "download", Toast.LENGTH_SHORT).show();

    }
}