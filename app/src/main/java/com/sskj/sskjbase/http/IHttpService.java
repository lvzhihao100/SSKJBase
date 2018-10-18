package com.sskj.sskjbase.http;
import com.sskj.sskjbase.bean.UserBean;
import com.sskj.sskjbase.bean.UserBean;
import com.sskj.sskjbase.bean.UserBean;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.HttpData;
import com.sskj.sskjbase.bean.UserBean;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public interface IHttpService {


    /**
     * 登录
     *
     * @param machine 手机序列号
     * @param platform 1 安卓 2 ios
     * @return
     */
        PostRequest<HttpData<UserBean>> login(String machine,String platform);



}