package com.example.caspian.taskmanager.model;

import java.util.UUID;

public class Account {
    private UUID AccountId;
    private String Username;
    private String Password;
    private static boolean isGUess =false;


    public Account(){ this(UUID.randomUUID());
    }
    public Account(UUID id){
        AccountId = id;
    }

    public static boolean isIsGUess() {
        return isGUess;
    }

    public static void setIsGUess(boolean isGUess) {
        Account.isGUess = isGUess;
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
