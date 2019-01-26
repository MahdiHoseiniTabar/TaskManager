package com.example.caspian.taskmanager.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class TaskORM {
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
    private AccountORM mAccountORM;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1145022388)
    private transient TaskORMDao myDao;

    @Generated(hash = 752957323)
    public TaskORM(Long id, UUID mId, String mTitle, String mDescribtion,
            Long maccountId, Date mDate, boolean mDone) {
        this.id = id;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDescribtion = mDescribtion;
        this.maccountId = maccountId;
        this.mDate = mDate;
        this.mDone = mDone;
    }

    @Generated(hash = 20896722)
    public TaskORM() {
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

    public Long getMaccountId() {
        return this.maccountId;
    }

    public void setMaccountId(Long maccountId) {
        this.maccountId = maccountId;
    }

    public Date getMDate() {
        return this.mDate;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean getMDone() {
        return this.mDone;
    }

    public void setMDone(boolean mDone) {
        this.mDone = mDone;
    }

    @Generated(hash = 481006388)
    private transient Long mAccountORM__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1301010806)
    public AccountORM getMAccountORM() {
        Long __key = this.maccountId;
        if (mAccountORM__resolvedKey == null
                || !mAccountORM__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AccountORMDao targetDao = daoSession.getAccountORMDao();
            AccountORM mAccountORMNew = targetDao.load(__key);
            synchronized (this) {
                mAccountORM = mAccountORMNew;
                mAccountORM__resolvedKey = __key;
            }
        }
        return mAccountORM;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1792043731)
    public void setMAccountORM(AccountORM mAccountORM) {
        synchronized (this) {
            this.mAccountORM = mAccountORM;
            maccountId = mAccountORM == null ? null : mAccountORM.getId();
            mAccountORM__resolvedKey = maccountId;
        }
    }

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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 29723396)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskORMDao() : null;
    }
}
