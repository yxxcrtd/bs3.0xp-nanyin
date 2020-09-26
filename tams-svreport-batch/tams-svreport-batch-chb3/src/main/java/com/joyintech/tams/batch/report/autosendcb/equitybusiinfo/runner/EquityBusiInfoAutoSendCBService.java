/*
 * 文件名：EquityBusiInfoAutoSendCBService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘二通
 * 修改时间：2017年11月14日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */
 
package com.joyintech.tams.batch.report.autosendcb.equitybusiinfo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.report.autosendcb.equitybusiinfo.service.EquityBusiInfoAutoSendCBMainService;


/**
 * 业务明细数据自动检查报送
 *
 * @author 刘二通
 * @version 1.0 2017年11月14日
 * @see EquityBusiInfoAutoSendCBService
 * @since 1.0
 */
@Service(value = "equityBusiInfoAutoSendCBService")
@Scope("prototype")
public class EquityBusiInfoAutoSendCBService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 业务明细数据自动检查报送 业务service
     */
    @Autowired
    private EquityBusiInfoAutoSendCBMainService mainService;
    
    @Override
    public JobExecution execute() {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        mainService.autoSendCB(batchExecuteInfo);
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }
}