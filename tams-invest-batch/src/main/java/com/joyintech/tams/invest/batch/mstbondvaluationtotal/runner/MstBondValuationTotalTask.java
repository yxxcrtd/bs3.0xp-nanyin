package com.joyintech.tams.invest.batch.mstbondvaluationtotal.runner;

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
import com.joyintech.tams.invest.batch.mstbondvaluationtotal.service.MstBondValuationTotalService;

/**
 * 估值行情根据配置汇总成一条
 * @author Administrator
 *
 */
@Service("mstBondValuationTotalTask")
@Scope("prototype")
public class MstBondValuationTotalTask extends BatchBaseService{
	
     @Autowired
     private MstBondValuationTotalService mstBondValuationTotalService;

	@Override
	public JobExecution execute() throws BatchException {
		JobExecution result = new JobExecution(1l);
		SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        result.setStatus(BatchStatus.FAILED);
        mstBondValuationTotalService.run(batchExecuteInfo);
        result.setStatus(BatchStatus.COMPLETED);
		return result;
	}
	
}
