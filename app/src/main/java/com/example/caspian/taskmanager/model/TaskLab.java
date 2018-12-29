package com.example.caspian.taskmanager.model;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

//singleton Repository
public class TaskLab {
    private static TaskLab mInstance;
    private static List<Task> mTaskList = new ArrayList<>();
    private static List<Task> doneTasks = new ArrayList<>();
    private static HashMap<UUID,Task> mHashMap = new LinkedHashMap<>();

    public static TaskLab getmInstance() {
        if (mInstance == null)
            mInstance =  new TaskLab();
        return mInstance;
    }

    private TaskLab(){
    }

    public void addTask(String title, String description, Date date, boolean done){
        Task task = new Task();
        task.setTitle(title);
        task.setDescribtion(description);
        task.setDate(date);
        task.setDone(done);
        mTaskList.add(task);
        if (task.isDone())
            doneTasks.add(task);
        mHashMap.put(task.getId(),task);
    }

    public static List<Task> getTaskList(){
        return mTaskList;
    }

    public static List<Task> getDoneTasks(){
        return doneTasks;
    }

    public Task getTask(UUID id){
        return mHashMap.get(id);
    }
    public Task getTask(int position, List<Task> tasks){
        return tasks.get(position);
    }

}
