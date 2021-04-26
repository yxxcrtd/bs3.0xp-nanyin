package com.joyintech.tams.settle.batch.datadeal.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealDeleteWriteDto;
import com.joyintech.tams.settle.batch.datadeal.listener.StlDataDealStepListener;
import com.joyintech.tams.settle.batch.datadeal.processor.StlDownDataDealDeleteProcessor;
import com.joyintech.tams.settle.batch.datadeal.processor.StlDownDataDealProcessor;
import com.joyintech.tams.settle.batch.datadeal.reader.StlDownDataDealDeleteReader;
import com.joyintech.tams.settle.batch.datadeal.reader.StlDownDataDealReader;
import com.joyintech.tams.settle.batch.datadeal.writer.StlDownDataDealDeleteWriter;
import com.joyintech.tams.settle.batch.datadeal.writer.StlDownDataDealWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据清算批处理
 * 
 * @author 陈卓
 * @date 2020/7/2
 * @see StlDownDataDealTask
 * @since 1.0
 */
@Service("stlDownDataDealTask")
public class StlDownDataDealTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, StlDownDataDealDeleteWriteDto>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    // step1
    @Autowired
    private StlDownDataDealDeleteReader step1Reader;
    
    @Autowired
    private StlDownDataDealDeleteProcessor step1Processor;
    
    @Autowired
    private StlDownDataDealDeleteWriter step1Writer;
    
    // step2
    @Autowired
    private StlDownDataDealReader step2Reader;
    
    @Autowired
    private StlDownDataDealProcessor step2Processor;
    
    @Autowired
    private StlDownDataDealWriter step2Writer;

    @Autowired
    private StlDataDealStepListener stlDataDealStepListener;
    
    @Override
    public JobExecution execute() throws BatchException {

        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        
        return batchBuilder.init(this)
				.batch("数据清算处理step1", (BaseReader)step1Reader, (BaseProcessor)step1Processor, (BaseWriter)step1Writer)
				.batch("数据清算处理step2", (BaseReader)step2Reader, (BaseProcessor)step2Processor, (BaseWriter)step2Writer, stlDataDealStepListener)
				.builder(this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

    private <T> T getBean(Class<T> beanType) {
        return SpringContextUtil.getBean(beanType);
    }
}
