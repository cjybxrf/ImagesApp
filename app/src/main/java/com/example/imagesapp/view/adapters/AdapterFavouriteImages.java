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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imagesapp.R;
import com.example.imagesapp.model.Images;
import com.example.imagesapp.viewmodel.ViewModelFavouriteImages;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavouriteImages extends RecyclerView.Adapter<AdapterFavouriteImages.FavouriteImagesViewHolder>  {
    private List<Images> imagesList = new ArrayList<>();
    private Context context;
    private ViewModelFavouriteImages viewModelFavouriteImages;

    public AdapterFavouriteImages(Context context, ViewModelFavouriteImages viewModelFavouriteImages) {
        this.context = context;
        this.viewModelFavouriteImages = viewModelFavouriteImages;
    }

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public FavouriteImagesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new FavouriteImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavouriteImagesViewHolder holder, int position) {
        Images images = imagesList.get(position);
        if (images != null) {
            Glide.with(context).load(images.getDownloadUrl()).into(holder.imageView);
            holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    viewModelFavouriteImages.deleteImage(images);
                    Toast.makeText(context, "remove like", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class FavouriteImagesViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;

        public FavouriteImagesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
