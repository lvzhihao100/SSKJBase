package com.sskj.k3dlib.bean;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.sskj.common.util.NumberUtil;

/**
 * Created by lv on 18-5-7.
 */
@Entity(primaryKeys = {"id"})
public class UserData {
    @NonNull
    private String id;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}
