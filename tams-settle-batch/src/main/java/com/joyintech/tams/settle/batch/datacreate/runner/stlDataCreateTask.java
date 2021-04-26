package com.joyintech.tams.settle.batch.datacreate.runner;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.baseclass.BatchBuilder;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.settle.batch.datacreate.dto.StlDataCreateWriteDto;
import com.joyintech.tams.settle.batch.datacreate.processor.StlDataCreateProcessor;
import com.joyintech.tams.settle.batch.datacreate.reader.StlDataCreateReader;
import com.joyintech.tams.settle.batch.datacreate.writer.StlDataCreateWriter;

/**
 * @author 陈卓
 * @date 2020/11/26
 * @see stlDataCreateTask
 * @since 1.0
 */
@Service("stlDataCreateTask")
public class stlDataCreateTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private StlDataCreateReader stlDataCreateReader;
    
    @Autowired
    private StlDataCreateProcessor stlDataCreateProcessor;
    
    @Autowired
    private StlDataCreateWriter stlDataCreateWriter;

    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<StlDataCreateReader, BaseProcessor<NeedOperateEntity, StlDataCreateWriteDto>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Override
    public JobExecution execute() throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("productIdList", this.getProductIdList());
        stlDataCreateReader.setParams(params);
        return batchBuilder.init(this)
                .batch("StlDataCreate", stlDataCreateReader, stlDataCreateProcessor, stlDataCreateWriter)
                .builder("结算下行数据自动生成").run();
    }
}