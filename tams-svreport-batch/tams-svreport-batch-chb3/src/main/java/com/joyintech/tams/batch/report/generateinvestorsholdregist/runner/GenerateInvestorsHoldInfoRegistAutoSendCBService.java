/*
 * 文件名：GetInvestorsHoldInfoRegistAutoSendCBService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.generateinvestorsholdregist.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.tams.batch.report.generateinvestorsholdregist.service.GenerateInvestorsHoldInfoRegistAutoSendCBMainService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;

/**
 * 投资者持有信息登记数据自动生成批处理
 * @author zuoyinyi
 * @version 1.0 2018年5月21日
 * @see GenerateInvestorsHoldInfoRegistAutoSendCBService
 * @since 1.0
 */
@Service(value = "generateInvestorsHoldInfoRegistAutoSendCBService")
@Scope("prototype")
public class GenerateInvestorsHoldInfoRegistAutoSendCBService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 投资者身份信息数据自动检查报送 业务service
     */
    @Autowired
    private GenerateInvestorsHoldInfoRegistAutoSendCBMainService mainService;

    @Override
    public JobExecution execute() {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }

        try {
            mainService.generateRegistData(DateUtil.format(batchExecuteInfo.getExecuteDate()));
        }
        catch (Exception e) {
            logger.error("投资者持有信息登记数据自动生成批处理Error",e);
        }
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }


}
