package com.sskj.k3dlib.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sskj.k3dlib.bean.UserData;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by lv on 18-5-7.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM UserData")
    LiveData<List<UserData>> getAll();


    @Insert(onConflict = REPLACE)
    void save(UserData user);


    @Update
    void update(UserData... users);

    @Insert
    void insertAll(List<UserData> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserData user);

    @Query("DELETE  FROM UserData")
    void deleteAll();
    @Delete
    void delete(UserData user);
}
