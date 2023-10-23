package com.gz.controller;

import com.gz.common.UserService;
import com.gz.common.model.User;
import com.gz.utils.Response;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created by gongzhen on 2018/6/13.
 */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {
    /**
     * 普通用户列表
     */
    public ResponseEntity<Map<String, Object>> getNormalUserList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        Page<User> userPage= UserService.getService().getPage(pageNum, pageSize, "user");
        return new ResponseEntity<>(Response.responseJson(0,"请求成功",userPage), HttpStatus.OK);
    }
}
