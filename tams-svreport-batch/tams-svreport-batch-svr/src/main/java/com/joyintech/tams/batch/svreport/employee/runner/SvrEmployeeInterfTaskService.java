package com.joyintech.tams.batch.svreport.employee.runner;

import java.text.SimpleDateFormat;
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
import com.joyintech.tams.batch.svreport.employee.service.EmployeeImpMainService;

/**
 * 功能:从业人员信息接口批处理入口(将业务维护进来的信息接入到sub表中)
 * @since 1.0.0
 * @version 1.0.0
 * @作者：lingxq
 * @创建时间：2020年10月17日
 */
@Service(value = "svrEmployeeInterfTaskService")
@Scope("prototype")
public class SvrEmployeeInterfTaskService extends BatchBaseService{
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private EmployeeImpMainService employeeImpMainService;
	
	@Override
	public JobExecution execute() throws BatchException {
		JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("从业人员信息接口批处理启动:"+sdf.format(bdate));
        try {
        	employeeImpMainService.generateEmployeeSubInfo(batchExecuteInfo.getExecuteDate());
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error("从业人员信息接口批处理Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("从业人员信息接口批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
	}

}
