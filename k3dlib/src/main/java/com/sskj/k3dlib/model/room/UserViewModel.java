package com.sskj.k3dlib.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sskj.k3dlib.bean.UserData;
import com.sskj.k3dlib.model.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by lv on 18-5-7.
 */

public class UserViewModel extends ViewModel {

    UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<List<UserData>> getUsers() {
        if (userRepository == null) {
            Log.e("---->", "isNull");
            return null;
        }
        return userRepository.getAllUser();
    }


    public void insert(UserData user) {
        if (userRepository == null) {
            Log.e("---->", "isNull");
        }
        userRepository.insert(user);
    }

    public void clear() {
        if (userRepository == null) {
            Log.e("---->", "isNull");
        }
        userRepository.clear();
    }
    public void update(UserData user) {
        if (userRepository == null) {
            Log.e("---->", "isNull");
        }
        userRepository.update(user);
    }
    public void update() {
        if (userRepository == null) {
            Log.e("---->", "isNull");
        }
        userRepository.update();
    }

}
