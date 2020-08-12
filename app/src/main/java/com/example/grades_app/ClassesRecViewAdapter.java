package com.example.grades_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassesRecViewAdapter extends RecyclerView.Adapter<ClassesRecViewAdapter.ViewHolder> {
    private ArrayList <Classes> classes = new ArrayList<>();
    private Context context;

    public ClassesRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_list_item,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.className.setText(classes.get(position).getName());
        holder.grade.setText(Float.toString(classes.get(position).getGrade())+"%");
        holder.parent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(context,classes.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView className, grade;
        private RelativeLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.classNameTxtView);
            grade = itemView.findViewById(R.id.gradeTxtView);
            parent = itemView.findViewById(R.id.classes_recViewParent);
        }
    }
}
