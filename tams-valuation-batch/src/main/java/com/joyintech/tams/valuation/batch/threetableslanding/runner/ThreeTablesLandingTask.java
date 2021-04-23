package com.joyintech.tams.valuation.batch.threetableslanding.runner;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.threetableslanding.processor.ThreeTablesLandingDelProcessor;
import com.joyintech.tams.valuation.batch.threetableslanding.processor.ThreeTablesLandingProcessor1;
import com.joyintech.tams.valuation.batch.threetableslanding.processor.ThreeTablesLandingProcessor2;
import com.joyintech.tams.valuation.batch.threetableslanding.processor.ThreeTablesLandingProcessor3;
import com.joyintech.tams.valuation.batch.threetableslanding.reader.ThreeTablesLandingReader;
import com.joyintech.tams.valuation.batch.threetableslanding.writer.ThreeTablesLandingDelWriter;
import com.joyintech.tams.valuation.batch.threetableslanding.writer.ThreeTablesLandingWriter;
import com.joyintech.tams.valuation.common.constants.BookConstant;

@Service("threeTablesLandingTask")
@Scope("prototype")
public class ThreeTablesLandingTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * batchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Autowired
    private BookCommonLogic bookCommonLogic;

    @Autowired
    private ThreeTablesLandingReader step0Reader;

    @Autowired
    private ThreeTablesLandingDelProcessor step0Processor;

    @Autowired
    private ThreeTablesLandingDelWriter step0Writer;

    @Autowired
    private ThreeTablesLandingReader step1Reader;

    @Autowired
    private ThreeTablesLandingProcessor1 step1Processor;

    @Autowired
    private ThreeTablesLandingWriter step1Writer;

    @Autowired
    private ThreeTablesLandingReader step2Reader;

    @Autowired
    private ThreeTablesLandingProcessor2 step2Processor;

    @Autowired
    private ThreeTablesLandingWriter step2Writer;

    @Autowired
    private ThreeTablesLandingReader step3Reader;

    @Autowired
    private ThreeTablesLandingProcessor3 step3Processor;

    @Autowired
    private ThreeTablesLandingWriter step3Writer;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public JobExecution execute()
        throws BatchException {

        // 获取批处理信息
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", batchExecuteInfo.getTaskId());// 任务代码放入params中
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(
            this.getBatchExecuteInfo());
        params.put(BookConstant.BOOKSET_DATE,
            DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put(BookConstant.BOOK_DATE,
            DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put(BookConstant.HAPPEN_DATE,
            DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put("booksetIds", this.getProductIdList());
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        // 各步骤设置参数
        step3Reader.setParams(params);
        step3Processor.setParams(params);
        step3Writer.setParams(params);
        step2Reader.setParams(params);
        step2Processor.setParams(params);
        step2Writer.setParams(params);
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        step0Reader.setParams(params);
        step0Processor.setParams(params);
        step0Writer.setParams(params);
        logger.info("批处理[" + this.getBatchTask().getTaskName() + "]开始。");
        return batchBuilder.init(this).batch("step0", (BaseReader)step0Reader,
            (BaseProcessor)step0Processor, (BaseWriter)step0Writer).batch("step1",
                (BaseReader)step1Reader, (BaseProcessor)step1Processor,
                (BaseWriter)step1Writer).batch("step2", (BaseReader)step2Reader,
                    (BaseProcessor)step2Processor, (BaseWriter)step2Writer).batch("step3",
                        (BaseReader)step3Reader, (BaseProcessor)step3Processor,
                        (BaseWriter)step3Writer).builder(
                            this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
