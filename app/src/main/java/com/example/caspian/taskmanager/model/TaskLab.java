package com.example.caspian.taskmanager.model;

import java.text.SimpleDateFormat;
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
    private static List<Task> mDoneTaskList = new ArrayList<>();
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
            mDoneTaskList.add(task);
        mHashMap.put(task.getId(),task);
    }

    public static List<Task> getTaskList(){
        return mTaskList;
    }

    public static List<Task> getDoneTaskList(){
        return mDoneTaskList;
    }

    public Task getTask(UUID id){
        return mHashMap.get(id);
    }
    public Task getTask(int position, List<Task> tasks){
        return tasks.get(position);
    }
    public void deleteTask(Task task){
        mTaskList.remove(task);
        if (task.isDone())
            mDoneTaskList.remove(task);
    }
    public void editTask(Task task, String title, String description, Date date, boolean done){
        mTaskList.remove(task);
        mDoneTaskList.remove(task);
        Task newtask = new Task();
        newtask.setTitle(title);
       newtask.setDescribtion(description);
        newtask.setDate(date);
        newtask.setDone(done);
        mTaskList.add(newtask);
        if (newtask.isDone())
            mDoneTaskList.add(newtask);
        mHashMap.put(newtask.getId(),newtask);
    }
    public void doneTask(Task task){
        for (int i = 0; i < mTaskList.size() ; i++) {
            if (mTaskList.get(i) == task) {
                mTaskList.get(i).setDone(true);
                mDoneTaskList.add(mTaskList.get(i));
                break;
            }
        }

    }

}
