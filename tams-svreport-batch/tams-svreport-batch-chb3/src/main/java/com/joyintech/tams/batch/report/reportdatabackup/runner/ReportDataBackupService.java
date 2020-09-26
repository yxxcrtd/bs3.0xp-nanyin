/*
 * 文件名：ReportDataBackupService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.reportdatabackup.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.tams.batch.report.reportdatabackup.service.ReportDataBackupMainService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;

/**
 * 数据备份批处理
 * @author wangmin
 * @version 1.0 2018年10月9日
 * @see ReportDataBackupService
 * @since 1.0
 */
@Service(value = "reportDataBackupService")
@Scope("prototype")
public class ReportDataBackupService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 数据归档service
     */
    @Autowired
    private ReportDataBackupMainService mainService;

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
            logger.error("数据备份批处理Error",e);
        }
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }


}
