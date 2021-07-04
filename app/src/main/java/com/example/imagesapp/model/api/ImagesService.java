package com.example.imagesapp.model.api;

import com.example.imagesapp.model.Images;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImagesService {
    @GET("list?")
    Call<List<Images>> getImages(@Query("page") int page, @Query("limit") int limit);
}
