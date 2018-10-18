package com.sskj.k3dlib.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.sskj.k3dlib.bean.UserData;


/**
 * Created by lv on 18-5-7.
 */
@Database(entities = {UserData.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {
    public abstract UserDao userDao();

    private static UserDataBase INSTANCE;

    public static UserDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDataBase.class, "user")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
