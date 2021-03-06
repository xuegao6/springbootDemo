package com.zzq.service;

import com.zzq.dao.UserMapper;
import com.zzq.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务例子
 */
@Component
public class QuartzService {

    private static Logger logger = LoggerFactory.getLogger(QuartzService.class);

    @Autowired
    private UserMapper userMapper;

//    每分钟启动
    @Scheduled(cron = "0 0/1 * * * ?")
    public void timerToNow(){

        logger.info("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }
//    项目运行后的每5秒一次
    @Scheduled(fixedRate = 5000)
    public void timerToZZP(){
        User user = userMapper.selectByPrimaryKey(1);
        logger.info("用户信息:"+user);

    }

    //    项目运行后的每5秒一次
    @Scheduled(fixedRate = 20000)
    public void timerToInsert(){
        User user = new User();
        user.setPassword("88888888");
        user.setUserName("yanghao");
        user.setPhone("789456123");
        int i = userMapper.insertSelective(user);
        logger.info(""+i);

    }
//    项目启动后每50秒启动
    @Scheduled(fixedDelay = 50000)
    public void timerToReportCount(){
        for (int i = 0; i < 10; i++){
            logger.info("<================its" + i + "count===============>" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }
//    第一次启动在项目启动后延迟50秒开始，之后每6秒执行一次
    @Scheduled(initialDelay = 50000,fixedRate = 6000)
    public void timerToReport(){
        for (int i = 0; i < 10; i++){
            logger.info("<================delay :" + i + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "count===============>");
        }
    }
}
