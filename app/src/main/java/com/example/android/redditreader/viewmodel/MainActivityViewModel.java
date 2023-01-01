package com.example.android.redditreader.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.redditreader.model.Children;
import com.example.android.redditreader.model.RootRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private RootRepository rootRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        rootRepository = new RootRepository();
    }

    public LiveData<List<Children>> getAllPosts() {
        return rootRepository.getMutableLiveData();
    }

}
