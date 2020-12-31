package com.joyintech.tams.valuation.batch.accountmaintfeebook.runner;

import java.util.HashMap;
import java.util.Map;

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
import com.joyintech.tams.valuation.batch.accountmaintfeebook.processor.AccountMaintFeeBookProcessor;
import com.joyintech.tams.valuation.batch.accountmaintfeebook.reader.AccountMaintFeeBookReader;
import com.joyintech.tams.valuation.batch.accountmaintfeebook.writer.AccountMaintFeeBookWriter;

/**
 * 账户维护费每日计提
 * @author hpp
 *
 */
@Service("accountMaintFeeBookTask")
@Scope("prototype")
public class AccountMaintFeeBookTask extends BatchBaseService{

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
    private AccountMaintFeeBookReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private AccountMaintFeeBookProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private AccountMaintFeeBookWriter step1Writer;

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
