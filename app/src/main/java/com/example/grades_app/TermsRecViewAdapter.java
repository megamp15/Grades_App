package com.example.grades_app;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TermsRecViewAdapter extends RecyclerView.Adapter<TermsRecViewAdapter.ViewHolder>{
    private View.OnClickListener onItemClickListner;
    private ArrayList <Terms> terms = new ArrayList<>();
    private Context context;

    List<TextView>viewList = new ArrayList<>();

    public TermsRecViewAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public TermsRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.terms_list_item,parent, false);
        TermsRecViewAdapter.ViewHolder holder = new TermsRecViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TermsRecViewAdapter.ViewHolder holder, final int position) {
        holder.termName.setText(terms.get(position).getTerm_name());
        if (!viewList.contains(holder.termName)) {
            viewList.add(holder.termName);
        }
        holder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for (TextView termName : viewList){
                    termName.setTextColor(Color.parseColor("#000000"));
                }
                holder.termName.setTextColor(Color.parseColor("#ff0000"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public void setClasses(ArrayList<Terms> terms) {
        this.terms = terms;
        notifyDataSetChanged();
    }

    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListner = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView termName;
        private RelativeLayout parent;
        private ImageView removeTerm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListner);
            termName = itemView.findViewById(R.id.TermNameTxtView);
            parent = itemView.findViewById(R.id.terms_recViewParent);
            removeTerm = itemView.findViewById(R.id.remove_term_btn);


        }
    }

}
