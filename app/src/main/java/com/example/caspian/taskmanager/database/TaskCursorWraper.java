package com.example.caspian.taskmanager.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.caspian.taskmanager.model.Account;
import com.example.caspian.taskmanager.model.Task;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWraper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TaskCursorWraper(Cursor cursor) {
        super(cursor);
    }
    public Account getAccount(){
        UUID id = UUID.fromString(getString(getColumnIndex(TaskDbSchema.Account.AccountCols.UUID)));
        String username = getString(getColumnIndex(TaskDbSchema.Account.AccountCols.USERNAME));
        String password = getString(getColumnIndex(TaskDbSchema.Account.AccountCols.PASSWORD));
        Account account = new Account(id);
        account.setUsername(username);
        account.setPassword(password);
        return account;
    }
    public Task getTask(){
        UUID id = UUID.fromString(getString(getColumnIndex(TaskDbSchema.Task.TaskCols.UUID)));
        String title = getString(getColumnIndex(TaskDbSchema.Task.TaskCols.TITLE));
        String description = getString(getColumnIndex(TaskDbSchema.Task.TaskCols.DESCRIPTION));
        Date date =new Date(getLong(getColumnIndex(TaskDbSchema.Task.TaskCols.DATE)));
        boolean isDone = getInt(getColumnIndex(TaskDbSchema.Task.TaskCols.ISDONE)) != 0;
        Task task = new Task(id);
        task.setTitle(title);
        task.setDone(isDone);
        task.setDescribtion(description);
        task.setDate(date);
        return task;
    }
}
