package com.joyintech.tams.invest.batch.assetintdatecarryover.runner;

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
import com.joyintech.tams.invest.batch.assetintdatecarryover.processor.AssetIntdateCarryForwardProcessor;
import com.joyintech.tams.invest.batch.assetintdatecarryover.reader.AssetIntdateCarryForwardReader;
import com.joyintech.tams.invest.batch.assetintdatecarryover.writer.AssetIntdateCarryForwardWriter;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;

/**
 * 交易类标的生成批处理
 * 标的类资产理论付息日、理论还本日生成交易类标的批处理（债券、计划类、存款、项目类）
 * @author 金茂东
 * @Date 2021年6月3日
 *
 */
@Service("assetIntdateCarryForwardTask")
@Scope("prototype")
public class AssetIntdateCarryForwardTask extends BatchBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Autowired
    private AssetIntdateCarryForwardReader step1Reader;

    @Autowired
    private AssetIntdateCarryForwardProcessor step1Processor;

    @Autowired
    private AssetIntdateCarryForwardWriter step1Writer;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public JobExecution execute() throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        Map<String, Object> params = new HashMap<>();
        params.put(BatchConstant.BATCH_INFO, batchExecuteInfo);
        params.put(BatchConstant.PLATE_DATE, batchExecuteInfo.getExecuteDate());
        params.put("TASK_ID",batchExecuteInfo.getTaskId());

        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);

        return batchBuilder.init(this)
        		.batch("step1", (BaseReader)step1Reader,(BaseProcessor)step1Processor, (BaseWriter)step1Writer)
        		.builder(this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }
}
