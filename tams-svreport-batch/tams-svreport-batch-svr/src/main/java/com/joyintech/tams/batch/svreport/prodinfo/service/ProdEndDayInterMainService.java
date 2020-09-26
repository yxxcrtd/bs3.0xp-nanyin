package com.joyintech.tams.batch.svreport.prodinfo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.prodinfo.service.ChbPrdEndDayYieldInterf;

/**
 * 每日日终接取收益信息接口
 * 
 * @author kangxu
 *
 */
@Service(value = "prodEndDayInterMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ProdEndDayInterMainService extends AutoSendCBBaseService{

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    
    @Autowired
	private ChbPrdEndDayYieldInterf chbPrdEndDayYieldInterf;
    
	public Map<String, List<String>> generatePrdPeriodyieldData(Date batchDate,List<String> prodidList) throws Exception {
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		// 设置批处理bean
		setBatchBeanName(BatchBeanName.每日日终接取收益信息接口数据接入);
		logInfoBatchStart();
		try {
//			// 获取配置项
//			ChbInterfConfigDto interfConfig = ChbInterfUtil
//					.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.每日日终接取收益信息接口数据.getCode());
//			if (interfConfig == null) {
//				logger.info("CHB_INTERF_CONFIG表中无配置项！");
//			}
//			String contentSrc = interfConfig.getContentSrc();
//			if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
				// 报价信息数据库接入
				chbPrdEndDayYieldInterf.generatePrdPeriodyieldData(batchDate, prodidList, false);
//            }
//			else {
//				logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
//			}
		} catch (Exception e) {
			logger.error("每日日终接取收益信息接口数据接入Error",e);
			throw e;
		}
		logInfoBatchEnd();
		return mapPara;
	}
	
}
