package com.example.grades_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class TermsActivity extends AppCompatActivity{

    private RecyclerView terms_recView;
    private ArrayList<Terms> terms;
    private ImageView close_btn;
    Bitmap bitmap;
    Bitmap O;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

//      Recycler for Classes
        terms_recView = findViewById(R.id.terms_recView);
        terms = new ArrayList<>();
        terms.add(new Terms("Fall 2018"));
        terms.add(new Terms("Spring 2019"));
        terms.add(new Terms("Fall 2019"));
        terms.add(new Terms("Spring 2020"));
        terms.add(new Terms("Fall 2020"));


        TermsRecViewAdapter adapter = new TermsRecViewAdapter(this);
        adapter.setClasses(terms);

        terms_recView.setAdapter(adapter);
        terms_recView.setLayoutManager(new LinearLayoutManager(this));

//      Close Button
        close_btn = findViewById(R.id.close);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
            }
        });

    }
}