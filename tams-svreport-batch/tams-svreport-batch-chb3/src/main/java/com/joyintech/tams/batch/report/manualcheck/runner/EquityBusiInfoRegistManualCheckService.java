package com.joyintech.tams.batch.report.manualcheck.runner;

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
import com.joyintech.tams.batch.report.manualcheck.service.EquityBusiInfoRegistManualCheckMainService;

/**
 * 投资者明细信息数据检查批处理
 *
 * @author zhengxiaolian
 * @version 1.0 2019年11月21日
 * @see EquityBusiInfoRegistManualCheckService
 * @since 1.0
 */
@Service(value = "equityBusiInfoRegistManualCheckService")
@Scope("prototype")
public class EquityBusiInfoRegistManualCheckService extends BatchBaseService {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 投资者明细信息数据检查批处理 业务service
	 */
	@Autowired
	private EquityBusiInfoRegistManualCheckMainService mainService;

	@Override
	public JobExecution execute() throws BatchException {
		JobExecution result = new JobExecution(1l);
		result.setStatus(BatchStatus.FAILED);
		SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();

		if (batchExecuteInfo == null) {
			logger.info("无启动参数，批处理退出。");
			return result;
		}
//		try {
			mainService.manualCheck(batchExecuteInfo);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
		result.setStatus(BatchStatus.COMPLETED);
		return result;
	}

}
