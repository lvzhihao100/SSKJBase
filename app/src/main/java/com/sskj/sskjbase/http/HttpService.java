package com.sskj.sskjbase.http;
import com.sskj.sskjbase.bean.UserBean;
import com.sskj.sskjbase.bean.UserBean;
import com.sskj.sskjbase.bean.UserBean;
import com.sskj.sskjbase.bean.UserBean;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.HttpData;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService implements IHttpService {



    /**
     * 登录
     *
     * @param machine 手机序列号
     * @param platform 1 安卓 2 ios
     * @return
     */
    @Override
        public 
        PostRequest<HttpData<UserBean>> login(String machine,String platform){
        return OkGo.<HttpData<UserBean>>post(HttpConfig.BASE_URL + HttpConfig.LOGIN)
                .params("machine", machine)
                .params("platform", platform);
    }


}