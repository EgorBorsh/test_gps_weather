package com.smollvile.gpsweather;


import android.app.Application;

import com.smollvile.gpsweather.dao.DaoMaster;
import com.smollvile.gpsweather.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "library_book_bd");
        Database database = helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
