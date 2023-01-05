package com.example.android.redditreader.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.paging.PagingData;

import com.example.android.redditreader.model.Children;
import com.example.android.redditreader.model.RootRepository;

import io.reactivex.rxjava3.core.Flowable;

public class MainActivityViewModel extends AndroidViewModel {

    private final RootRepository rootRepository;
    private Flowable<PagingData<Children>> childrenPagedData = null;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        rootRepository = new RootRepository(this);
    }

    public Flowable<PagingData<Children>> getAllPosts() {
        if (childrenPagedData == null) {
            childrenPagedData = rootRepository.getPagingLiveData();
        }
        return childrenPagedData;
    }
}