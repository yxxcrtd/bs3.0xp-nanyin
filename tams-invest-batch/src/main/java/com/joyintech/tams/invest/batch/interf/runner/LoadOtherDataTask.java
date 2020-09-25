/*
 * 文件名：LoadOtherDataTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.invest.interf.dto.BatchResultDto;
import com.joyintech.tams.invest.interf.service.InterfComponentService;

/**
 * wind数据接入，ods文件录入等定时任务共同类
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see LoadOtherDataTask
 * @since 1.0
 */
public abstract class LoadOtherDataTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 接口组件服务
     */
    @Autowired
    private InterfComponentService interfComponentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecution execute()
        throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        try {
            // 接口参数-任务信息
            BatchResultDto dto = new BatchResultDto();
            dto.setBatchId(this.getBatchTask().getTaskId());
            dto.setBatchName(this.getBatchTask().getBeanName());
            // 调用接口
            interfComponentService.sendBatchRequest(getCaller(), dto);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 取得调用模块
     * 
     * @return 调用模块
     * @see
     * @since 1.0
     */
    public abstract String getCaller();
}
