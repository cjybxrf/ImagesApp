package com.example.imagesapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.imagesapp.model.Images;
import com.example.imagesapp.model.api.ImageDataSourceFactory;
import com.example.imagesapp.model.database.ImagesDatabaseDataSource;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelMainActivity extends AndroidViewModel {
    private ImageDataSourceFactory imageDataSourceFactory;
    private Executor executor;
    private LiveData<PagedList<Images>> pagedListLiveData;
    private ImagesDatabaseDataSource imagesDatabaseDataSource;

    public ViewModelMainActivity(Application application) {
        super(application);
        imageDataSourceFactory = new ImageDataSourceFactory();
        executor = Executors.newCachedThreadPool();
        imagesDatabaseDataSource = new ImagesDatabaseDataSource(application);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(20)
                .build();

        pagedListLiveData = new LivePagedListBuilder<Integer, Images>(imageDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Images>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public void insertImage(Images images) {
        imagesDatabaseDataSource.insertImage(images);
    }
}
