package com.example.caspian.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.caspian.taskmanager.database.TaskBaseHelper;
import com.example.caspian.taskmanager.database.TaskDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountLab {
    private static AccountLab mInstance;
    private static SQLiteDatabase mDatabase;
    private Context mContext;

    public static AccountLab getInstance(Context context) {
        if (mInstance == null)
            mInstance = new AccountLab(context);
        return mInstance;
    }

    private AccountLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();

    }
    public void addAccount(Account account){
        ContentValues values = getContentValue(account);
        mDatabase.insert(TaskDbSchema.Account.NAME, null, values);
    }

    private ContentValues getContentValue(Account account) {
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.Account.AccountCols.UUID , account.getAccountId().toString());
        values.put(TaskDbSchema.Account.AccountCols.USERNAME , account.getUsername());
        values.put(TaskDbSchema.Account.AccountCols.PASSWORD, account.getPassword());
        return values;
    }

    public Account getAccount(UUID id){
        return new Account();
    }
    public List<Account> getAccounts(){
        return new ArrayList<>();
    }
    public void removeAccount(Account account){

    }
    public void updateAccount(Account account){

    }
}
