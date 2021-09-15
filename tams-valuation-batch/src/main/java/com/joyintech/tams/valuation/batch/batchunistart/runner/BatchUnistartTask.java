package com.joyintech.tams.valuation.batch.batchunistart.runner;


import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.dao.SysBatchExecLogMapper;
import com.joyintech.framework.base.entity.SysBatchExecLog;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.batchunistart.dao.BatchUnistartMapper;
import com.joyintech.tams.valuation.batch.batchunistart.processor.BatchUnistartProcessor;
import com.joyintech.tams.valuation.batch.batchunistart.reader.BatchUnistartReader;
import com.joyintech.tams.valuation.batch.batchunistart.writer.BatchUnistartWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("batchUnistartTask")
@Scope("prototype")
public class BatchUnistartTask extends BatchBaseService {
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
    private BatchUnistartReader step0Reader;

    @Autowired
    private BatchUnistartProcessor step0Processor;

    @Autowired
    private BatchUnistartWriter step0Writer;

    @Autowired
    private BatchUnistartMapper batchUnistartMapper;

    @Autowired
    private SysBatchExecLogMapper sysBatchExecLogMapper;



    @Override
    public JobExecution execute()
            throws BatchException {

        // 获取批处理信息
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        if (StringUtil.equals(BatchDictCodeList.批处理启动模式_自动.getValue(),batchExecuteInfo.getStartMode())){
            // 仅自动 需要 在此校验  所有机构 是否  批前检查 有无卡批
            // 手动在 批处理启动时校验 可获得选择机构校验
            Map<String,Object> param = new HashMap<>();
            param.put("checkDate",batchExecuteInfo.getExecuteDate());
            List<String> list= batchUnistartMapper.selectErrorPrebatchResult(param);

            if(!StringUtil.isEmptyList(list)){

                SysBatchExecLog sysBatchExecLog = new SysBatchExecLog();
                sysBatchExecLog.setSeqNo(batchExecuteInfo.getSeqNo());
                sysBatchExecLog.setLogInfo("批前检查有卡批项未检查通过，请处理后手动启动【批前检查】后再启动【批处理统一启动】");
                sysBatchExecLogMapper.updateByPrimaryKeySelective(sysBatchExecLog);

                JobExecution execution = new JobExecution(2333L);
                execution.setStatus(BatchStatus.FAILED);


                return execution;
            }


        }

        logger.info("批处理[" + this.getBatchTask().getTaskName() + "]开始。");
        return batchBuilder.init(this).batch("step0", (BaseReader)step0Reader,
                (BaseProcessor)step0Processor, (BaseWriter)step0Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
