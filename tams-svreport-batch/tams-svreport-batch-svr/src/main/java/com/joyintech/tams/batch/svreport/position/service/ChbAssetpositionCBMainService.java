package com.joyintech.tams.batch.svreport.position.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.position.service.AssetPositionService;

@Service
public class ChbAssetpositionCBMainService  extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AssetPositionService assetPositionService;
    
	public void genPosition(Date executeDate,List<String> prodids) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
        logger.info("生成资产头寸："+sdf.format(executeDate)+"开始");
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.资产头寸批处理);
        // 批处理开始
        logInfoBatchStart();
        Map<String,List<String>> mappara= new HashMap<String,List<String>>();
        mappara.put(InterfConstants.SVR_PRODLIST, prodids);
        assetPositionService.genAssetPosition(executeDate,mappara);
        // 批处理开始
        logInfoBatchEnd();
        logger.info("生成资产头寸："+sdf.format(executeDate)+"结束");
       
	}

}
