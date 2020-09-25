/*
 * 文件名：RskParamReCalcTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.riskparamcalc.runner;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.riskparamcalc.processor.RskParamReCalcProcessor;
import com.joyintech.tams.invest.batch.riskparamcalc.reader.RskParamReCalcReader;
import com.joyintech.tams.invest.batch.riskparamcalc.writer.RskParamReCalcWriter;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.baseclass.BatchBuilder;
import com.joyintech.batch.batchbase.exception.BatchException;


/**
 * 批处理-指标参数重新更新redis
 * 
 * @author jjpei
 * @version 1.0 2020年7月23日
 * @see RskParamReCalcTask
 * @since 1.0
 */
@Service(value = "rskParamReCalcTask")
@Scope(value = "prototype")
public class RskParamReCalcTask extends BatchBaseService {

    /**
     * 控制台日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<RskParamReCalcReader, RskParamReCalcProcessor, RskParamReCalcWriter> batchBuilder;

    /**
     * RskParamReCalcReader
     */
    @Autowired
    private RskParamReCalcReader step1Reader;

    /**
     * RskParamReCalcProcessor
     */
    @Autowired
    private RskParamReCalcProcessor step1Processor;

    /**
     * RskParamReCalcWriter
     */
    @Autowired
    private RskParamReCalcWriter step1Writer;

    @Autowired
    private PlatformDateImpl platDataUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecution execute()
        throws BatchException {
        logger.info("批处理-指标参数重新更新redis开始");
        Map<String, Object> params = new HashMap<>();
        params.put(BatchConstant.KEY_PRODID_LIST, this.getProductIdList());
        params.put("sysDate", platDataUtil.getSysDate());
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        return batchBuilder.init(this)
            .batch("step1", step1Reader, step1Processor, step1Writer)
            .builder(this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId())
            .run();
    }

}
