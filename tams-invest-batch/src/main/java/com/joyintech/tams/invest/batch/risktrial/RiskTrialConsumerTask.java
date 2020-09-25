/*
 * 文件名：RiskTrialConsumerTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月04日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.batch.risktrial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.invest.common.risktrial.RiskTrialComm;
import com.joyintech.tams.invest.common.risktrial.service.TaskQueue;

/**
 * 监督指标事后监控消费任务
 * @author 裴家俊
 * @version 1.0 2018年12月04日
 * @see RiskTrialConsumerTask
 * @since 1.0
 */
@Service("riskTrialConsumerTask")
@Scope("prototype")
public class RiskTrialConsumerTask extends BatchBaseService {

	 /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private RiskTrialComm riskTrialComm;
	
	@Autowired
	private TaskQueue taskQueue;
	
	private static Boolean isRunning = false;
	private static final Object lock1 = new Object();
	
    @Override
    public JobExecution execute()
        throws BatchException {
    	JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
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
        	riskTrialComm.afterRiskTrialDeal(taskQueue);
        	result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        isRunning=false;
		return result;
	}
	
}
