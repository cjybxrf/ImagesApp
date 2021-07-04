package com.example.imagesapp.model.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.imagesapp.model.Images;

import java.util.List;

public class ImagesDatabaseDataSource {
    private ImagesDao imagesDao;

    public ImagesDatabaseDataSource(Application application) {
        imagesDao = ImagesDatabase.getInstance(application).getDao();
    }

    public LiveData<List<Images>> getAllImages() {
        return imagesDao.getAllImages();
    }

    public void insertImage(Images images) {
        new InsertImage().execute(images);
    }

    private class InsertImage extends AsyncTask<Images, Void, Void> {

        @Override
        protected Void doInBackground(Images... images) {
            imagesDao.insertImage(images[0]);
            return null;
        }
    }

    public void deleteImage(Images images) {
        new DeleteImage().execute(images);
    }

    private class DeleteImage extends AsyncTask<Images, Void, Void> {

        @Override
        protected Void doInBackground(Images... images) {
            imagesDao.deleteImage(images[0]);
            return null;
        }
    }
}
