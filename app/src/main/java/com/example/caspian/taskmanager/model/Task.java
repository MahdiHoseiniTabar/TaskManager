package com.example.caspian.taskmanager.model;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTitle;
    private String mDescribtion;
    private char mIconName;
    private Date mDate;
    private boolean mDone;

    public Task(){
        mId = UUID.randomUUID();
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

    public char getIconName() {
        return mIconName;
    }

    public void setIconName(char iconName) {
        mIconName = iconName;
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
}
