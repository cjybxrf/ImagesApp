package com.example.imagesapp.view.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.imagesapp.R;
import com.example.imagesapp.model.Images;
import com.example.imagesapp.view.adapters.AdapterMainActivity;
import com.example.imagesapp.viewmodel.ViewModelMainActivity;

public class MainActivity extends AppCompatActivity {
    private ViewModelMainActivity viewModelMainActivity;
    private PagedList<Images> imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModelMainActivity = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(ViewModelMainActivity.class);
        getImages();
        ImageView imageView = findViewById(R.id.favoriteImages);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavouriteImagesActivity.class));
            }
        });
    }

    private void getImages() {
        viewModelMainActivity.getPagedListLiveData().observe(this, new Observer<PagedList<Images>>() {
            @Override
            public void onChanged(PagedList<Images> images) {
                if (images != null) {
                    imagesList = images;
                    fillRecyclerView();
                }
            }
        });
    }

    private void fillRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMainActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterMainActivity adapterMainActivity = new AdapterMainActivity(this, viewModelMainActivity);
        adapterMainActivity.submitList(imagesList);
        recyclerView.setAdapter(adapterMainActivity);
    }
}