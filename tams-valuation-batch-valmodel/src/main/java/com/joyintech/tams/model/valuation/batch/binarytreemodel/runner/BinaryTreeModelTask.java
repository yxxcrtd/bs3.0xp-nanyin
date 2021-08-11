package com.joyintech.tams.model.valuation.batch.binarytreemodel.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.listener.BinaryTreeModelListener;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.processor.BinaryTreeModelProcessor;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.reader.BinaryTreeModelReader;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.writer.BinaryTreeModelWriter;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.listener.ProductAssetsIncomeMethodListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("binaryTreeModelTask")
@Scope("prototype")
public class BinaryTreeModelTask extends BatchBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static Boolean isRunning = false;
    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;
    /**
     * 读
     */
    @Autowired
    private BinaryTreeModelReader binaryTreeModelReader;
    /**
     * 执行
     */
    @Autowired
    private BinaryTreeModelProcessor binaryTreeModelProcessor;
    /**
     * 写
     */
    @Autowired
    private BinaryTreeModelWriter binaryTreeModelWriter;

    @Autowired
    private BinaryTreeModelListener binaryTreeModelListener;

    @Override
    public JobExecution execute() throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        params.put("BATCH_INFO", batchExecuteInfo);

//        把执行日期当作平台日来替换处理
        params.put("PLATE_DATE", batchExecuteInfo.getExecuteDate());
        params.put("TASK_ID",batchExecuteInfo.getTaskId());
        // 各步骤设置参数
        binaryTreeModelReader.setParams(params);
        binaryTreeModelProcessor.setParams(params);
        binaryTreeModelWriter.setParams(params);

        return batchBuilder.init(this).batch("step1",
                (BaseReader)binaryTreeModelReader,(BaseProcessor)binaryTreeModelProcessor, (BaseWriter)binaryTreeModelWriter).builder(
                this.getBatchTask().getBeanName()  + GlobalConstant.UNDER_LINE + this.getBatchTask().getTaskId()
                , binaryTreeModelListener).run();
    }
}
