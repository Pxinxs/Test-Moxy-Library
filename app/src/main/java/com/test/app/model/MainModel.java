package com.test.app.model;

import com.test.app.entity.Post;
import com.test.app.managers.ApiManager;

import java.util.List;

import retrofit2.HttpException;

public class MainModel {

    public void getPosts(final LoadCallback callback) {
        new ApiManager().getPosts()
                .subscribe(posts -> {
                    callback.onLoadSuccess(posts);
                }, error -> {
                    if (error instanceof HttpException) {
                        callback.onLoadError(((HttpException) error).message());
                    } else {
                        callback.onLoadError(null);
                    }
                });
    }

    public interface LoadCallback {
        void onLoadSuccess(List<Post> posts);

        void onLoadError(String errorMessage);
    }
}
