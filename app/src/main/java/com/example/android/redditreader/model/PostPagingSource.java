package com.example.android.redditreader.model;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingState;

import com.example.android.redditreader.service.RedditApiInstance;
import com.example.android.redditreader.service.TopPostService;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import retrofit2.HttpException;

public class PostPagingSource extends ListenableFuturePagingSource<String, Children> {

    private final TopPostService postService;
    private Executor executor;

    public PostPagingSource(Executor executor) {
        this.postService = RedditApiInstance.getService();
        this.executor = executor;
    }

    @NonNull
    @Override
    public ListenableFuture<LoadResult<String, Children>> loadFuture(@NonNull LoadParams<String> loadParams) {
        String nextPageKey = loadParams.getKey();
        if (nextPageKey == null) {
            try {
                RootApiResponse rootApiResponse = postService.getTopPosts(null, null).get();
                nextPageKey = rootApiResponse.getData().getAfter();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        ListenableFuture<LoadResult<String, Children>> pageFuture =
                Futures.transform(postService.getTopPosts(nextPageKey, null),
                        this::toLoadResult, executor);

        ListenableFuture<LoadResult<String, Children>> partialLoadResultFuture =
                Futures.catching(pageFuture, HttpException.class,
                        LoadResult.Error::new, executor);

        return Futures.catching(partialLoadResultFuture,
                IOException.class, LoadResult.Error::new, executor);
        }

    private LoadResult<String, Children> toLoadResult(@NonNull RootApiResponse response) {
        return new LoadResult.Page<>(response.getData().getChildren(),
                response.getData().getBefore(),
                response.getData().getAfter(),
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }

    @Nullable
    @Override
    public String getRefreshKey(@NotNull PagingState<String, Children> state) {
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<String, Children> anchorPage = state.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        String prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey;
        }

        String nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey;
        }

        return null;
    }

}

