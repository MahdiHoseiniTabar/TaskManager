package com.example.caspian.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.caspian.taskmanager.App;
import com.example.caspian.taskmanager.database.TaskBaseHelper;
import com.example.caspian.taskmanager.database.TaskCursorWraper;
import com.example.caspian.taskmanager.database.TaskDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.UUID;

//singleton Repository
public class TaskLab {
    private static TaskLab mInstance;
    private static List<Task> mTaskList;
    private static List<Task> mDoneTaskList;
    private static SQLiteDatabase mDatabase;
    private static Context mContext;
    private static TaskCursorWraper cursorWraper;
    private static TaskDao taskDao;


    public static TaskLab getmInstance(Context context) {
        if (mInstance == null)
            mInstance = new TaskLab(context);
        return mInstance;
    }

    private TaskLab(Context context) {
        mContext = context;
        /* mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();*/
        DaoSession daoSession = (App.getApp()).getDaoSession();
        taskDao = daoSession.getTaskDao();
    }

    public static List<Task> getmTaskList() {
        return mTaskList;
    }

    public void addTask(Task task) {
      /*  Task task = new Task();
        task.setTitle(title);
        task.setDescribtion(description);
        task.setDate(date);
        task.setDone(done);
        mTaskList.add(task);
        if (task.isDone())
            mDoneTaskList.add(task);
        mHashMap.put(task.getId(),task);*/
        /*ContentValues values = getContentValues(task);
        mDatabase.insert(TaskDbSchema.Task.NAME, null, values);*/
        taskDao.insert(task);
        Log.i("+++", "addTask: " + task.getMTitle());
    }


    private ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.Task.TaskCols.UUID, task.getId().toString());
        values.put(TaskDbSchema.Task.TaskCols.TITLE, task.getMTitle());
        values.put(TaskDbSchema.Task.TaskCols.DESCRIPTION, task.getMDescribtion());
        values.put(TaskDbSchema.Task.TaskCols.DATE, task.getMDate().getTime());
        values.put(TaskDbSchema.Task.TaskCols.ISDONE, task.getMDone() ? 1 : 0);
        values.put(TaskDbSchema.Task.TaskCols.ACCOUNTID, task.getMaccountId().toString());
        return values;
    }

    public static List<Task> getTaskList() {
        mTaskList = new ArrayList<>();

        /*Cursor cursor = mDatabase.rawQuery("SELECT * from " + TaskDbSchema.Task.NAME + " WHERE " + TaskDbSchema.Task.TaskCols.ACCOUNTID
                + " = ? ", new String[]{AccountLab.accountId.toString()});
        cursorWraper = new TaskCursorWraper(cursor);
        try {
            if (cursorWraper.getCount() == 0)
                return getmTaskList();
            cursorWraper.moveToFirst();
            while (!cursorWraper.isAfterLast()) {
                getmTaskList().add(cursorWraper.getTask());
                cursorWraper.moveToNext();
            }
        } finally {
            cursorWraper.close();
        }
        return getmTaskList();*/
        mTaskList = taskDao.queryBuilder().where(TaskDao.Properties.MaccountId.eq(AccountLab.accountId)).list();
        return mTaskList;
    }

    public static List<Task> getDoneTaskList() {
        mDoneTaskList = new ArrayList<>();
        for (int i = 0; i < getmTaskList().size(); i++) {
            if (getmTaskList().get(i).getMDone())
                mDoneTaskList.add(getmTaskList().get(i));
        }
        return mDoneTaskList;
    }

    public Task getTask(UUID id) {
        /*Cursor cursor = mDatabase.query(TaskDbSchema.Task.NAME, null, TaskDbSchema.Task.TaskCols.UUID + " = ? ",
                new String[]{id.toString()}, null, null, null);
        TaskCursorWraper cursorWraper = new TaskCursorWraper(cursor);
        try {
            if (cursorWraper.getCount() == 0)
                return null;
            cursorWraper.moveToFirst();
            return cursorWraper.getTask();
        } finally {
            cursorWraper.close();
        }*/
        return taskDao.queryBuilder().where(TaskDao.Properties.MId.eq(id)).unique();
    }

    public Task getTask(int position, List<Task> tasks) {
        return tasks.get(position);
    }

    public void deleteTask(Task task) {
        /*mTaskList.remove(task);
        if (task.isDone())
            mDoneTaskList.remove(task);*/
        /*mDatabase.delete(TaskDbSchema.Task.NAME, TaskDbSchema.Task.TaskCols.UUID + " = ? "
                , new String[]{task.getId().toString()});*/
        taskDao.delete(task);
    }

    public void deleteAllTask() {
        //  mDatabase.delete(TaskDbSchema.Task.NAME, TaskDbSchema.Task.TaskCols.ACCOUNTID + " = ? ", new String[]{AccountLab.accountId.toString()});
        for (int i = 0; i < mTaskList.size(); i++) {
            deleteTask(mTaskList.get(i));
        }
    }

    public void editTask(Task newTask, Task oldTask) {
       /* mTaskList.remove(oldTask);
        mDoneTaskList.remove(oldTask);
       mTaskList.add(newTask);
        if (newTask.isDone())
            mDoneTaskList.add(newTask);
        mHashMap.put(newTask.getId(), newTask);*/
       /* mDatabase.update(TaskDbSchema.Task.NAME, getContentValues(newTask), TaskDbSchema.Task.TaskCols.UUID + " = ? "
                , new String[]{oldTask.getId().toString()});*/
        oldTask.setMTitle(newTask.getMTitle());
        oldTask.setMDescribtion(newTask.getMDescribtion());
        oldTask.setMDate(newTask.getMDate());
        oldTask.setMDone(newTask.getMDone());
        taskDao.update(oldTask);
    }

    public List<Task> searchTask(String search) {
        List<Task> taskList = new ArrayList<>();
       /* Cursor cursor = mDatabase.query(TaskDbSchema.Task.NAME, null, TaskDbSchema.Task.TaskCols.ACCOUNTID +
                        " = ? " + " AND " + TaskDbSchema.Task.TaskCols.TITLE + " = ? "
                        + " OR " + TaskDbSchema.Task.TaskCols.DESCRIPTION + " = ? ", new String[]{AccountLab.accountId.toString(),search,search},
                null,
                null,
                null);
        cursorWraper = new TaskCursorWraper(cursor);
        try {
            if (cursorWraper.getCount() == 0)
                return taskList;
            cursorWraper.moveToFirst();
            while (!cursorWraper.isAfterLast()) {
                taskList.add(cursorWraper.getTask());
                cursorWraper.moveToNext();
            }
        } finally {
            cursorWraper.close();
        }*/
        taskList = taskDao.queryBuilder().where(TaskDao.Properties.MaccountId.eq(AccountLab.accountId))
                .whereOr(TaskDao.Properties.MDescribtion.like("%" + search + "%")
                        , TaskDao.Properties.MTitle.like("%" + search + "%")).list();
        return taskList;

    }

    public File getPhotoFile(Task task) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, "IMG_" + task.getMId() + ".JPG");
    }


}
