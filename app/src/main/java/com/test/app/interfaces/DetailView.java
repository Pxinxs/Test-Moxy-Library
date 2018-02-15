package com.test.app.interfaces;

import com.arellomobile.mvp.MvpView;
import com.test.app.entity.Post;

public interface DetailView extends MvpView {
    void showDetailPost(Post post);
}
