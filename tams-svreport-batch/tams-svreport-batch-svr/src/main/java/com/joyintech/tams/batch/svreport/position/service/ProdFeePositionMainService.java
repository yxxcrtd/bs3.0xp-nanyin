package com.joyintech.tams.batch.svreport.position.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.position.service.ProdFeePositionService;

/**
 * 产品费用头寸自动生成批处理
 * @author chenxc
 * @version 1.7 2019年5月22日
 * @see ProdFeePositionMainService
 * @since 1.7
 */
@Service(value = "prodFeePositionMainService")
@Scope("prototype")
public class ProdFeePositionMainService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
	private ProdFeePositionService prodFeePositionService;
	
    /**
     * @param batchDate 
     * @param prodIdList 
     * @see
     * @since 1.0
     */
    public void generatePositionData(Date batchDate,List<String> prodIdList) {
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.产品费用头寸批处理);
		logInfoBatchStart();
    	 
		prodFeePositionService.generatePositionData(batchDate, prodIdList);
		
		logger.debug("产品费用头寸批处理");
		logInfoBatchEnd();
    }
}