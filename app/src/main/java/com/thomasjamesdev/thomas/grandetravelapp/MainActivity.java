package com.thomasjamesdev.thomas.grandetravelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnViewAll;
    Button btnSearch;
    EditText editTextSearchField;
    TextView textViewTitle;
    TextView textViewSubTitle;
    boolean isShowingSearch = false;
    String searchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnViewAll = (Button) findViewById(R.id.btn_view_all);
        btnSearch = (Button) findViewById(R.id.btn_search);
        editTextSearchField = (EditText) findViewById(R.id.et_search_field);
        editTextSearchField.setVisibility(View.GONE);
        textViewTitle = (TextView) findViewById(R.id.tv_title);
        textViewSubTitle = (TextView) findViewById(R.id.tv_sub_title);
    }

    public void clickViewAll(View view) {

        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        startActivity(intent);

    }

    public void clickSearch(View view) {

        if (!isShowingSearch) {
            isShowingSearch = true;
            btnViewAll.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right));
            btnViewAll.setVisibility(View.GONE);
            textViewSubTitle.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right));
            textViewSubTitle.setVisibility(View.GONE);
            editTextSearchField.setVisibility(View.VISIBLE);
            editTextSearchField.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
        } else {
            searchTerm = editTextSearchField.getText().toString();


        }

    }
}
