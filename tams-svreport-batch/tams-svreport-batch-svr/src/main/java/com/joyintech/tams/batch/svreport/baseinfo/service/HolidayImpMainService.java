package com.joyintech.tams.batch.svreport.baseinfo.service;

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

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.HolidayFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;

/**
 * @Description 节假日信息接口批处理
 * @author jiangxiao
 * @version 1.7 2019年5月10日
 * @since 1.7
 */
@Service(value = "holidayImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class HolidayImpMainService extends AutoSendCBBaseService{
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private HolidayFileImpService holidayFileImpService;
    
	public Map<String, List<String>> generateHolidayData(Date batchDate) {
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		// 设置批处理bean
		setBatchBeanName(BatchBeanName.节假日数据接入);
		logInfoBatchStart();
		try {
			// 获取配置项
			ChbInterfConfigDto interfConfig = ChbInterfUtil
					.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.节假日数据.getCode());
			if (interfConfig == null) {
				logger.info("CHB_INTERF_CONFIG表中无配置项！");
			}
			String contentSrc = interfConfig.getContentSrc();
			// 评级信息文件接入
			if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
				logger.debug("节假日信息一体化数据公用");
            } else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc) 
            		|| InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
            	String result = holidayFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.节假日数据.getCode());
            	if("fail".equals(result)){
					throw new BusinessException("fail");
				}
			} else {
				logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
			}
		} catch (Exception e) {
			logger.error("节假日信息接口批处理Error",e);
			throw e;
		}
		logInfoBatchEnd();
		return mapPara;
	}
}
