package com.test.app.interfaces;

import com.arellomobile.mvp.MvpView;
import com.test.app.entity.Post;

import java.util.List;

public interface MainView extends MvpView {
    void showContent(List<Post> posts);
    void showError(String errorMessage);
    void showLoading();
    void hideLoading();
}
