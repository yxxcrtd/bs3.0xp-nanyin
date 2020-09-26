package com.joyintech.tams.batch.svreport.feebasis.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.ChbPrdFeeBasisDataFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.feebasis.service.SvrChbPrdFeeBasisDBInterf;


@Service(value = "chbPrdFeeBasisImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ChbPrdFeeBasisImpMainService extends AutoSendCBBaseService{
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ChbPrdFeeBasisDataFileImpService chbPrdFeeBasisDataFileImpService;
    
    @Autowired
    private SvrChbPrdFeeBasisDBInterf svrChbPrdFeeBasisDBInterf;
    
    /**
     * 功能:根据跑批日自动接入产品费用交易头寸接口数据
     * @param batchDate 跑批日
     * @prodList 产品代码
     * void
     */
    public void generateSubInvestPlan(Date batchDate){
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.产品费用交易头寸接口数据接入);
    	logInfoBatchStart();
    	try{
    		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.产品费用头寸接口.getCode());
            if (interfConfig == null){
                logger.info("CHB_INTERF_CONFIG表中无配置项！");
            }
            
            String contentSrc = interfConfig == null ? null : interfConfig.getContentSrc();
            if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)||InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
            	chbPrdFeeBasisDataFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.产品费用头寸接口.getCode());
            } else if(InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
            	svrChbPrdFeeBasisDBInterf.generateData(batchDate);
            }else {
                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
            }
    	}catch(Exception e){
    		throw e;
    	}
    	logger.debug("产品费用交易头寸接口数据接入批处理");
		logInfoBatchEnd();
    }
}
