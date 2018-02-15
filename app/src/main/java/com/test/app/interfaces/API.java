package com.test.app.interfaces;

import com.test.app.entity.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Anton on 15.02.2018.
 */

public interface API {

    @GET("posts")
    Observable<List<Post>> getPosts();

}
