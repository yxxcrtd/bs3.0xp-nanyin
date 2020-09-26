package com.joyintech.tams.batch.report.equitybusiinfodatabackup.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.report.equitybusiinfodatabackup.service.EquityBusiInfoDataBackupMainService;

/**
 * 投资者明细信息数据归档批处理
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年12月5日
 * @see EquityBusiInfoDataBackupService
 * @since 1.0
 */
@Service(value = "equityBusiInfoDataBackupService")
@Scope("prototype")
public class EquityBusiInfoDataBackupService extends BatchBaseService {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 数据归档service
	 */
	@Autowired
	private EquityBusiInfoDataBackupMainService mainService;

	@Override
	public JobExecution execute() {
		JobExecution result = new JobExecution(1l);
		result.setStatus(BatchStatus.FAILED);
		SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
		if (batchExecuteInfo == null) {
			logger.info("无启动参数，批处理退出。");
			return result;
		}
		mainService.doDataBackup(batchExecuteInfo.getExecuteDate());
		result.setStatus(BatchStatus.COMPLETED);
		return result;
	}
}
