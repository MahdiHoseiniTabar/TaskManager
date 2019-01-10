package com.example.caspian.taskmanager.model;

import java.util.UUID;

public class Account {
    private UUID AccountId;
    private String Username;
    private String Password;


    public Account(){
        AccountId = UUID.randomUUID();
    }

    public UUID getAccountId() {
        return AccountId;
    }



    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String pasword) {
        Password = pasword;
    }


}
