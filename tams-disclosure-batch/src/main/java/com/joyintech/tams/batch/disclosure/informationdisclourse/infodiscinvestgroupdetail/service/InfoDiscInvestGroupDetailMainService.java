package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscinvestgroupdetail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscinvestgroupdetail.servcie.InfoDiscInvestGroupDetailService;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;

/**
 * 投资组合详细mainService
 * 
 * @author ZhangYangFei
 *
 */
@Service
public class InfoDiscInvestGroupDetailMainService extends AutoSendCBBaseService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private InfoDiscInvestGroupDetailService infoDiscInvestGroupDetailService;

	/**
	 * 投资组合详细批处理执行
	 * 
	 * @param executeDateStr
	 */
	public void generateData(String executeDateStr) {
		setBatchBeanName(BatchBeanName.投资组合详细批处理);
		logInfoBatchStart();
		try {
			infoDiscInvestGroupDetailService.genDetailData(executeDateStr);
		} catch (Exception e) {
			logger.error("error:", e);
			logger.error(BatchBeanName.投资组合详细批处理.getBatchName() + "任务执行失败，" + executeDateStr);
		}
		logInfoBatchEnd();
	}
}
