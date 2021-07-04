package com.example.imagesapp.model.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.imagesapp.model.Images;

@Database(entities = {Images.class}, version = 1, exportSchema = false)
public abstract class ImagesDatabase extends RoomDatabase {
    private static ImagesDatabase imagesDataBase;
    private static final Object LOCK = new Object();
    private static final String DB_NAME = "images_db";

    public static ImagesDatabase getInstance(Application application) {
        synchronized (LOCK) {
            if (imagesDataBase == null) {
                imagesDataBase = Room.databaseBuilder(application, ImagesDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return imagesDataBase;
    }

    public abstract ImagesDao getDao();
}
