package com.joyintech.tams.batch.svreport.prodinfo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.ProdInfoFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.prodinfo.service.ProdInfoInterfService;

/**
 * 产品要素接入批处理
 * @author lingxq
 * @version 1.0 2019年2月19日
 * @see prodDealImpService
 * @since 1.0
 */
@Service(value = "SvrProdInfoImpMainService")
@Scope("prototype")
public class SvrProdInfoImpMainService extends AutoSendCBBaseService{
	 /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProdInfoInterfService prodInfoInterfService;
    @Autowired
    private ProdInfoFileImpService prodInfoFileImpService;
	 public List<String> generateProdInfoData(Date batchDate,List<String> prodIdList) {
	    	// 设置批处理bean
	    	setBatchBeanName(BatchBeanName.产品基本信息接口数据接入);
			logInfoBatchStart();
			List<String> resultList= new ArrayList<String>();
			  ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.产品基本信息接口数据.getCode());
	            if (interfConfig == null){
	                logger.info("CHB_INTERF_CONFIG表中无配置项！");
	            }
	            String contentSrc = interfConfig.getContentSrc();
	            // 产品要素
	            logger.debug("产品要素数据接入批处理开始");
	            if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
	            	resultList = prodInfoInterfService.getIssueData(batchDate);
	            } else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc) 
	                    || InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
                    String currentDate=DateUtil.format(batchDate);
                    String result = prodInfoFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.产品基本信息接口数据.getCode());
	                 if ("fail".equals(result)){
	                     throw new BusinessException("fail");
	                 }
	            } else {
	                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
	            }
	            
			logger.debug("产品要素数据接入批处理结束");
			logInfoBatchEnd();
			return resultList;
	    }
}
