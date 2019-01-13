package com.example.caspian.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.caspian.taskmanager.database.TaskBaseHelper;
import com.example.caspian.taskmanager.database.TaskCursorWraper;
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
    //private static List<Task> mTaskList = new ArrayList<>();
    private static List<Task> mTaskList;
    private static List<Task> mDoneTaskList;
    private static HashMap<UUID, Task> mHashMap;
    private static SQLiteDatabase mDatabase;
    private Context mContext;
    private static TaskCursorWraper cursorWraper;


    public static TaskLab getmInstance(Context context) {
        if (mInstance == null)
            mInstance = new TaskLab(context);
        return mInstance;
    }

    private TaskLab(Context context) {
        mTaskList = new ArrayList<>();
        mDoneTaskList = new ArrayList<>();
        mHashMap = new LinkedHashMap<>();
        if (!Account.isIsGUess()) {
            mContext = context;
            mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
        }
    }

    public void addTask(Task task) {
        if (Account.isIsGUess()) {
            mTaskList.add(task);
            if (task.isDone())
                mDoneTaskList.add(task);
            mHashMap.put(task.getId(), task);
        } else {
            ContentValues values = getContentValues(task);
            mDatabase.insert(TaskDbSchema.Task.NAME, null, values);
        }

    }

    public boolean taskIsExist(Task task) {
        if (!Account.isIsGUess()) {
            Cursor cursor = mDatabase.query(TaskDbSchema.Task.NAME, null, TaskDbSchema.Task.TaskCols.TITLE + " = ? "
                            + " AND " + TaskDbSchema.Task.TaskCols.ACCOUNTID + " = ? ", new String[]{task.getTitle(), AccountLab.accountId.toString()},
                    null, null, null);
            cursorWraper = new TaskCursorWraper(cursor);
            try {
                return cursorWraper.getCount() != 0;
            } finally {
                cursorWraper.close();
            }
        } else {
            for (int i = 0; i < mTaskList.size(); i++) {
                return mTaskList.get(i).getTitle().equals(task.getTitle());
            }
        }
        return false;
    }

    private ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.Task.TaskCols.UUID, task.getId().toString());
        values.put(TaskDbSchema.Task.TaskCols.TITLE, task.getTitle());
        values.put(TaskDbSchema.Task.TaskCols.DESCRIPTION, task.getDescribtion());
        values.put(TaskDbSchema.Task.TaskCols.DATE, task.getDate().toString());
        values.put(TaskDbSchema.Task.TaskCols.ISDONE, task.isDone() ? 1 : 0);
        values.put(TaskDbSchema.Task.TaskCols.ACCOUNTID, task.getMaccountId().toString());
        return values;
    }

    public static List<Task> getTaskList() {
        if (!Account.isIsGUess()) {
            Cursor cursor = mDatabase.rawQuery("SELECT * from " + TaskDbSchema.Task.NAME + " WHERE " + TaskDbSchema.Task.TaskCols.ACCOUNTID
                    + " = ? ", new String[]{AccountLab.accountId.toString()});
            cursorWraper = new TaskCursorWraper(cursor);
            try {
                if (cursorWraper.getCount() == 0)
                    return mTaskList;
                cursorWraper.moveToFirst();
                while (!cursorWraper.isAfterLast()) {
                    mTaskList.add(cursorWraper.getTask());
                    cursorWraper.moveToNext();
                }
            } finally {
                cursorWraper.close();
            }
        }
        return mTaskList;
    }

    public static List<Task> getDoneTaskList() {
        /*mDoneTaskList = new ArrayList<>();
        for (int i = 0; i < mTaskList.size(); i++) {
            if (mTaskList.get(i).isDone())
                mDoneTaskList.add(mTaskList.get(i));
        }*/
        return mDoneTaskList;
    }

    public Task getTask(UUID id) {

        if (Account.isIsGUess()) {
            return mHashMap.get(id);
        } else {
            Cursor cursor = mDatabase.query(TaskDbSchema.Task.NAME, null, TaskDbSchema.Task.TaskCols.UUID + " = ? ",
                    new String[]{id.toString()}, null, null, null);
            TaskCursorWraper cursorWraper = new TaskCursorWraper(cursor);
            try {
                if (cursorWraper.getCount() == 0)
                    return null;
                cursorWraper.moveToFirst();
                return cursorWraper.getTask();
            } finally {
                cursorWraper.close();
            }
        }
    }

    public Task getTask(int position, List<Task> tasks) {
        return tasks.get(position);
    }

    public void deleteTask(Task task) {
        if (Account.isIsGUess()) {
            mTaskList.remove(task);
            if (task.isDone())
                mDoneTaskList.remove(task);
        } else {
            mDatabase.delete(TaskDbSchema.Task.NAME, TaskDbSchema.Task.TaskCols.UUID + " = ? "
                    , new String[]{task.getId().toString()});
        }
    }

    public void editTask(Task newTask, Task oldTask) {
        if (Account.isIsGUess()) {
            mTaskList.remove(oldTask);
            mDoneTaskList.remove(oldTask);
            mTaskList.add(newTask);
            if (newTask.isDone())
                mDoneTaskList.add(newTask);
            mHashMap.put(newTask.getId(), newTask);
        } else {
            mDatabase.update(TaskDbSchema.Task.NAME, getContentValues(newTask), TaskDbSchema.Task.TaskCols.UUID + " = ? "
                    , new String[]{oldTask.getId().toString()});
        }
    }

    public void doneTask(Task newTask, Task oldTask) {
        newTask.setTitle(oldTask.getTitle());
        newTask.setDescribtion(oldTask.getDescribtion());
        newTask.setDate(oldTask.getDate());
        newTask.setId(oldTask.getId());
        newTask.setDone(true);
    }
   /* public void doneTask(Task task) {
        for (int i = 0; i < mTaskList.size(); i++) {
            if (mTaskList.get(i) == task) {
                mTaskList.get(i).setDone(true);
                mDoneTaskList.add(mTaskList.get(i));
                break;
            }
        }

    }*/

}
