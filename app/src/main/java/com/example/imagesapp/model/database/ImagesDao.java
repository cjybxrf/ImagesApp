package com.example.imagesapp.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.imagesapp.model.Images;

import java.util.List;

@Dao
public interface ImagesDao {

    @Query("SELECT * FROM images")
    LiveData<List<Images>> getAllImages();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(Images images);

    @Delete
    void deleteImage(Images images);
}
