package com.joyintech.tams.batch.svreport.proddurationreg.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.ProdDealInterfImpService;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.svreport.interf.chb.proddeal.service.ProdDealInterfService;

/**
 * 产品交易自动接入批处理
 * @author lingxq
 * @version 1.0 2019年1月26日
 * @see ProdDealImpMainService
 * @since 1.0
 */
@Service(value = "prodDealImpMainService")
@Scope("prototype")
public class ProdDealImpMainService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ProdDealInterfService prodDealInterfService;
    @Autowired
    private ProdDealInterfImpService prodDealInterfImpService;
    /**
     * @param batchTime 
     * @see
     * @since 1.0
     */
    public HashMap<String,List<String>> generatePrdDealData(Date batchDate,List<String> prodIdList) {
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.产品交易信息接入接入);
		logInfoBatchStart();
		HashMap<String,List<String>> mapPara = new HashMap<String,List<String>>();
		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.投资者交易信息接口数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
        }
		String contentSrc = interfConfig.getContentSrc();
		String dealFileType = SvrDataUtil.getSysParam("CHB_PRD_DEAL_FILE_TYPE");
		logger.debug("产品交易数据接入批处理开始");
        if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
        	mapPara = prodDealInterfService.generatePrdDealData(batchDate,prodIdList,false);
        }else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc) 
                || InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
        	if(dealFileType.equals(SVReportGlobalConstant.CHB_PRD_DEAL_FILE_TYPE_02)) {
        		// 阜新投资交易文件接入，直接从SALE_CUST_SVRREPORT抽取交易数据入中债CHB_PRD_DEAL表
        		prodDealInterfService.generatePrdDealDataFx(batchDate);
        	}else {
        		String result = prodDealInterfImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.投资者交易信息接口数据.getCode());
            	if ("fail".equals(result)){
                    throw new BusinessException("fail");
                }
        	}
        }
        else{
        	  logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
        }
		logger.debug("产品交易数据接入批处理结束");
		logInfoBatchEnd();
		return mapPara;
    }

}