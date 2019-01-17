/*
 * Copyright (c) 2018, 北京中体骏彩信息技术有限公司
 * All rights reserved.
 * 本软件，包括了机密资料，是私有和保密的，属于北京中体骏彩信息
 * 技术有限公司的财产与版权所有。未经公司许可，不得对任何部分或
 * 全部文档进行复制，不得进行其他系统转存，不得以任何形式或任何
 * 方式进行传播，包括电子的、机械的、化学的、影印本、录制或者其
 * 他方式。
 */

package com.zzq.service;

import com.github.pagehelper.PageHelper;
import com.zzq.dao.UserMapper;
import com.zzq.model.User;
import com.zzq.util.DescribeException;
import com.zzq.util.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuziqiang
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int addUser(User user) {
        if (user.getPhone().length()<6){
            throw new DescribeException(ExceptionEnum.PASSWORD_SIMPLE.getMsg(),ExceptionEnum.PASSWORD_SIMPLE.getCode());
        }
        return userMapper.insertSelective(user);
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    public List<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }

    public User getUser(int userId) {
       return userMapper.selectByPrimaryKey(userId);
    }
}
