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

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.ProdOpenschFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.prodinfo.service.ChbPrdOpenschInterf;

/**
 * @Description 开放计划接口
 * @author jiangxiao
 * @version 1.7 2019年5月10日
 * @since 1.7
 */
@Service(value = "prodPlanImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ProdPlanImpMainService extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ProdOpenschFileImpService prodOpenschFileImpService;
    
    @Autowired
	private ChbPrdOpenschInterf chbPrdOpenschInterf;
    
	public Map<String, List<String>> generatePrdOpensch(Date batchDate,List<String> prodidList) {
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		// 设置批处理bean
		 setBatchBeanName(BatchBeanName.产品开放计划接口数据接入);
		logInfoBatchStart();
		try {
			// 获取配置项
			ChbInterfConfigDto interfConfig = ChbInterfUtil
					.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.产品开放计划接口数据.getCode());
			if (interfConfig == null) {
				logger.info("CHB_INTERF_CONFIG表中无配置项！");
			}
			String contentSrc = interfConfig.getContentSrc();
			if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
				// 开放计划数据库接入
				chbPrdOpenschInterf.generatePrdOpenschData(batchDate, prodidList);
            } else  if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc) 
            		|| InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
            	// 开放计划文件接入
            	String result = prodOpenschFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.产品开放计划接口数据.getCode());
            	if("fail".equals(result)){
					throw new BusinessException("fail");
				}
			} else {
				logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
			}
		} catch (Exception e) {
			logger.error("产品开放计划接口数据接入Error",e);
			throw e;
		}
		logInfoBatchEnd();
		return mapPara;
	}
}
