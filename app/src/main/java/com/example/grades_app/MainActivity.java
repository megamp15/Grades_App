package com.example.grades_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView classes_recView;
    private Button btn_terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //      Terms
        btn_terms = findViewById(R.id.btn_terms);
        btn_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TermsActivity.class));
                overridePendingTransition(0, 0);
            }
        });

//      Recycler for Classes
        classes_recView = findViewById(R.id.classes_recView);

        ArrayList<Classes> classes = new ArrayList<>();
        classes.add(new Classes("Test Class 1", (float) 95.2));
        classes.add(new Classes("Test Class 2", (float) 87.9));
        classes.add(new Classes("Test Class 3", (float) 83.4));
        classes.add(new Classes("Test Class 4", (float) 92.9));
        classes.add(new Classes("Test Class 5", (float) 79.1));

        ClassesRecViewAdapter adapter = new ClassesRecViewAdapter(this);
        adapter.setClasses(classes);

        classes_recView.setAdapter(adapter);
        classes_recView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.classes:
                        return true;
                    case R.id.calendar:
                        startActivity(new Intent(getApplicationContext(), Calendar.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }
}