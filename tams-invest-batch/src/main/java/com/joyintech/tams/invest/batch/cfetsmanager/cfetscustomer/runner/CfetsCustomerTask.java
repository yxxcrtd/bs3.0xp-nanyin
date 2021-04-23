package com.joyintech.tams.invest.batch.cfetsmanager.cfetscustomer.runner;


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
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.invest.batch.cfetsmanager.cfetscustomer.service.CfetsCustomerService;

/**
 * JET下行基础信息-交易机构信息-主体信息
 * 
 * @author EX-ANJIAJUN001
 *
 */
@Service("cfetsCustomerTask")
@Scope("prototype")
public class CfetsCustomerTask extends BatchBaseService{

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * PlatformDate
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;
    
    @Autowired
    private CfetsCustomerService cfetsCustomerService;
    
	@Override
	public JobExecution execute() throws BatchException {
		SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        JobExecution result = new JobExecution(1L);
        result.setStatus(BatchStatus.FAILED);
        
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
     // 自动批处理，取平台日期为跑批日，手动批处理，取画面选择日期为跑批日
        Date batchDate = batchExecuteInfo.getExecuteDate();
        if (StringUtil.equals(BatchDictCodeList.批处理启动模式_自动.getValue(),
            batchExecuteInfo.getStartMode())) {
            batchDate = platformDateService.getSysDate();
        }
        try {
        	cfetsCustomerService.autoCustomerInfo(batchDate);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            result.setStatus(BatchStatus.FAILED);
        }
		return result;
	}

}
