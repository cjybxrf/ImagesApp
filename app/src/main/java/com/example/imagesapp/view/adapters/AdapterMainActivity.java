package com.example.imagesapp.view.adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imagesapp.R;
import com.example.imagesapp.model.Images;
import com.example.imagesapp.viewmodel.ViewModelMainActivity;

import org.jetbrains.annotations.NotNull;

public class AdapterMainActivity extends PagedListAdapter<Images, AdapterMainActivity.MainActivityViewHolder> {
    private Context context;
    private ViewModelMainActivity viewModelMainActivity;

    public AdapterMainActivity(Context context, ViewModelMainActivity viewModelMainActivity) {
        super(Images.CALLBACK);
        this.context = context;
        this.viewModelMainActivity = viewModelMainActivity;

    }

    @NonNull
    @NotNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new MainActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainActivityViewHolder holder, int position) {
        Images images = getItem(position);
        if (images != null) {
            Glide.with(context).load(images.getDownloadUrl()).into(holder.imageView);
            holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    viewModelMainActivity.insertImage(images);
                    Toast.makeText(context, "like", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    public class MainActivityViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MainActivityViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
