package com.example.taskapp;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tasksadapter extends RecyclerView.Adapter<Tasksadapter.Viewholder>{
    Databasehelper db;
    ArrayList<Task> data;

    Tasksadapter(ArrayList<Task> data){
        this.data = data;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        db = new Databasehelper(parent.getContext());
        return new Viewholder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Task cutodo = data.get(position);
        holder.tasks.setText(cutodo.title);
        holder.checkbox.setChecked(cutodo.ischecked);

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                int itemId = 0;
//                Cursor data = db.getId(cutodo.title);
//                while (data.moveToNext()){
//                    itemId = data.getInt(0);
//                }
                cutodo.ischecked = !cutodo.ischecked;
//                if (cutodo.ischecked){
//                    db.updatedata(cutodo.title,1,0,1);
//                } else{
//                    db.updatedata(cutodo.title,0,1,1);
//                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setData(Task taskss){
        data.add(taskss);
        notifyItemInserted(data.size()-1);
    }
    public void addAllData(ArrayList<Task> a){
        data.addAll(a);
    }
    public void removedata(){
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).ischecked){
                data.remove(i);
            }
        }
        notifyDataSetChanged();
    }


    public static class Viewholder extends RecyclerView.ViewHolder {
        CheckBox checkbox;
        TextView tasks;
        
        public Viewholder(View itemview){
            super(itemview);
            checkbox = itemview.findViewById(R.id.checkBox);
            tasks = itemview.findViewById(R.id.textView);
        }
    }
}
