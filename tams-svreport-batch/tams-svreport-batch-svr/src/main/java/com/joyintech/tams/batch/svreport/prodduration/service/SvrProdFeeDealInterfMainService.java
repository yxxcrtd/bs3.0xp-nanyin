package com.joyintech.tams.batch.svreport.prodduration.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.ProdFeeDealInterfImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.proddeal.service.ProdDealInterfService;

/**
 * 产品费用交易自动接入批处理
 * @author yuluan
 * @version 1.0 2019年4月9日
 * @see SvrProdFeeDealInterfMainService
 * @since 1.0
 */
@Service(value = "svrProdFeeDealInterfMainService")
@Scope("prototype")
public class SvrProdFeeDealInterfMainService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ProdDealInterfService prodDealInterfService;
    @Autowired
    private ProdFeeDealInterfImpService prodFeeDealInterfImpService;
    /**
     * @param batchTime 
     * @see
     * @since 1.0
     */
    public HashMap<String,List<String>> generatePrdDealData(Date batchDate,List<String> prodIdList,boolean isSynchronizeTask) {
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.产品费用交易接口数据接入);
		logInfoBatchStart();
		HashMap<String,List<String>> mapPara = new HashMap<String,List<String>>();
		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.产品费用交易接口数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
        }
		String contentSrc = interfConfig.getContentSrc();
        // 费用交易信息
        if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
        	mapPara = prodDealInterfService.generatePrdFeeDealData(batchDate,prodIdList,isSynchronizeTask);
        }else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc) 
                || InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
            String result = prodFeeDealInterfImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.产品费用交易接口数据.getCode());
            if ("fail".equals(result)){
                 throw new BusinessException("fail");
             }
        } else {
            logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
        }
		logger.debug("产品交易基础数据导入批处理");
		logInfoBatchEnd();
		return mapPara;
    }

}