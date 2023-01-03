package com.example.android.redditreader.model;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.example.android.redditreader.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import kotlinx.coroutines.CoroutineScope;

public class RootRepository {

    private List<Children> childrenList = new ArrayList<>();
    private MutableLiveData<List<Children>> mutableLiveData = new MutableLiveData<>();
    private MainActivityViewModel mainActivityViewModel;

    public RootRepository(MainActivityViewModel mainActivityViewModel) {
        this.mainActivityViewModel = mainActivityViewModel;
    }

    public LiveData<PagingData<Children>> getPagingLiveData(){

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(mainActivityViewModel);
        Pager<String, Children> pager = new Pager(new PagingConfig(25),
                () -> new PostPagingSource(Executors.newCachedThreadPool()));

        LiveData<PagingData<Children>> pagingDataLiveData =
                PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);

        return pagingDataLiveData;
    }
}
