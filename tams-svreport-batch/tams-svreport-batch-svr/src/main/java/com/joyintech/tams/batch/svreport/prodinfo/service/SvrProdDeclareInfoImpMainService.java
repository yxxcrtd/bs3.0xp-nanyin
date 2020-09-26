package com.joyintech.tams.batch.svreport.prodinfo.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.PrdDeclareInfoFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.proddeclare.service.ChbProdDeclareInterf;

/**
 * 产品申报要素接入批处理
 * @author yuluan
 * @version 1.0 2019年10月11日
 * @see prodDealImpService
 * @since 1.0
 */
@Service(value = "svrProdDeclareInfoImpMainService")
@Scope("prototype")
public class SvrProdDeclareInfoImpMainService extends AutoSendCBBaseService{
	 /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ChbProdDeclareInterf chbProdDeclareInterf;
    @Autowired
    private PrdDeclareInfoFileImpService prdDeclareInfoFileImpService;
  
	 public void generateProdInfoData(Date batchDate,List<String> prodIdList) {
	    	// 设置批处理bean
	    	setBatchBeanName(BatchBeanName.产品申报信息接口数据接入);
			logInfoBatchStart();
			  ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.产品申报信息接口数据.getCode());
	            if (interfConfig == null){
	                logger.info("CHB_INTERF_CONFIG表中无配置项！");
	            }
	            String contentSrc = interfConfig.getContentSrc();
	            // 产品要素
	            logger.debug("产品申报要素数据接入批处理开始");
	            if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
	            	 chbProdDeclareInterf.generatePrdDeclareData(batchDate);
	            } else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc) 
	                    || InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
                    String result = prdDeclareInfoFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.产品申报信息接口数据.getCode());
	                 if ("fail".equals(result)){
	                     throw new BusinessException("fail");
	                 }
	            } else {
	                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
	            }
	            
			logger.debug("产品申报要素数据接入批处理结束");
			logInfoBatchEnd();
	    }
}
