package com.example.grades_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView classes_recView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classes_recView = findViewById(R.id.classes_recView);

        /*ArrayList<Classes> classes = new ArrayList<>();
        classes.add(new Classes("Test Class 1", (float) 95.2));
        classes.add(new Classes("Test Class 2", (float) 87.9));
        classes.add(new Classes("Test Class 3", (float) 83.4));
        classes.add(new Classes("Test Class 4", (float) 92.9));
        classes.add(new Classes("Test Class 5", (float) 79.1));

        ClassesRecViewAdapter adapter = new ClassesRecViewAdapter(this);
        adapter.setClasses(classes);

        classes_recView.setAdapter(adapter);*/
        classes_recView.setLayoutManager(new LinearLayoutManager(this));

    }
}