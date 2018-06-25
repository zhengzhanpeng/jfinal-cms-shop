package com.gz.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * Created by gongzhen on 2018/6/4.
 */
public class AdminInterceptor implements Interceptor{
    @Override
    public void intercept(Invocation inv) {
        Controller controller=inv.getController();
        String token=controller.getRequest().getHeader("Authorization");
        System.out.println(token);

    }
}
