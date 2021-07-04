package com.example.imagesapp.model.api;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.imagesapp.model.Images;

import org.jetbrains.annotations.NotNull;

public class ImageDataSourceFactory extends DataSource.Factory<Integer, Images> {

    private ImagesDataSource imagesDataSource;
    private MutableLiveData<ImagesDataSource> liveData;

    public ImageDataSourceFactory() {
        this.imagesDataSource = new ImagesDataSource();
        this.liveData = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource<Integer, Images> create() {
        liveData.postValue(imagesDataSource);
        return imagesDataSource;
    }

    public MutableLiveData<ImagesDataSource> getLiveData() {
        return liveData;
    }
}
