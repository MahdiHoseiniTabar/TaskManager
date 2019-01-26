package com.example.caspian.taskmanager;

import android.app.Application;

import com.example.caspian.taskmanager.model.DaoMaster;
import com.example.caspian.taskmanager.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private static App app;
    private DaoSession mDaoSession;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MyDevOpenHelper myDevOpenHelper = new MyDevOpenHelper(this, "DatabaseName");
        Database db = myDevOpenHelper.getWritableDb();

       mDaoSession = new DaoMaster(db).newSession();

        app = this;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
