package com.sskj.sskjbase.http;

import com.sskj.libannotation.request.AutoRequestConfig;
import com.sskj.libannotation.request.AutoRequestParamConfig;


/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：每次编译都会生成，不使用，注解注释
 * 创建时间：2018-10-12 17:31
 */
//@AutoRequestConfig(methodType = "POST",//请求方式 POST GET
//        modelName = "UserBean", //创建的Bean名字
//        path = "/user/login",//网络请求url路径
//        methodName = "login",//方法名
//        methodDesc = "登录",//请求描述
//        constantName = "LOGIN",//常量名字
//        isCreateModel = true,//是否创建Bean,如果进行网络请求，按照返回json中的data，自动生成各属性
//        isNoHTTP = true,//是否进行网络请求
//        isCreatePresenter = true,//是否在Presenter中添加方法
//        presenterClassName = "LoginActivityPresenter"//Presenter名字
//)
public class LoginRequest {
    @AutoRequestParamConfig(value = "fjj23j2aa12iu22iux1jaxa3as", desc = "手机序列号")
    String machine;
    @AutoRequestParamConfig(value = "1", desc = "1 安卓 2 ios")
    String platform;
}
