package com.sskj.sskjbase.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.k3dlib.http.JsonCallBack;
import com.sskj.sskjbase.bean.UserBean;
import com.sskj.sskjbase.http.HttpConfig;
import com.sskj.sskjbase.ui.activity.LoginActivity;


public class LoginActivityPresenter extends BasePresenter<LoginActivity> {


    /**
     * 登录
     *
     * @param machine 手机序列号
     * @param platform 1 安卓 2 ios
     * @return
     */
      public void login(String machine,String platform){
               httpService.login( machine, platform)
               .execute(new JsonCallBack<HttpData<UserBean>>() {
                       @Override
                        public void onSuccess(Response<HttpData<UserBean>> response) {
                           HttpData<UserBean> httpData = response.body();
                           if (httpData.getStatus() == HttpConfig.OK) {

                           }
               }
           });
}


}
