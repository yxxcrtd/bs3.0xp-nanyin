/*
 * 文件名：ReportStartupListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月24日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.system.config.StartupConfiguration;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.batchUtil.server.BatchUtilServer;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.dao.RepTaskDeatilMapper;


/**
 * 系统启动完成监听
 * 系统启动完成后，可在afterStartup()中启动后续必要的程序
 * @author ZhangYangFei
 * @version 1.0 2018年12月24日
 * @see ReportStartupListener
 * @since 1.0
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class BathchStartListener extends StartupConfiguration {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(BathchStartListener.class);
    
    @Autowired
    private RepTaskDeatilMapper repTaskDeatilMapper;
   
    @Autowired
    private BatchUtilServer batchUtilServer;
    
    @Override
    public void afterStartup(ApplicationContext applicationContext) {
        logger.info("系统启动完成，开始启动后续程序...");
        
        // 重置作业定时扫描功能并启动定时任务
        initQuartz();
    }
    
    /**
     * 重置作业定时扫描功能并启动定时任务
     * 
     * @see
     * @since 1.0
     */
    private void initQuartz(){
        logger.info("系统已经重启,重置作业定时扫描功能并启动定时任务 ... 开始");
        repTaskDeatilMapper.initRepTaskDetail();
        
        Thread socketThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*30);
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                	logger.error("error:", e);
                }
                logger.info("进入线程：重启定时扫描功能");
                batchUtilServer.execBatchRefresh();
            }
        });
        socketThread.start();
        logger.info("系统已经重启,重置作业定时扫描功能并启动定时任务...结束");

    }
    
    
}
