/*
 * 文件名：InfoDiscAutoRevealTask.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月15日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.processor.InfoDiscAutoRevealProcessor;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.reader.InfoDiscAutoRevealReader;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.writer.InfoDiscAutoRevealWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 自动披露-批处理
 *
 * @author wangmin
 * @version 1.0 2018年12月15日
 * @see InfoDiscAutoRevealTask
 * @since 1.0
 */
@Service("infoDiscAutoRevealTask")
@Scope("prototype")
public class InfoDiscAutoRevealTask extends BatchBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;
    /**
     * step1Reader
     */
    @Autowired
    private InfoDiscAutoRevealReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private InfoDiscAutoRevealProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private InfoDiscAutoRevealWriter step1Writer;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public synchronized JobExecution execute() throws BatchException {

        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();

        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        logger.info("信息披露批处理开始执行");
        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        params.put("BATCHINFO", batchExecuteInfo);
        params.put("BATCHDEL", this.getBatchExecuteDetail());
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put("taskId", batchExecuteInfo.getTaskId());
        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        
        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
                (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                        this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
