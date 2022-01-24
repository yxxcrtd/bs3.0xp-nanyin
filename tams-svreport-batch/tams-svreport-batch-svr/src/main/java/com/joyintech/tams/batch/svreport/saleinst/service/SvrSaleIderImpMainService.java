package com.joyintech.tams.batch.svreport.saleinst.service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.SaleIderFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售人员身份信息接入批处理
 * @author
 * @version 3.0
 * @see
 * @since 3.0
 */
@Service(value = "SvrSaleIderImpMainService")
@Scope("prototype")
public class SvrSaleIderImpMainService extends AutoSendCBBaseService{
	 /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SaleIderFileImpService saleIderFileImpService;
	 public List<String> generateSaleInstData(Date batchDate,List<String> prodIdList) {
	    	// 设置批处理bean
	    	setBatchBeanName(BatchBeanName.销售人员身份信息接入);
			logInfoBatchStart();
			List<String> resultList= new ArrayList<String>();
			  ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.销售人员身份信息接口数据.getCode());
	            if (interfConfig == null){
	                logger.info("CHB_INTERF_CONFIG表中无配置项！");
	            }
	            String contentSrc = interfConfig.getContentSrc();
	            logger.debug("销售人员身份信息接入批处理开始");
	            if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
					logger.info("暂无升级此项！");
	            } else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc) 
	                    || InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
                    String currentDate=DateUtil.format(batchDate);
                    String result = saleIderFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.销售人员身份信息接口数据.getCode());
	                 if ("fail".equals(result)){
	                     throw new BusinessException("fail");
	                 }
	            } else {
	                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
	            }
	            
			logger.debug("销售人员身份信息接入批处理结束");
			logInfoBatchEnd();
			return resultList;
	    }
}
