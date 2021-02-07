package com.joyintech.tams.batch.svreport.investmentsinfo.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.investmentsinfo.service.ChbAssetInfoRateInterf;
import com.joyintech.tams.svreport.interf.chb.investmentsinfo.service.ChbProdInfoRateInterf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产利率接入
 */
@Service(value = "SvrAssetRateInfoImpMainService")
@Scope("prototype")
public class SvrAssetRateInfoImpMainService extends AutoSendCBBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private ChbAssetInfoRateInterf chbAssetInfoRateInterf;

    public Map<String, List<String>> generateAssetRateData(Date batchDate, List<String> prodidList) {
        Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.资产利率基本信息接口数据接入);
        logInfoBatchStart();
        try {
            // 获取配置项
            ChbInterfConfigDto interfConfig = ChbInterfUtil
                    .queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.资产利率基本信息接口数据.getCode());
            if (interfConfig == null) {
                logger.info("CHB_INTERF_CONFIG表中无配置项！");
            }
            String contentSrc = interfConfig.getContentSrc();
            if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
                // 产品收益率信息数据库接入
                chbAssetInfoRateInterf.generateAssetInfoRateData(batchDate, prodidList, false);
            } else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)
                    || InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {

            } else {
                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
            }
        } catch (Exception e) {
            logger.error("资产收益率信息接口数据接入Error",e);
            throw e;
        }
        logInfoBatchEnd();
        return mapPara;
    }

}
