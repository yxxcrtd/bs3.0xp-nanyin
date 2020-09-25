package com.joyintech.tams.batch.disclosure.informationdisclourse.prodtrd.servcie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.prodtrd.servie.InfoDisProdtrdDetailService;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;

/**
 * 产品申赎信息mainService
 * 
 * @author ZhangYangFei
 *
 */
@Service
public class InfoDisProdtrdMainService extends AutoSendCBBaseService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private InfoDisProdtrdDetailService infoDisProdtrdDetailService;

	/**
	 * 投资组合详细批处理执行
	 * 
	 * @param executeDateStr
	 */
	public void generateData(String executeDateStr) {
		setBatchBeanName(BatchBeanName.生成产品申赎信息批处理);
		logInfoBatchStart();
		try {
			infoDisProdtrdDetailService.genDetailData(executeDateStr);
		} catch (Exception e) {
			logger.error("error:", e);
			logger.error(BatchBeanName.生成产品申赎信息批处理.getBatchName() + "任务执行失败，" + executeDateStr);
		}
		logInfoBatchEnd();
	}
}
