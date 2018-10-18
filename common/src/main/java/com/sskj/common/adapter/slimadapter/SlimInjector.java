package com.sskj.common.adapter.slimadapter;


import java.util.List;

/**
 * Created by linshuaibin on 01/04/2017.
 */

public interface SlimInjector<T> {
    void onInject(T data, IViewInjector injector, List<Object> payloads);

}
