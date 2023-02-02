package com.example.taskapp;

import static com.example.taskapp.R.id.edittext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList <Task> n = new ArrayList<>();
    private Button addtask,removetask;
    private RecyclerView recyclerview;
    public static final String TAG = "ListDataActivity";
    EditText editText;
    Databasehelper mDatabasehelper;
    Tasksadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabasehelper = new Databasehelper(this);
        editText = findViewById(edittext);
        addtask = findViewById(R.id.button);
        recyclerview =findViewById(R.id.recyclerView2);
        removetask = findViewById(R.id.removetask);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Tasksadapter(n);
        recyclerview.setAdapter(adapter);
        populateView();
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String abc = editText.getText().toString();
                adapter.setData(new Task(abc,false));
                AddData(abc,false);
                editText.setText("");
            }
        });
        removetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.removedata();
            }
        });


    }
    public void AddData(String newentry, boolean a){
        int ischeck = 0;
        if (a){
            ischeck = 1;
        }
        boolean insertdata = mDatabasehelper.addData(newentry,ischeck);
    }
    private void populateView(){
        Log.d(TAG,"populateRecyclerView: Displaying data in the RecyclerView");
        Cursor data = mDatabasehelper.getData();
        ArrayList<Task> listdata = new ArrayList<>();
        while (data.moveToNext()){
//            adapter.setData(new Task(data.getString(1),false));
            listdata.add(new Task(data.getString(1),data.getInt(2)!=0));

//            listdata.add(data.getString(1));
//            adapter.setData(data.getString(1));
        }
        adapter.addAllData(listdata);
    }
}