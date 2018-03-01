package com.test.app.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.test.app.R;

public class DetailActivity extends MvpAppCompatActivity {

    private static final String TITLE = "title";
    private static final String DETAIL = "detail";

    private Toolbar toolbar;
    private TextView tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        tvBody = findViewById(R.id.tvBody);

        String title = getIntent().getStringExtra(TITLE);
        String detail = getIntent().getStringExtra(DETAIL);

        initToolbar(title);

        tvBody.setText(detail);
    }

    private void initToolbar(String title) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(view -> DetailActivity.super.onBackPressed());
    }
}
