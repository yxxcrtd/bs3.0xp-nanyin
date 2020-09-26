package com.joyintech.tams.batch.svreport.cprdbalance.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.CprodBalanceFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.assetbasis.service.SvrAssetBasisInterf;
import com.joyintech.tams.svreport.interf.chb.cprdbalance.service.ChbCprodBalanceInterf;


@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SvrCprodBalanceImpMainService extends AutoSendCBBaseService{
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private CprodBalanceFileImpService cprodBalanceFileImpService;
    
    @Autowired
    private ChbCprodBalanceInterf chbCprodBalanceInterf;
    
    /**
     * 功能:根据跑批日自动接入资产基础指标数据
     * @param batchDate 跑批日
     * @prodList 产品代码
     * void
     */
    public void generateCprodBalanceData(Date batchDate){
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.周期型产品余额数据接入);
    	logInfoBatchStart();
    	try{
    		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.周期型产品余额接口数据.getCode());
            if (interfConfig == null){
                logger.info("CHB_INTERF_CONFIG表中无配置项！");
            }
            
            String contentSrc = interfConfig == null ? null : interfConfig.getContentSrc();
            if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)||InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
            	cprodBalanceFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.周期型产品余额接口数据.getCode());
            } else if(InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
            	chbCprodBalanceInterf.generateData(batchDate);
            }else {
                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
            }
    	}catch(Exception e){
    		throw e;
    	}
    	logger.debug("周期型产品余额数据接入批处理");
		logInfoBatchEnd();
    }
}
