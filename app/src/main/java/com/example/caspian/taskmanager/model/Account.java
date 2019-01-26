package com.example.caspian.taskmanager.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Account {
    @Id(autoincrement = true)
    private Long id;

    @Convert(converter = UUIDConverter.class,columnType = String.class)
    private UUID AccountId;

    @Unique
    private String Username;
    private String Password;
    @Generated(hash = 600756919)
    public Account(Long id, UUID AccountId, String Username, String Password) {
        this.id = id;
        this.AccountId = AccountId;
        this.Username = Username;
        this.Password = Password;
    }
    @Generated(hash = 214225843)
    public Account() {
        AccountId = UUID.randomUUID();
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UUID getAccountId() {
        return this.AccountId;
    }
    public void setAccountId(UUID AccountId) {
        this.AccountId = AccountId;
    }
    public String getUsername() {
        return this.Username;
    }
    public void setUsername(String Username) {
        this.Username = Username;
    }
    public String getPassword() {
        return this.Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
}
