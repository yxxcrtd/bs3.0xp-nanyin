/*
 * 文件名：InfoDisclosureReadAndGenService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月03日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.informationdisclosurereadandgen.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.tams.batch.disclosure.informationdisclourse.informationdisclosurereadandgen.service.InfoDisclosureReadAndGenMainService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;

/**
 * 读取作业信息并生成待处理信息批处理
 * @author wangmin
 * @version 1.0 2018年12月03日
 * @see InfoDisclosureReadAndGenService
 * @since 1.0
 */
@Service(value = "infoDisclosureReadAndGenService")
@Scope("prototype")
public class InfoDisclosureReadAndGenService extends BatchBaseService {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 读取作业信息并生成待处理信息service
     */
    @Autowired
    private InfoDisclosureReadAndGenMainService mainService;
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
