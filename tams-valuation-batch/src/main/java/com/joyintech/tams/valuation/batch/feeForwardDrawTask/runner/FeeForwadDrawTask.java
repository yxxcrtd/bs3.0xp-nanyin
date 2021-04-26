package com.joyintech.tams.valuation.batch.feeForwardDrawTask.runner;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.valuation.batch.feeForwardDrawTask.processor.FeeForwadDrawProcessor;
import com.joyintech.tams.valuation.batch.feeForwardDrawTask.reader.FeeForwadDrawReader;
import com.joyintech.tams.valuation.batch.feeForwardDrawTask.writer.FeeForwadDrawWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.baseclass.BatchBuilder;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;

/**
 * 费用预提批处理
 * @version 1.0
 *  当前只支持账户维护费计提，但是代码保留扩展性
 * @author jinmaodong
 * @date 2021-03-18 17:03:997
 *
 */
@Service("feeForwadDrawTask")
@Scope("prototype")
public class FeeForwadDrawTask extends BatchBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * batchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>,
         BaseWriter<NeedToWriteEntity>> batchBuilder;

    /**
     * step1Reader
     */
    @Autowired
    private FeeForwadDrawReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private FeeForwadDrawProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private FeeForwadDrawWriter step1Writer;

    @SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public JobExecution execute() throws BatchException {

        // 获取批处理信息
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();

        params.put("BATCHINFO", batchExecuteInfo);// // 获取批处理信息
        params.put("taskId", batchExecuteInfo.getTaskId());// 任务代码放入params中
        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
	}

}
