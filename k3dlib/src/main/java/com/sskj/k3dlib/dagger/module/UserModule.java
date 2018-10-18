package com.sskj.k3dlib.dagger.module;


import com.sskj.common.base.App;
import com.sskj.k3dlib.model.room.UserDao;
import com.sskj.k3dlib.model.room.UserDao_Impl;
import com.sskj.k3dlib.model.room.UserDataBase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lv on 18-5-7.
 */


@Module
public class UserModule {
    @Provides
    @Named("UserDao")
    UserDao provideUserDao() {
        return new UserDao_Impl(UserDataBase.getInstance(App.INSTANCE));
    }
}
