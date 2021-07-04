package com.example.imagesapp.model.api;

import android.util.Log;

import androidx.paging.PageKeyedDataSource;

import com.example.imagesapp.model.Images;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesDataSource extends PageKeyedDataSource<Integer, Images> {
    private ImagesService imagesService;
    private List<Images> imagesList;

    public ImagesDataSource() {
        this.imagesService = ImagesRepository.getInstance().getImagesService();
        imagesList = new ArrayList<>();
    }

    @Override
    public void loadAfter(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer,
            Images> loadCallback) {
        Call<List<Images>> call = imagesService.getImages(loadParams.key, 15);
        call.enqueue(new Callback<List<Images>>() {
            @Override
            public void onResponse(Call<List<Images>> call, Response<List<Images>> response) {
                imagesList = response.body();
                if (imagesList != null) {
                    loadCallback.onResult(imagesList, loadParams.key + 1);
                }
            }

            @Override
            public void onFailure(Call<List<Images>> call, Throwable t) {
                Log.i("error", "ошибка вторичной загрузки\n" + t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer,
            Images> loadCallback) {

    }

    @Override
    public void loadInitial(@NotNull LoadInitialParams<Integer> loadInitialParams,
                            @NotNull LoadInitialCallback<Integer, Images> loadInitialCallback) {
        Call<List<Images>> call = imagesService.getImages(1, 15);
        call.enqueue(new Callback<List<Images>>() {
            @Override
            public void onResponse(Call<List<Images>> call, Response<List<Images>> response) {
                imagesList = response.body();
                if (imagesList != null && !imagesList.isEmpty()) {
                    loadInitialCallback.onResult(imagesList, null, 2);
                }
            }

            @Override
            public void onFailure(Call<List<Images>> call, Throwable t) {
                Log.i("error", "ошибка первичной загрузки\n" + t.getMessage());
            }
        });
    }
}
