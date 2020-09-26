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
import com.joyintech.tams.svreport.position.service.ProdPosition001Service;

/**
 * 产品头寸自动生成批处理
 * @author lingxq
 * @version 1.0 2019年1月26日
 * @see ProdDurationRegCBMainService
 * @since 1.0
 */
@Service(value = "prodPositionMainService")
@Scope("prototype")
public class ProdPositionMainService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
	private ProdPosition001Service prodPosition001Service;
	
    /**
     * @param batchDate 
     * @param prodIdList 
     * @see
     * @since 1.0
     */
    public void generatePositionData(Date batchDate,List<String> prodIdList) {
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.产品头寸批处理);
		logInfoBatchStart();
    	 
		prodPosition001Service.generatePositionData(batchDate, prodIdList);
		
		logger.debug("产品头寸批处理");
		logInfoBatchEnd();
    }
}