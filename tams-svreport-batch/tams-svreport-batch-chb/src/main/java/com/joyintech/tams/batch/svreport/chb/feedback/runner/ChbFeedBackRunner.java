/*
 * 文件名：ChbFeedBackRunner.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年10月20日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.batch.svreport.chb.feedback.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.component.svrreport.chb.feedbackmsg.service.ChbFeedbackMsgService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 王波
 * @date 2020年10月20日
 * @see ChbFeedBackRunner
 * @since 1.0
 */
@Service(value = "chbFeedBackRunner")
@Scope("prototype")
public class ChbFeedBackRunner extends BatchBaseService {

    @Autowired
    private ChbFeedbackMsgService chbFeedbackMsgService;

    @Override
    public JobExecution execute() throws BatchException {

        JobExecution result = new JobExecution(1l);
        // 调用组件处理反馈信息
        try {
            chbFeedbackMsgService.dealFeedBack(new Date());
        } catch (Exception e) {
            result.setStatus(BatchStatus.FAILED);
        }

        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }
}
