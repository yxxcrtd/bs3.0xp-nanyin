package com.joyintech.tams.batch.report.equityinforegistautomanual.runner;

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
import com.joyintech.tams.batch.report.equityinforegistautomanual.service.EquityInfoRegistAutoManualCBMainService;

/**
 * 投资者身份信息登记手工处理
 *
 * @author zhengxiaolian
 * @version 1.0 2020年01月07日
 * @see EquityInfoRegistAutoManualCBService
 * @since 1.0
 */
@Service(value = "equityInfoRegistAutoManualCBService")
@Scope("prototype")
public class EquityInfoRegistAutoManualCBService extends BatchBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 投资者身份信息登记手工处理 业务service
     */
    @Autowired
    private EquityInfoRegistAutoManualCBMainService mainService;
	
	@Override
	public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);

        mainService.autoManualCB();
        
        result.setStatus(BatchStatus.COMPLETED);
        return result;
	}
}
