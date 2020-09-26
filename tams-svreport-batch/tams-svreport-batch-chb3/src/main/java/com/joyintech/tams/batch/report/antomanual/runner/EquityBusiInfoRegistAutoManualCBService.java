/*
 * 文件名：EquityInfoAutoSendCBService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2017年12月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.antomanual.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.report.antomanual.service.EquityBusiInfoRegistAutoManualCBMainService;

/**
 * 投资者明细信息登记自动手工处理
 *
 * @author yuluan
 * @version 1.0 2019年11月15日
 * @see EquityBusiInfoRegistAutoManualCBService
 * @since 1.0
 */
@Service(value = "equityBusiInfoRegistAutoManualCBService")
@Scope("prototype")
public class EquityBusiInfoRegistAutoManualCBService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 投资者身份信息数据自动检查报送 业务service
     */
    @Autowired
    private EquityBusiInfoRegistAutoManualCBMainService mainService;

    @Override
    public JobExecution execute() {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }


//        try {
            mainService.autoManualCB(batchExecuteInfo);
//        }
//        catch (Exception e) {
//            logger.error(e.getMessage());
//        }
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }
}
