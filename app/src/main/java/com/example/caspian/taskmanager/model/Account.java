package com.example.caspian.taskmanager.model;

import java.util.UUID;

public class Account {
    private UUID AccountId;
    private String Username;
    private long Password;
    private UUID TaskId;

    public Account(){
        AccountId = UUID.randomUUID();
        TaskId = UUID.randomUUID();
    }

    public UUID getAccountId() {
        return AccountId;
    }

    public void setAccountId(UUID accountId) {
        AccountId = accountId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public long getPassword() {
        return Password;
    }

    public void setPassword(long pasword) {
        Password = pasword;
    }

    public UUID getTaskId() {
        return TaskId;
    }

    public void setTaskId(UUID taskId) {
        TaskId = taskId;
    }
}
