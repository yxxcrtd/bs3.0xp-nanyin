package com.joyintech.tams.valuation.batch.fxdrawbookdetail.runner;
/**
 * @author yanya
 * @create 2019-12-12 16:59
 */

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.valuation.batch.common.bookgenerateresult.BookGenerateResultForCurrentCommonWriterWithBokSummary;
import com.joyintech.tams.valuation.batch.fxdrawbookdetail.processor.FxDrawBookDetailProcessor;
import com.joyintech.tams.valuation.batch.fxdrawbookdetail.reader.FxDrawBookDetailReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FxDrawBookDetailTask
 * @Description: 逾期款计提账务处理
 * @Author yanya
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Service("fxDrawBookDetailTask")
@Scope("prototype")
public class FxDrawBookDetailTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity,
                NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    /**
     * step1Reader
     */
    @Autowired
    FxDrawBookDetailReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    FxDrawBookDetailProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private BookGenerateResultForCurrentCommonWriterWithBokSummary step1Writer;


    @Override
    public JobExecution execute() throws BatchException {
        //获取批处理信息
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();

        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
                (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }
}
