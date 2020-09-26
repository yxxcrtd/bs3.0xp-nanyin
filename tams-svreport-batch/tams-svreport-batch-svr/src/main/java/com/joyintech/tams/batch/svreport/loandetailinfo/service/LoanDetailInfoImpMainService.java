package com.joyintech.tams.batch.svreport.loandetailinfo.service;


import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.LoanDetailInfoFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(value = "LoanDetailInfoImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class LoanDetailInfoImpMainService extends AutoSendCBBaseService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoanDetailInfoFileImpService loanDetailInfoFileImpService;

    public void generateLoanDetailData(Date batchDate, List<String> prodList, boolean isSynchronizeTask) {
        setBatchBeanName(BatchBeanName.贷款明细信息接口数据接入);
        logInfoBatchStart();
            ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.贷款明细信息接口数据.getCode());
            if (interfConfig == null) {
                logger.info("CHB_INTERF_CONFIG表中无配置项");
            }
            String contentSrc = interfConfig.getContentSrc();

            logger.debug("贷款明细信息数据接入批处理开始");
            if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)) {

            } else if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)
                    || InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
                String result = loanDetailInfoFileImpService.execute(batchDate, InterfDefine.贷款明细信息接口数据.getCode());
                if ("fail".equals(result)) {
                    throw new BusinessException("fail");
                }
            } else {
                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效!");
            }
        logger.debug("贷款明细信息数据接入批处理结束");
        logInfoBatchEnd();
    }
}
