package com.example.android.redditreader.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagingData;

import com.example.android.redditreader.model.Children;
import com.example.android.redditreader.model.RootRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private RootRepository rootRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        rootRepository = new RootRepository(this);
    }

    public LiveData<PagingData<Children>> getAllPosts() {
        return rootRepository.getPagingLiveData();
    }

}
