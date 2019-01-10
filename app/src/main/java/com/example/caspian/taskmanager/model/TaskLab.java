package com.example.caspian.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.caspian.taskmanager.database.TaskBaseHelper;
import com.example.caspian.taskmanager.database.TaskDbSchema;

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
    private static HashMap<UUID, Task> mHashMap = new LinkedHashMap<>();
    private SQLiteDatabase mDatabase;
    private Context mContext;
    private UUID mAccountId;


    public static TaskLab getmInstance(Context context) {
        if (mInstance == null)
            mInstance = new TaskLab(context);
        return mInstance;
    }

    private TaskLab(Context context) {

        mContext = context;
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
    }

    public void addTask(String title, String description, Date date, boolean done) {
      /*  Task task = new Task();
        task.setTitle(title);
        task.setDescribtion(description);
        task.setDate(date);
        task.setDone(done);
        mTaskList.add(task);
        if (task.isDone())
            mDoneTaskList.add(task);
        mHashMap.put(task.getId(),task);*/
        Task task = new Task();
        ContentValues values = getContentValues(task);
        mDatabase.insert(TaskDbSchema.Task.NAME, null, values);

    }

    private ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.Task.TaskCols.UUID, task.getId().toString());
        values.put(TaskDbSchema.Task.TaskCols.TITLE, task.getTitle());
        values.put(TaskDbSchema.Task.TaskCols.DESCRIPTION, task.getDescribtion());
        values.put(TaskDbSchema.Task.TaskCols.DATE, task.getDate().toString());
        values.put(TaskDbSchema.Task.TaskCols.ISDONE, task.isDone() ? 1 : 0);
        values.put(TaskDbSchema.Task.TaskCols.ACCOUNTID , task.getMaccountId().toString());
        return values;
    }

    public static List<Task> getTaskList() {
        return mTaskList;
    }

    public static List<Task> getDoneTaskList() {
        return mDoneTaskList;
    }

    public Task getTask(UUID id) {
        return mHashMap.get(id);
    }

    public Task getTask(int position, List<Task> tasks) {
        return tasks.get(position);
    }

    public void deleteTask(Task task) {
        mTaskList.remove(task);
        if (task.isDone())
            mDoneTaskList.remove(task);
    }

    public void editTask(Task newTask, Task oldTask) {
        mTaskList.remove(oldTask);
        mDoneTaskList.remove(oldTask);
        mTaskList.add(newTask);
        if (newTask.isDone())
            mDoneTaskList.add(newTask);
        mHashMap.put(newTask.getId(), newTask);
    }

    public void doneTask(Task task) {
        for (int i = 0; i < mTaskList.size(); i++) {
            if (mTaskList.get(i) == task) {
                mTaskList.get(i).setDone(true);
                mDoneTaskList.add(mTaskList.get(i));
                break;
            }
        }

    }

}
