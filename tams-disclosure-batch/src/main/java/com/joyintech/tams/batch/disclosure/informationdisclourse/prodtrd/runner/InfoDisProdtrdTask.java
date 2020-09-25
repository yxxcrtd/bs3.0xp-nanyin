package com.joyintech.tams.batch.disclosure.informationdisclourse.prodtrd.runner;

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
import com.joyintech.tams.batch.disclosure.informationdisclourse.prodtrd.servcie.InfoDisProdtrdMainService;

/**
 * 生成产品申赎信息批处理
 * 
 * @author ZhangYangFei
 *
 */
@Service(value = "infoDisProdtrdTask")
@Scope("prototype")
public class InfoDisProdtrdTask extends BatchBaseService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 生成产品申赎信息批处理service
	 */
	@Autowired
	private InfoDisProdtrdMainService mainService;

	@Override
	public JobExecution execute() {
		JobExecution result = new JobExecution(1l);
		result.setStatus(BatchStatus.FAILED);
		SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
		if (batchExecuteInfo == null) {
			logger.info("无启动参数，批处理退出。");
			return result;
		}
		try {
			mainService.generateData(DateUtil.format(batchExecuteInfo.getExecuteDate()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		result.setStatus(BatchStatus.COMPLETED);
		return result;
	}
}
