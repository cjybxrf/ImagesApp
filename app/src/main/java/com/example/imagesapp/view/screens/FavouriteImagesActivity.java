package com.example.imagesapp.view.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.imagesapp.R;
import com.example.imagesapp.model.Images;
import com.example.imagesapp.view.adapters.AdapterFavouriteImages;
import com.example.imagesapp.viewmodel.ViewModelFavouriteImages;

import java.util.ArrayList;
import java.util.List;

public class FavouriteImagesActivity extends AppCompatActivity {
    private List<Images> imagesList = new ArrayList<>();
    private ViewModelFavouriteImages viewModelFavouriteImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_images);
        viewModelFavouriteImages = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(ViewModelFavouriteImages.class);
        fillRecyclerView();

        ImageView imageView = findViewById(R.id.feedImages);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavouriteImagesActivity.this, MainActivity.class));
            }
        });
    }

    private void fillRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFavouriteImages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterFavouriteImages adapter = new AdapterFavouriteImages(this, viewModelFavouriteImages);
        viewModelFavouriteImages.getAllImages().observe(this, new Observer<List<Images>>() {
            @Override
            public void onChanged(List<Images> images) {
                imagesList = images;
                adapter.setImagesList(imagesList);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}