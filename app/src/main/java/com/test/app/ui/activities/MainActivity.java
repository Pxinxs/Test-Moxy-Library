package com.test.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private Button btnReload;
    private TextView tvError;

    private static final String POST_ID = "id";
    private static final String TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvMain);
        progressBar = findViewById(R.id.pBar);
        btnReload = findViewById(R.id.btnReload);
        tvError = findViewById(R.id.tvError);

        btnReload.setOnClickListener(view -> presenter.getPosts());

        adapter = new MainAdapter(this);
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
        btnReload.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(errorMessage);
    }

    @Override
    public void showLoading() {
        btnReload.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        tvError.setVisibility(View.GONE);
        btnReload.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCardClick(int id, String title) {
        startActivity(new Intent(this, DetailActivity.class)
                .putExtra(POST_ID, id)
                .putExtra(TITLE, title));
    }
}
