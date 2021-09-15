package com.joyintech.tams.batch.svreport.investmentsinfo.service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.DateUtil;
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

import java.text.ParseException;
import java.util.*;

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
                //跑批日期所在的季度末日期
                Date quarterEndDate = getLastDayOfQuarter(batchDate);
                //跑批日期是否为季度末
                if(DateUtil.compareDate(batchDate, quarterEndDate) == 0){
                    // 产品收益率信息数据库接入
                    chbAssetInfoRateInterf.generateAssetInfoRateData(batchDate, prodidList, false);
                }
            } else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)
                    || InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {

            } else {
                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
            }
        } catch (Exception e) {
            logger.error("资产收益率信息接口数据接入Error",e);
            throw new BusinessException(e);
        }
        logInfoBatchEnd();
        return mapPara;
    }
    /**
     * 返回指定日期的季的最后一天
     * Calendar 的 month 从 0 开始，也就是全年 12 个月由 0 ~ 11 进行表示。
     * 而 Calendar.DAY_OF_WEEK 定义和值如下：
     *  Calendar.SUNDAY = 1  Calendar.MONDAY = 2  Calendar.TUESDAY = 3
     *  Calendar.WEDNESDAY = 4  Calendar.THURSDAY = 5  Calendar.FRIDAY = 6  Calendar.SATURDAY = 7
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfQuarter(Date date)
            throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int quarterOfYear = calendar.get(Calendar.MONTH) / 3 + 1;
        int month = 0;
        switch (quarterOfYear) {
            case 1:
                month = 2;
                break;
            case 2:
                month = 5;
                break;
            case 3:
                month = 8;
                break;
            case 4:
                month = 11;
                break;
            default:
                month = calendar.get(Calendar.MONTH);
        }
        calendar.set(calendar.get(Calendar.YEAR), month, 1);
        return DateUtil.getLastDay(calendar.getTime());
    }
}
