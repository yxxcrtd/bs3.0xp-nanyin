/*
 * 文件名：InfoDiscAutoConfirmMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.doReady.server;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.disclosure.informationdisclourse.doReady.dao.DisDoReadyMapper;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;

/**
 * 生成确认标识批处理
 * @author wangmin
 * @version 1.0 2018年12月8日
 * @see DisDoReadyServer
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisDoReadyServer extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * mapper
     */
    @Autowired
    private DisDoReadyMapper disDoReadyMapper;
    
    /**
	/**
     * 自动确认
     * @param executeDate 
     *
     * @see
     * @since 1.0
     */
    public void generateData(Date executeDateStr) {
    	setBatchBeanName(BatchBeanName.生成确认标识批处理);

    	logInfoBatchStart();
    	try{
    		disDoReadyMapper.insertReadyByInterf(DateUtil.format(executeDateStr, "yyyyMMdd"));
        	disDoReadyMapper.updateReadybyAdapter(DateUtil.format(executeDateStr, "yyyyMMdd"));
    		logger.info("确认标识生成完成");
    	}
    	catch(Exception e){
//    		e.printStackTrace();
    		logger.error("error:", e);
    		logger.info("确认标识生成失败"+DateUtil.getCurrentDateTime());
    	}
    	logInfoBatchEnd();
    }
}
