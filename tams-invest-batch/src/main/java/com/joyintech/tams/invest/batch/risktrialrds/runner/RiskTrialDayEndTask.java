package com.joyintech.tams.invest.batch.risktrialrds.runner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.common.risktrialrds.dict.LogLvl;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
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
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.risktrialrds.dto.RiskTrialDayTimeReaderResult;
import com.joyintech.tams.invest.batch.risktrialrds.dto.RiskTrialDayTimeResult;
import com.joyintech.tams.invest.batch.risktrialrds.service.RiskTrialDayTimeProcessorService;
import com.joyintech.tams.invest.batch.risktrialrds.service.RiskTrialDayTimeReaderService;
import com.joyintech.tams.invest.batch.risktrialrds.service.RiskTrialDayTimeWriterService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.risktrialrds.util.Const;

/**
 * 日终批处理指标计算
 * @author 裴家俊
 * @version 1.0 2019/9/2
 * @see RiskTrialDayEndTask
 * @since 1.0
 **/
@Service(value="riskTrialDayEndTask")
@Scope(value="prototype")
public class RiskTrialDayEndTask extends BatchBaseService{
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;
    /**
     * PlatformDate
     */
    @Autowired(required = false)
    private IPlatformDate platformDateService;

    /**
     * PlatformDate
     */
    @Autowired(required = false)
    private RiskRedisInitService riskRedisInitService;

    /**
     * RiskTrialDayTimeReader
     */
    @Autowired
    private RiskTrialDayTimeReaderService step1Reader;

    /**
     * RiskTrialDayTimeProcessor
     */
    @Autowired
    private RiskTrialDayTimeProcessorService step1Processor;

    /**
     * RiskTrialDayTimeWriter
     */
    @Autowired
    private RiskTrialDayTimeWriterService step1Writer;

    @Override
    public JobExecution execute() throws BatchException {
    	JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        try{
	        params.put(BatchConstant.BATCH_INFO, batchExecuteInfo);
	        if(null!=batchExecuteInfo&&null!=batchExecuteInfo.getExecuteDate()&&!StringUtil.equals("0",batchExecuteInfo.getStartMode())){
	            params.put(BatchConstant.PLATE_DATE, batchExecuteInfo.getExecuteDate());
	        }
	        else{
	            params.put(BatchConstant.PLATE_DATE, platformDateService.getSysDate());
	        }
	        params.put(Const.PROD_LIST,this.getProductIdList());
	        params.put(Const.SUP_FREQUENCY, BussinessDictCodeListRds.监督频率_日终.getValue());
	        List<RiskTrialDayTimeReaderResult> readerList=step1Reader.execute(params);
	        List<RiskTrialDayTimeResult> processList=step1Processor.execute(readerList,params);
	        step1Writer.execute(processList, params);
	        result.setStatus(BatchStatus.COMPLETED);
	    }
	    catch (Exception e) {
	        logger.error(e.getMessage(),e);
            riskRedisInitService.insertTrialLog(RiskUtil.getLogString(e), StringUtil.isNotEmpty(params.get(BatchConstant.PLATE_DATE))? (Date)params.get(BatchConstant.PLATE_DATE):new Date(), LogLvl.ERROR.toString(), BussinessDictCodeListRds.批机能类型_日终指标计算.getValue());
	    }
        return result;
    }
}
