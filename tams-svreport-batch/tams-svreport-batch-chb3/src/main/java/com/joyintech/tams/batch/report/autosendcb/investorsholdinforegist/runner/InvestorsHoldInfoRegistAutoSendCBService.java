/*
 * 文件名：InvestorsHoldInfoRegistAutoSendCBService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月14日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.autosendcb.investorsholdinforegist.runner;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.tams.batch.report.autosendcb.investorsholdinforegist.service.InvestorsHoldInfoRegistAutoSendCBMainService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 投资者持有信息数据自动检查报送
 * @author zuoyinyi
 * @version 1.0 2018年5月14日
 * @see InvestorsHoldInfoRegistAutoSendCBService
 * @since 1.0
 */
@Service(value = "investorsHoldInfoRegistAutoSendCBService")
@Scope("prototype")
public class InvestorsHoldInfoRegistAutoSendCBService extends BatchBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 投资者持有信息数据自动检查报送 业务service
     */
    @Autowired
    private InvestorsHoldInfoRegistAutoSendCBMainService mainService;

    @Override
    public JobExecution execute() {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
//            return result;
        }

        // 批处理执行日期（要报送的持有日期）
        String holdDate = null;

        // 批处理开始日
        Date executeDate = batchExecuteInfo.getExecuteDate();
        logger.info("[参数]批处理开始日executeDate：" + DateUtil.format(executeDate));

        if (StringUtil.equals(BatchDictCodeList.批处理启动模式_手动.getValue(),
            batchExecuteInfo.getStartMode())) {
            holdDate = DateUtil.format(executeDate);
            logger.info("手动跑批，将处理持有日期为" + holdDate + "的持有信息数据。");
        }
        else {
            holdDate = null;
            logger.info("自动跑批，将处理所有未报送的持有信息数据。");
        }

        try {
            mainService.autoSendCB(holdDate);
        }
        catch (Exception e) {
            logger.error("投资者持有信息数据自动检查报送Error",e);
        }
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }
}
