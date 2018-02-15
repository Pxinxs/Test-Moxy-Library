package com.test.app.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.test.app.R;
import com.test.app.entity.Post;
import com.test.app.interfaces.DetailView;
import com.test.app.presenter.DetailPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    private static final String POST_ID = "id";
    private static final String TITLE = "title";

    @InjectPresenter
    DetailPresenter presenter;

    @ProvidePresenter
    DetailPresenter provideDetailPresenter() {
        return new DetailPresenter(getIntent().getIntExtra(POST_ID, -1));
    }

    private TextView tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        tvBody = findViewById(R.id.tvBody);

        String title = getIntent().getStringExtra(TITLE);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(view -> DetailActivity.super.onBackPressed());

    }

    @Override
    public void showDetailPost(Post post) {
        tvBody.setText(post.body);
    }
}
