package com.sskj.k3dlib.model.repository;


import android.arch.lifecycle.LiveData;

import com.sskj.k3dlib.bean.UserData;
import com.sskj.k3dlib.model.room.UserDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by lv on 18-5-7.
 */
//@Singleton
public class UserRepository {

    public UserDao userDao;
    @Inject
    public UserRepository(@Named("UserDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public LiveData<List<UserData>> getAllUser() {
        return userDao.getAll();
    }

    public void updateUser() {
        //todo 获取用户信息，存储数据库

    }

    public void insert(UserData user) {
        Schedulers.newThread().scheduleDirect(() -> userDao.insert(user));
    }

    public void clear() {
        Schedulers.newThread().scheduleDirect(() -> userDao.deleteAll());
    }

    public void update(UserData user) {
        Schedulers.newThread().scheduleDirect(() -> userDao.update(user));
    }

    public void update() {
        updateUser();
    }
}
