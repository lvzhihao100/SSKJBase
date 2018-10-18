package com.sskj.sskjbase.http;

import com.sskj.libannotation.base.AutoHttpConfig;


/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-12 17:30
 */
@AutoHttpConfig(baseUrl = "http://www.adqki.cn",//域名
        moduleName = "app/",//基本路径
        ftlPath = "/home/lv/AndroidStudioProjects/SSKJBase/ftl",//模板路径
        beanPath = "com.sskj.sskjbase.bean",//bean 路径
        servicePath = "com.sskj.sskjbase.http",//http 路径
        presenterPath = "com.sskj.sskjbase.presenter", //presenter 路径
        iserviceClassName = "IHttpService",//http接口名字
        serviceClassName = "HttpService",//http实现名字
        httpConfigClassName = "HttpConfig")//http配置名字
public class HttpCommon {
}
