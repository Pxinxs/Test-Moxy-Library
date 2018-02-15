package com.test.app.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.test.app.R;

public class DetailActivity extends AppCompatActivity {

    private static final String TITLE = "title";
    private static final String BODY = "body";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tvBody = findViewById(R.id.tvBody);

        String title = getIntent().getStringExtra(TITLE);
        String body = getIntent().getStringExtra(BODY);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.super.onBackPressed();
            }
        });

        tvBody.setText(body);
    }
}
