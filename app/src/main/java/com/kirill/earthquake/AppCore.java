package com.kirill.earthquake;

import android.app.Application;

import com.kirill.earthquake.mvp.models.DaoMaster;
import com.kirill.earthquake.mvp.models.DaoSession;

import org.greenrobot.greendao.database.Database;

public class AppCore extends Application {
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "earthquakes-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
