/*
 * 文件名：InfoDiscPreReadAndGenService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautosend.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautosend.service.InfoDiscAutoPackAndSendMainService;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscprereadandgen.service.InfoDiscPreReadAndGenMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 自动披露单据按代销机构打包并发送服务器批处理
 * @author zhouqiang
 * @version 1.0 2022年08月06日
 * @see InfoDiscAutoPackAndSendService
 * @since 1.0
 */
@Service(value = "infoDiscAutoPackAndSendService")
@Scope("prototype")
public class InfoDiscAutoPackAndSendService extends BatchBaseService {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 读取自动披露类型单据打包发送service
     */
    @Autowired
    private InfoDiscAutoPackAndSendMainService mainService;

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
            mainService.generateData(batchExecuteInfo);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }
}
