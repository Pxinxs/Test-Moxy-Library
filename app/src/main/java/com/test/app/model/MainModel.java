package com.test.app.model;

import com.test.app.entity.Post;
import com.test.app.managers.ApiManager;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public class MainModel {

    public void getPosts(final LoadCallback callback) {
        // we can inject APiManager with Dagger2
        new ApiManager().getPosts()
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        callback.onLoadSuccess(posts);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            callback.onLoadError(((HttpException) e).message());
                        } else {
                            callback.onLoadError("Some error");
                            // or we can write class where will be error messages
                            // with resources id in this case
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface LoadCallback {
        void onLoadSuccess(List<Post> posts);

        void onLoadError(String errorMessage);
    }
}
