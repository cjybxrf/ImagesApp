package com.example.imagesapp.model.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesRepository {
    private static ImagesRepository imagesRepository;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://picsum.photos/v2/";

    private ImagesRepository() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static ImagesRepository getInstance() {
        if (imagesRepository == null) {
            imagesRepository = new ImagesRepository();
        }
        return imagesRepository;
    }

    public ImagesService getImagesService() {
        return retrofit.create(ImagesService.class);
    }
}
