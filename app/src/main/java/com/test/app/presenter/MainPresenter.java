package com.test.app.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.test.app.entity.Post;
import com.test.app.interfaces.MainView;
import com.test.app.model.MainModel;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private MainView mView;
    private MainModel mModel;
    private List<Post> mPosts;

    public MainPresenter() {
        // we can inject MainModel with DI
        mModel = new MainModel();
        mPosts = new ArrayList<>();
    }

    public void attach(MainView view) {
        mView = view;
        if (mPosts.size() > 0) {
            mView.showContent(mPosts);
        } else {
            getPosts();
        }
    }

    private void getPosts() {
        if (mView != null)
            mView.showLoading();

        mModel.getPosts(new MainModel.LoadCallback() {
            @Override
            public void onLoadSuccess(List<Post> posts) {
                mPosts = posts;
                if (mView != null) {
                    mView.hideLoading();
                    mView.showContent(posts);
                }
            }

            @Override
            public void onLoadError(String errorMessage) {
                if (mView != null) {
                    mView.hideLoading();
                    mView.showError(errorMessage);
                }
            }
        });
    }

    public void detach() {
        mView = null;
    }
}
