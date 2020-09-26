package com.joyintech.tams.batch.svreport.chb.updateoperatemode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.svreport.chb.updateoperatemode.dao.UpdateOperateModeMapper;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

/**
 * 产品运作模式更新service
 * @author zhengxiaolian
 * @version 1.0 2019年12月23日
 * @see UpdateOperateModeMainService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UpdateOperateModeMainService extends AutoSendCBBaseService{
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UpdateOperateModeMapper mapper;
    
    
    public void doUpdateOperateMode(){
    	setBatchBeanName(BatchBeanName.产品运作模式更新);
    	
    	try {
    		logInfoBatchStart();
    		
            /**
        	 * 从baseinfo取
        	 * 然后更新产品登记编码
        	 * 
        	 */
    		logger.info("更新产品运作模式开始！");
    		mapper.updateOperateMode();
    		logger.info("更新产品运作模式结束！");
    		
    		logInfoBatchEnd();
			
		} catch (Exception e) {
            logInfo(e.getMessage());
            insertResultLogException(DateUtil.now());
            throw e;
		}
    }
    
}
