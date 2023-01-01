package com.example.android.redditreader.model;


import androidx.lifecycle.MutableLiveData;

import com.example.android.redditreader.service.RedditApiInstance;
import com.example.android.redditreader.service.TopPostService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RootRepository {

    private List<Children> childrenList = new ArrayList<>();
    private MutableLiveData<List<Children>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Children>> getMutableLiveData() {

        TopPostService topPostService = RedditApiInstance.getService();
        Call<RootApiResponse> call = topPostService.getTopPosts();
        call.enqueue(new Callback<RootApiResponse>() {
            @Override
            public void onResponse(Call<RootApiResponse> call, Response<RootApiResponse> response) {
                RootApiResponse root = response.body();
                if (root != null && root.getData() != null) {
                    childrenList = (ArrayList<Children>) root.getData().getChildren();
                    mutableLiveData.setValue(childrenList);
                }
            }

            @Override
            public void onFailure(Call<RootApiResponse> call, Throwable t) {
            }
        });

        return mutableLiveData;
    }
}
