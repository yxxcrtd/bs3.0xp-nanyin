package com.joyintech.tams.batch.svreport.monthcollect.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.daycollect.service.DayCollectImpMainService;
import com.joyintech.tams.batch.svreport.monthcollect.service.MonthCollectImpMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能:月汇总
 */
@Service(value = "svrMonthCollectInterfTaskService")
@Scope("prototype")
public class SvrMonthCollectInterfTaskService extends BatchBaseService{
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private MonthCollectImpMainService monthCollectImpMainService;
	
	@Override
	public JobExecution execute() throws BatchException {
		JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        Date batchDateStart = this.getFirstDayDateOfMonth(batchExecuteInfo.getExecuteDate());
        Date batchDateEnd = this.getLastDayOfMonth(batchExecuteInfo.getExecuteDate());
        if(monthCollectImpMainService.checkAllMonth(batchDateStart,batchDateEnd) == 0){
            logger.info("请先执行明细汇总批处理，再执行月报生成批处理！");
            return result;
        }
        if(monthCollectImpMainService.checkSubmission(batchDateStart,batchDateEnd) != 0){
            logger.info("已报送的月报，不再生成报送数据！");
            return result;
        }
        if(monthCollectImpMainService.checkLastDay(batchDateEnd) == 0){
            logger.info("本月的明细信息不全，请确认数据是否完善，并请先执行明细汇总批处理！");
        }
        if(monthCollectImpMainService.checkHold(batchDateEnd) == 0){
            logger.info("当月月末的持有信息不存在，请确认后重新跑批！");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("销售月报每日明细汇总批处理启动:"+sdf.format(bdate));
        try {
            monthCollectImpMainService.generateSalesTransactionSubInfo(batchExecuteInfo.getExecuteDate(),batchDateStart,batchDateEnd);
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

	//获取当前月份的第一天
    public static Date getFirstDayDateOfMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

    //获取当前月份的最后一天
    public static Date getLastDayOfMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }
}
