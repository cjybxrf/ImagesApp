package com.example.imagesapp.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "images")
public class Images {

    @PrimaryKey(autoGenerate = true)
    private int integerId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("download_url")
    @Expose
    private String downloadUrl;

    public int getIntegerId() {
        return integerId;
    }

    public void setIntegerId(int integerId) {
        this.integerId = integerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public static final DiffUtil.ItemCallback<Images> CALLBACK = new DiffUtil.ItemCallback<Images>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Images oldItem, @NonNull @NotNull Images newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Images oldItem, @NonNull @NotNull Images newItem) {
            return oldItem.getDownloadUrl().equals(newItem.getDownloadUrl());
        }
    };
}
