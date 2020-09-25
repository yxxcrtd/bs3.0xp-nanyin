/*
 * 文件名：InfoDisclosureGenAndSendService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.informationdisclosuregenandsend.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.tams.batch.disclosure.informationdisclourse.informationdisclosuregenandsend.service.InfoDisclosureGenAndSendMainService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;

/**
 * 信息披露生成报表并发送批处理
 * @author wangmin
 * @version 1.0 2018年11月28日
 * @see infoDisclosureGenAndSendService
 * @since 1.0
 */
@Service(value = "infoDisclosureGenAndSendService")
@Scope("prototype")
public class InfoDisclosureGenAndSendService extends BatchBaseService {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 信息披露生成报表并发送批处理service
     */
    @Autowired
    private InfoDisclosureGenAndSendMainService mainService;
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
            mainService.generateData(DateUtil.format(batchExecuteInfo.getExecuteDate()));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }
}
