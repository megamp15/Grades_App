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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TermsActivity extends AppCompatActivity{
//  Static variables for saved preference of the terms data
    public static final String SHARED_PREFS_TERMS = "";
    public static final String TERMS = "";

//  Variables for View items
    private RecyclerView terms_recView;
    private ImageView close_btn;
    private TextView terms_edit_btn;
    private EditText term_add_editTxt;

//  Array list variable of terms for the recyclerview
    private ArrayList<Terms> terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

//      Recycler for Terms
        terms_recView = findViewById(R.id.terms_recView);
        loadData();

        final TermsRecViewAdapter adapter = new TermsRecViewAdapter(this);

//      Removing the terms from the recyclerview
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                terms.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
                saveData();
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(terms_recView);
        adapter.setTerms(terms);

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

//      Edit Text for Adding Term
        term_add_editTxt = findViewById(R.id.term_add_editTxt);
        term_add_editTxt.setVisibility(View.GONE);
        term_add_editTxt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == keyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    terms.add(new Terms(term_add_editTxt.getText().toString()));
                    saveData();
                    term_add_editTxt.setText("");
                    return true;
                }
                return false;
            }
        });

//      Edit Button
        terms_edit_btn = findViewById(R.id.terms_edit_btn);
        terms_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (term_add_editTxt.getVisibility() == View.GONE){
                    term_add_editTxt.setVisibility(View.VISIBLE);
                }
                else{
                    term_add_editTxt.setVisibility(View.GONE);
                }
            }
        });
    }

//  Saving the Terms Data
    protected void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_TERMS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(terms);
        editor.putString(TERMS, json);
        editor.apply();
    }

//  Loading the Terms Data. Creates a new term array if one does not exist.
    protected void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_TERMS, MODE_PRIVATE);
//        Uncomment line below to get rid of the saved terms data. For testing purposes currently.
//        sharedPreferences.edit().clear().commit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString(TERMS, null);
        Type type = new TypeToken<ArrayList<Terms>>(){}.getType();
        terms = gson.fromJson(json, type);

        if (terms == null){
            terms = new ArrayList<Terms>();
        }
    }
}