package com.joyintech.tams.batch.svreport.subjectbalance.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.SubjectBalanceFileImpService;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;

@Service(value = "subjectBalanceImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SubjectBalanceImpMainService extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SubjectBalanceFileImpService subjectBalanceFileImpService;
    
    /**
     * 功能:根据跑批日自动接入科目余额数据
     * @param batchDate 跑批日
     * @prodList 产品代码
     * void
     */
    public void generateSubjectBalanceData(Date batchDate,List<String> prodList,boolean isSynchronizeTask){
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.科目余额数据接入);
    	logInfoBatchStart();
    	try{
    		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.科目余额数据.getCode());
            if (interfConfig == null){
                logger.info("CHB_INTERF_CONFIG表中无配置项！");
            }
            String contentSrc = interfConfig == null ? null : interfConfig.getContentSrc();
            if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)||InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
            	String result = subjectBalanceFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.科目余额数据.getCode());
            	if("fail".equals(result)){
            		throw new BusinessException("fail");
            	}
            } else {
                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
            }
    	}catch(Exception e){
    		throw e;
    	}
    	logger.debug("资产基础指标表上游数据接入批处理");
		logInfoBatchEnd();
    }
}
