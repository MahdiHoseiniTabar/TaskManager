package com.example.caspian.taskmanager.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountLab {
    private static AccountLab mInstance;
    private static SQLiteDatabase mDatabase;

    public static AccountLab getInstance() {
        if (mInstance == null)
            mInstance = new AccountLab();
        return mInstance;
    }

    private AccountLab() {
    }
    public void addAccount(Account account){

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
