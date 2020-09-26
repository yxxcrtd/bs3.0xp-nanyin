package com.joyintech.tams.batch.report.equitysharecheckservice.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.report.equitysharecheckservice.service.EquityShareCheckMainService;

/**
 * 投资者份额检查批处理
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年12月9日
 * @see EquityShareCheckService
 * @since 1.0
 */
@Service(value = "equityShareCheckService")
@Scope("prototype")
public class EquityShareCheckService extends BatchBaseService{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 投资者份额检查批处理service
	 */
	@Autowired
	private EquityShareCheckMainService mainService;
	
	@Override
	public JobExecution execute() {
		JobExecution result = new JobExecution(1l);
		result.setStatus(BatchStatus.FAILED);
		SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
		if (batchExecuteInfo == null) {
			logger.info("无启动参数，批处理退出。");
			return result;
		}
		mainService.doEquityShareCheck(DateUtil.format(batchExecuteInfo.getExecuteDate()));
		result.setStatus(BatchStatus.COMPLETED);
		return result;
	}

}
