package com.joyintech.tams.batch.svreport.daycollect.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.daycollect.service.DayCollectImpMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能:每日明细汇总接口批处理入口(将业务信息接入到sum表中)
 */
@Service(value = "svrDayCollectInterfTaskService")
@Scope("prototype")
public class SvrDayCollectInterfTaskService extends BatchBaseService{
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private DayCollectImpMainService dayCollectImpMainService;
	
	@Override
	public JobExecution execute() throws BatchException {
		JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        if(dayCollectImpMainService.checkTransaction(batchExecuteInfo.getExecuteDate()) == 0){
            logger.info("当日交易数据不存在，或者没有接入，请确认后跑批。");
            return result;
        }
        if(dayCollectImpMainService.checkIdentity(batchExecuteInfo.getExecuteDate()) == 0){
            logger.info("当日身份信息不存在，或者没有接入,批处理继续。");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("销售月报每日明细汇总批处理启动:"+sdf.format(bdate));
        try {
            dayCollectImpMainService.generateSalesTransactionSubInfo(batchExecuteInfo.getExecuteDate());
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error("销售月报每日明细汇总批处理Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("销售月报每日明细汇总批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");
        }
        return result;
	}

}
