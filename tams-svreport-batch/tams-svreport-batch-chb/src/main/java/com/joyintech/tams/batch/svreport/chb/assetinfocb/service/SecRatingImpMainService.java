package com.joyintech.tams.batch.svreport.chb.assetinfocb.service;

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
import com.joyintech.tams.common.SecRatingFileImpService;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;


/**
 * @Description 评级信息接口
 * @author jiangxiao
 * @version 1.7 2019年5月10日
 * @since 1.7
 */
@Service(value = "secRatingImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SecRatingImpMainService extends AutoSendCBBaseService{

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SecRatingFileImpService secRatingFileImpService;
    
	public Map<String, List<String>> generateSecRatingData(Date batchDate) {
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		// 设置批处理bean
		setBatchBeanName(BatchBeanName.债券评级数据接入);
		logInfoBatchStart();
		try {
			// 获取配置项
			ChbInterfConfigDto interfConfig = ChbInterfUtil
					.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.债券评级数据.getCode());
			if (interfConfig == null) {
				logger.info("CHB_INTERF_CONFIG表中无配置项！");
			}
			String contentSrc = interfConfig.getContentSrc();
			// 评级信息文件接入
			if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
				logger.debug("评级信息一体化数据公用");
            } else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc) 
            		|| InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
				String result = secRatingFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.债券评级数据.getCode());
				if("fail".equals(result)){
					throw new BusinessException("fail");
				}
			} else {
				logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
			}
		} catch (Exception e) {
			logger.error("债券评级数据接入接口Error",e);
			throw e;
		}
		logInfoBatchEnd();
		return mapPara;
	}
}
