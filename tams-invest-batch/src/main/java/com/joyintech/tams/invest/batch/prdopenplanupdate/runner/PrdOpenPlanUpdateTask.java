package com.joyintech.tams.invest.batch.prdopenplanupdate.runner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dao.PrdOpenPlanUpdateMapper;
import com.joyintech.tams.invest.batch.prdopenplanupdate.processor.PrdOpenPlanUpdateProcessor;
import com.joyintech.tams.invest.batch.prdopenplanupdate.reader.PrdOpenPlanUpdateReader;
import com.joyintech.tams.invest.batch.prdopenplanupdate.writer.PrdOpenPlanUpdateWriter;

/**
 *  批处理-市场日历接入后更新产品开放计划
 */
@Service("prdOpenPlanUpdateTask")
@Scope("prototype")
public class PrdOpenPlanUpdateTask extends BatchBaseService{

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
     * 平台日期接口
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;

    /**
     * 读取注入
     */
    @Autowired
    private PrdOpenPlanUpdateReader stepReader;

    /**
     * 逻辑处理注入
     */
    @Autowired
    private PrdOpenPlanUpdateProcessor stepProcessor;

    /**
     * 写入处理注入
     */
    @Autowired
    private PrdOpenPlanUpdateWriter stepWriter;
    
    @Autowired
    private PrdOpenPlanUpdateMapper prdOpenPlanUpdateMapper;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public JobExecution execute() throws BatchException {
    	SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        
        
        String platYear = DateUtil.format(platformDateService.getSysDate()).substring(0, 4);
        //改为从表中获取
        String sysYear = prdOpenPlanUpdateMapper.getLastUpdateYear();
//        		CacheUtil.getSysParam("openplan_update_year");
        //查询市场日历表的最新的更新日期
        Date updateTime = prdOpenPlanUpdateMapper.getNewUpdateTime();
        Date plateDate = DateUtil.parse(platYear +"-11-01");
        //当市场日历表更新日期大于等于本年11月1日  并且 系统参数(市场日历更新联动更新的开发计划日期（年）)不等于本年时，批处理继续执行
        if(!StringUtil.equals(platYear, sysYear) && DateUtil.compareDate(updateTime, plateDate) >=0){
        	// 设置此task的序列任务公用的params参数
            Map<String, Object> params = new HashMap<>();

            params.put(BatchConstant.BATCH_INFO, batchExecuteInfo);
            params.put(BatchConstant.PLATE_DATE, platformDateService.getSysDate());

            // 各步骤设置参数
            stepReader.setParams(params);
            stepProcessor.setParams(params);
            stepWriter.setParams(params);
            
    		return batchBuilder.init(this).batch("step1", (BaseReader)stepReader,
    	            (BaseProcessor)stepProcessor, (BaseWriter)stepWriter).builder(
    	                    this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
        }else{
        	JobExecution result = new JobExecution(1l);
        	result.setStatus(BatchStatus.COMPLETED);
        	return result;
        }
	}

}
