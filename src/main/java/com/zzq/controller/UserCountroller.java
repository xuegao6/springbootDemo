/*
 * Copyright (c) 2018, 北京中体骏彩信息技术有限公司
 * All rights reserved.
 * 本软件，包括了机密资料，是私有和保密的，属于北京中体骏彩信息
 * 技术有限公司的财产与版权所有。未经公司许可，不得对任何部分或
 * 全部文档进行复制，不得进行其他系统转存，不得以任何形式或任何
 * 方式进行传播，包括电子的、机械的、化学的、影印本、录制或者其
 * 他方式。
 */

package com.zzq.controller;

import com.zzq.model.User;
import com.zzq.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhuziqiang
 */
@RequestMapping(value = "/user")
@RestController
public class UserCountroller {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询用户信息",notes = "查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id", paramType="path", required = true,dataType = "Int")
    })
    @GetMapping(value = "/get/{userId}")
    public User getUser(@PathVariable("userId") int userId){
        return userService.getUser(userId);
    }

    @ApiOperation(value = "新增用户信息",notes = "返回是否成功")
    @PostMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return userService.findAllUser(pageNum,pageSize);
    }
}
