package com.example.imagesapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.imagesapp.model.Images;
import com.example.imagesapp.model.database.ImagesDatabaseDataSource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewModelFavouriteImages extends AndroidViewModel {
    private ImagesDatabaseDataSource imagesDatabaseDataSource;

    public ViewModelFavouriteImages(@NonNull @NotNull Application application) {
        super(application);
        imagesDatabaseDataSource = new ImagesDatabaseDataSource(application);
    }

    public LiveData<List<Images>> getAllImages() {
        return imagesDatabaseDataSource.getAllImages();
    }

    public void deleteImage(Images images) {
        imagesDatabaseDataSource.deleteImage(images);
    }


}
