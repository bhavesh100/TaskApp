package com.example.taskapp;

public class Task {
    static int id = 0;
    String title;
    Boolean ischecked;
    Task(String task, Boolean isChecked){
        this.title = task;
        this.ischecked = isChecked;
        id += 1;
    }

}
