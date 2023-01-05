package com.example.android.redditreader.model;

import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.android.redditreader.viewmodel.MainActivityViewModel;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class RootRepository {

    private final MainActivityViewModel mainActivityViewModel;

    public RootRepository(MainActivityViewModel mainActivityViewModel) {
        this.mainActivityViewModel = mainActivityViewModel;
    }

    public Flowable<PagingData<Children>> getPagingLiveData(){
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(mainActivityViewModel);
        Pager<String, Children> pager = new Pager<>(
                new PagingConfig(/* pageSize = */ 25),
                PostPagingSource::new);

        Flowable<PagingData<Children>> flowable = PagingRx.getFlowable(pager);
        return PagingRx.cachedIn(flowable, viewModelScope);
    }
}