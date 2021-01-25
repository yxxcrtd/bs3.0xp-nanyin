/*
 * 文件名：ValRiftTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.batch.creditPierce.runner;

import java.util.Date;

import com.joyintech.tams.batch.creditPierce.service.CreditPierceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.invest.batch.valrift.service.ValRiftService;

/**
 *  授信估值穿透数据接入任务
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2020年12月9日
 * @see CreditPierceTask
 * @since 1.0
 */
@Service("creditPierceTask")
@Scope("prototype")
public class CreditPierceTask extends BatchBaseService{

    /**
    * 日志
    */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private CreditPierceService creditPierceService;
    
    private static Boolean isRunning = false;

    private static final Object lock1 = new Object();
    
	private Date supDate;
	
    @Override
    public JobExecution execute()
        throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        synchronized (lock1) {
            if(isRunning) {
                while (isRunning) {
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isRunning = true;
            }
            else {
                isRunning = true;
            }
        }
        try {
        	if(null!=batchExecuteInfo&&null!=batchExecuteInfo.getExecuteDate()){
        		supDate=batchExecuteInfo.getExecuteDate();
        	}
        	else{
        		supDate = new Date();
        	}
            creditPierceService.run(supDate, this.getProductIdList());
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        isRunning=false;
        return result;
    }
}
