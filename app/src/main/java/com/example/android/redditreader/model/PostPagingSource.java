package com.example.android.redditreader.model;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.android.redditreader.service.RedditApiInstance;
import com.example.android.redditreader.service.TopPostService;

import java.util.Objects;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostPagingSource extends RxPagingSource<String, Children> {

    private final TopPostService postService;

    public PostPagingSource() {
        this.postService = RedditApiInstance.getService();
    }

    @NonNull
    @Override
    public Single<LoadResult<String, Children>> loadSingle(@NonNull LoadParams<String> loadParams) {
        return postService.getTopPosts(loadParams.getKey(), null)
                .subscribeOn(Schedulers.io())
                .map(this::toLoadResult)
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<String, Children> toLoadResult(
            @NonNull RootApiResponse response) {
        return new LoadResult.Page<>(
                response.getData().getChildren(),
                null, // Only paging forward.
                response.getData().getAfter(),
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }

    @Nullable
    @Override
    public String getRefreshKey(@NonNull PagingState<String, Children> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }
        return Objects.requireNonNull(pagingState.closestPageToPosition(anchorPosition)).getNextKey();
    }
}