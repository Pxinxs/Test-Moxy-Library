package com.test.app.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.test.app.entity.Post;
import com.test.app.interfaces.DetailView;

import java.util.List;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private List<Post> postList;

    public DetailPresenter(int id) {
        // TODO: where І need set postList for use it here?
        // showDetail(id);

        // TODO: stub
        Post post = new Post();
        post.body = "some body here";
        getViewState().showDetailPost(post);
    }

    public void addPosts(List<Post> posts) {
        postList = posts;
    }

    private void showDetail(int id) {
        for (Post post : postList) {
            if (post.id.equals(id)) {
                getViewState().showDetailPost(post);
            }
        }
    }
}
