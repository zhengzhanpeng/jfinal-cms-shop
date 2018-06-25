package com.gz.controller;

import com.gz.common.UserService;
import com.gz.common.model.User;
import com.gz.utils.Response;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created by gongzhen on 2018/6/13.
 */
public class UserController extends Controller{
    /**
     * 普通用户列表
     */
    public void getNormalUserList(){
        Page<User> userPage= UserService.getService().getPage(getParaToInt("pageNum"),getParaToInt("pageSize"),"user");
        renderJson(Response.responseJson(0,"请求成功",userPage));
    }
}
