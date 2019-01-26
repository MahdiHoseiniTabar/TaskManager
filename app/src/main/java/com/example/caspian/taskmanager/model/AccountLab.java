package com.example.caspian.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.caspian.taskmanager.App;
import com.example.caspian.taskmanager.database.TaskCursorWraper;
import com.example.caspian.taskmanager.database.TaskDbSchema;

import java.util.UUID;

public class AccountLab {
    private static AccountLab mInstance;
    private static SQLiteDatabase mDatabase;
    private Context mContext;
    private static TaskCursorWraper cursorWraper;
    public static Long accountId;
    private AccountDao accountDao;
    private static boolean isGuest =false;

    public static AccountLab getInstance(Context context) {
        if (mInstance == null)
            mInstance = new AccountLab(context);
        return mInstance;
    }

    private AccountLab(Context context) {
        /*mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();*/
        DaoSession daoSession = (App.getApp()).getDaoSession();
        accountDao = daoSession.getAccountDao();

    }
    public boolean accountIsExist(Account account){
      /*  Cursor cursor = mDatabase.query(TaskDbSchema.Account.NAME, null, TaskDbSchema.Account.AccountCols.USERNAME +
        " = ? ",new String[]{account.getUsername()}, null,null,null);
        cursorWraper = new TaskCursorWraper(cursor);
        try {
            return cursorWraper.getCount() != 0;
        }finally {
            cursorWraper.close();
        }*/
      Long number = accountDao.queryBuilder().where(AccountDao.Properties.Username.eq(account.getUsername())).count();
      return number != 0;
    }

    public void addAccount(Account account) {
        /*ContentValues values = getContentValue(account);
        mDatabase.insert(TaskDbSchema.Account.NAME, null, values);*/

        accountDao.insert(account);
    }

    public void setAccountId(Account account) {
      /*  Cursor cursor = mDatabase.query(TaskDbSchema.Account.NAME, new String[]{"_id"},TaskDbSchema.Account.AccountCols.USERNAME + " = ? ",
                new String[]{account.getUsername()},null, null, null);
        cursor.moveToFirst();
        accountId = cursor.getInt(cursor.getColumnIndex("_id"));
        cursor.close();*/
        accountId = accountDao.queryBuilder().where(AccountDao.Properties.Username.eq(account.getUsername())).unique().getId();
        Log.i("accid", "setAccountId: "+accountId);
    }


    public Account getAccount(Account account) {
        /*Cursor cursor = mDatabase.query(TaskDbSchema.Account.NAME, null, TaskDbSchema.Account.AccountCols.USERNAME + " = ? "
                        + " AND  " + TaskDbSchema.Account.AccountCols.PASSWORD + " = ? "
                , new String[]{account.getUsername(), account.getPassword()}, null, null, null);
        cursorWraper = new TaskCursorWraper(cursor);
        try {
            if (cursorWraper.getCount() == 0)
                return null;
            cursorWraper.moveToFirst();
            return cursorWraper.getAccount();
        } finally {
            cursorWraper.close();
        }*/
       return accountDao.queryBuilder().where(AccountDao.Properties.Username.eq(account.getUsername()),AccountDao.Properties.Password.eq(account.getPassword())).unique();
    }


    public void removeAccount(UUID id) {
        /*mDatabase.delete(TaskDbSchema.Account.NAME, TaskDbSchema.Account.AccountCols.UUID + " =  ? "
        ,new String[]{id.toString()});*/
        Account account = accountDao.queryBuilder().where(AccountDao.Properties.AccountId.eq(id)).unique();
        accountDao.delete(account);

    }

    public void updateAccount(Account newAccount,UUID oldAccountId) {
        /*mDatabase.update(TaskDbSchema.Account.NAME,getContentValue(newAccount),TaskDbSchema.Account.AccountCols.UUID
        + "= ? ",new String[]{oldAccountId.toString()});*/
        Account oldAccount = accountDao.queryBuilder().where(AccountDao.Properties.AccountId.eq(oldAccountId)).unique();
        oldAccount.setUsername(newAccount.getUsername());
        oldAccount.setPassword(newAccount.getPassword());
        accountDao.update(oldAccount);
    }

    private ContentValues getContentValue(Account account) {
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.Account.AccountCols.UUID, account.getAccountId().toString());
        values.put(TaskDbSchema.Account.AccountCols.USERNAME, account.getUsername());
        values.put(TaskDbSchema.Account.AccountCols.PASSWORD, account.getPassword());
        return values;
    }
    public static boolean isIsGUess() {
        return isGuest;
    }

    public static void setIsGUess(boolean isGUess) {
        AccountLab.isGuest = isGUess;
    }
}
