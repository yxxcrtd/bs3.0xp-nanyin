package com.joyintech.tams.batch.svreport.monthcollect.runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.monthcollect.service.MonthCollectImpMainService;

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
        
        //
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(batchExecuteInfo.getExecuteDate());
        
        if ( calendar.get(Calendar.DATE)!=1) {  //每月1日生成上月的数据
        	result.setStatus(BatchStatus.COMPLETED);
        	return result;
        }
                
        // 获取上月的第一天
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstday = calendar.getTime();
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date lastday = calendar.getTime();        
        
        Date batchDateStart = firstday;
        Date batchDateEnd = lastday;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("销售月报每月明细汇总批处理启动:"+sdf.format(bdate));
        try {
            monthCollectImpMainService.generateSalesTransactionSubInfo(batchDateStart,batchDateStart,batchDateEnd);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error("销售月报每月明细汇总批处理Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("销售月报每月明细汇总批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");
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
