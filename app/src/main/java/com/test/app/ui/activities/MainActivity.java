package com.test.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.test.app.R;
import com.test.app.entity.Post;
import com.test.app.interfaces.MainView;
import com.test.app.presenter.MainPresenter;
import com.test.app.ui.adapters.MainAdapter;

import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements MainView, MainAdapter.CallbackItem {

    @InjectPresenter
    MainPresenter presenter;

    private MainAdapter adapter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private static final String TITLE = "title";
    private static final String BODY = "body";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvMain);
        progressBar = findViewById(R.id.pBar);

        adapter = new MainAdapter(this);

        presenter.attach(this);
    }

    @Override
    public void showContent(List<Post> posts) {
        adapter.addItems(posts);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCardClick(String title, String body) {
        startActivity(new Intent(this, DetailActivity.class)
                .putExtra(TITLE, title)
                .putExtra(BODY, body));
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }
}
