package com.example.caspian.taskmanager.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class Task {
    @Id(autoincrement = true)
    private Long id;
    @Convert(converter = UUIDConverter.class ,columnType = String.class)
    private UUID mId;
    private String mTitle;
    private String mDescribtion;
    private Long maccountId;
    private Date mDate;
    private boolean mDone;

    @ToOne(joinProperty = "maccountId")
    private Account mAccount;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;

    @Generated(hash = 2090256772)
    public Task(Long id, UUID mId, String mTitle, String mDescribtion, Long maccountId,
            Date mDate, boolean mDone) {
        this.id = id;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDescribtion = mDescribtion;
        this.maccountId = maccountId;
        this.mDate = mDate;
        this.mDone = mDone;
    }

    @Keep
    public Task() {
        mDate = new Date();
        mId = UUID.randomUUID();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getMId() {
        return this.mId;
    }

    public void setMId(UUID mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescribtion() {
        return this.mDescribtion;
    }

    public void setDescribtion(String mDescribtion) {
        this.mDescribtion = mDescribtion;
    }

    public Long getMaccountId() {
        return this.maccountId;
    }

    public void setMaccountId(Long maccountId) {
        this.maccountId = maccountId;
    }

    public Date getDate() {
        return this.mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean getMDone() {
        return this.mDone;
    }

    public void setMDone(boolean mDone) {
        this.mDone = mDone;
    }

    @Generated(hash = 962504994)
    private transient Long mAccount__resolvedKey;

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public String dateToString(){
        SimpleDateFormat df = new SimpleDateFormat("EEE , dd/MM/yyyy ");
        return df.format(mDate);
    }

    public String getMTitle() {
        return this.mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMDescribtion() {
        return this.mDescribtion;
    }

    public void setMDescribtion(String mDescribtion) {
        this.mDescribtion = mDescribtion;
    }

    public Date getMDate() {
        return this.mDate;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 623152656)
    public Account getMAccount() {
        Long __key = this.maccountId;
        if (mAccount__resolvedKey == null || !mAccount__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AccountDao targetDao = daoSession.getAccountDao();
            Account mAccountNew = targetDao.load(__key);
            synchronized (this) {
                mAccount = mAccountNew;
                mAccount__resolvedKey = __key;
            }
        }
        return mAccount;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1061706087)
    public void setMAccount(Account mAccount) {
        synchronized (this) {
            this.mAccount = mAccount;
            maccountId = mAccount == null ? null : mAccount.getId();
            mAccount__resolvedKey = maccountId;
        }
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }
}
