package com.sskj.common.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * <pre>
 *     author : 吕志豪
 *     e-mail : 1030753080@qq.com
 *     time   : 2018/04/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class HttpData<T> implements Serializable {

    /**
     * status : 2
     * msg : 该手机号不存在
     */
    @SerializedName(value = "status",alternate = {"code"})
    public int status;
    public String msg;
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
