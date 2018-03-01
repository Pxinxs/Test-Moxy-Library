package com.test.app.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.test.app.entity.Post;
import com.test.app.interfaces.MainView;
import com.test.app.model.MainModel;

import java.util.List;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private MainModel mModel;

    public MainPresenter() {
        mModel = new MainModel();

        getPosts();
    }

    public void getPosts() {
        getViewState().showLoading();

        mModel.getPosts(new MainModel.LoadCallback() {
            @Override
            public void onLoadSuccess(List<Post> posts) {
                getViewState().hideLoading();
                getViewState().showContent(posts);
            }

            @Override
            public void onLoadError(String errorMessage) {
                getViewState().hideLoading();
                if (errorMessage != null) {
                    getViewState().showError(errorMessage);
                } else {
                    getViewState().showError("Some error");
                }
            }
        });
    }
}
