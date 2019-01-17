package com.example.caspian.taskmanager.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTitle;
    private String mDescribtion;
    private Integer maccountId;
    private Date mDate;
    private boolean mDone;

    public Task(){
        this(UUID.randomUUID());
    }
    public Task(UUID id){
        mId = id;
        mDate = new Date();
        this.maccountId = AccountLab.accountId;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescribtion() {
        return mDescribtion;
    }

    public void setDescribtion(String describtion) {
        mDescribtion = describtion;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }
    public String dateToString(){
        SimpleDateFormat df = new SimpleDateFormat("EEE , dd/MM/yyyy , HH:mm:ss a");
        return df.format(mDate);
    }

    public Integer getMaccountId() {
        return maccountId;
    }

}
