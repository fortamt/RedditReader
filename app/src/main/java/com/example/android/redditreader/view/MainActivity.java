package com.example.android.redditreader.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.example.android.redditreader.R;
import com.example.android.redditreader.adapter.ChildrenComparator;
import com.example.android.redditreader.adapter.PostAdapter;
import com.example.android.redditreader.adapter.RecyclerViewClickInterface;
import com.example.android.redditreader.databinding.ActivityMainBinding;

import com.example.android.redditreader.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        swipeRefreshLayout = activityMainBinding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this::getPosts);

        getPosts();
    }

    private void getPosts() {
        fillRecycleReview();
        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        mainActivityViewModel.getAllPosts().observe(this, childrenPagingData ->
                adapter.submitData(getLifecycle(), childrenPagingData));
    }

    private void fillRecycleReview() {
        recyclerView = activityMainBinding.recyclerView;
        adapter = new PostAdapter(new ChildrenComparator(), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onImageClick(int position) {
        Intent fullScreenIntent = new Intent(this, FullScreenImageActivity.class);
        fullScreenIntent.setData(Uri.parse(adapter.snapshot().get(position).getData().getBetterImageUrl()));
        startActivity(fullScreenIntent);
    }

    @Override
    public void onDownloadButtonClick(int position) {
        Toast.makeText(this, "Downloading started", Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
        String url = adapter.snapshot().get(position).getData().getBetterImageUrl();
        downloadImage(url, "downloadedImage");
    }

    private void downloadImage(String url, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Downloading image");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}