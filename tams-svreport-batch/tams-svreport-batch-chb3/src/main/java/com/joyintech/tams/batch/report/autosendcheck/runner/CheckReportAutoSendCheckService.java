/*
 * 文件名：CheckReportAutoSendCheckService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.autosendcheck.runner;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.tams.batch.report.autosendcheck.service.CheckReportAutoSendCheckMainService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;

/**
 * 报送前检查自动检查
 * @author zuoyinyi
 * @version 1.0 2018年7月23日
 * @see CheckReportAutoSendCheckService
 * @since 1.0
 */
@Service(value = "checkReportAutoSendCheckService")
@Scope("prototype")
public class CheckReportAutoSendCheckService extends BatchBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 投资者持有信息数据自动检查报送 业务service
     */
    @Autowired
    private CheckReportAutoSendCheckMainService mainService;

    @Override
    public JobExecution execute() {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        Date executeDate = batchExecuteInfo.getExecuteDate();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }

        try {
            mainService.autoSendCB(executeDate);
        }
        catch (Exception e) {
            logger.error("报送前检查自动检查Error",e);
        }
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }
}
