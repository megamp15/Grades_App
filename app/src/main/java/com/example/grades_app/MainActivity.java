package com.example.grades_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//  Static variables for saved preference of the Classes data
    public static final String SHARED_PREFS_CLASS = "";
    public static final String CLASS = "";


    private RecyclerView classes_recView;
    private Button btn_terms;
    private Button btn_edit;
    private EditText classes_add_editTxt;

    //  Array list variable of classes for the recyclerview
    private ArrayList<Classes> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Terms btn
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
        loadData();

        final ClassesRecViewAdapter adapter = new ClassesRecViewAdapter(this);
        //      Removing the classes from the recyclerview
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                classes.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
                saveData();
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(classes_recView);

        adapter.setClasses(classes);

        classes_recView.setAdapter(adapter);
        classes_recView.setLayoutManager(new LinearLayoutManager(this));

//      Edit Text for Adding Classes
        classes_add_editTxt = findViewById(R.id.classes_add_editTxt);
        classes_add_editTxt.setVisibility(View.GONE);
        classes_add_editTxt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == keyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    classes.add(new Classes(classes_add_editTxt.getText().toString(), (float) 100));
                    saveData();
                    classes_add_editTxt.setText("");
                    return true;
                }
                return false;
            }
        });

//      Edit Button
        btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (classes_add_editTxt.getVisibility() == View.GONE){
                    classes_add_editTxt.setVisibility(View.VISIBLE);
                }
                else{
                    classes_add_editTxt.setVisibility(View.GONE);
                }
            }
        });
//      NAVIGATION
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


    //  Saving the Terms Data
    protected void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_CLASS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(classes);
        editor.putString(CLASS, json);
        editor.apply();
    }

    //  Loading the Terms Data. Creates a new term array if one does not exist.
    protected void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_CLASS, MODE_PRIVATE);
//        Uncomment line below to get rid of the saved classes data. For testing purposes currently.
//        sharedPreferences.edit().clear().commit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CLASS, null);
        Type type = new TypeToken<ArrayList<Classes>>(){}.getType();
        classes = gson.fromJson(json, type);

        if (classes == null){
            classes = new ArrayList<Classes>();
        }
    }
}