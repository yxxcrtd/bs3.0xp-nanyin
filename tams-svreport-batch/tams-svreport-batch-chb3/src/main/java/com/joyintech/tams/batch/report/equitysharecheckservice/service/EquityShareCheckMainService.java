package com.joyintech.tams.batch.report.equitysharecheckservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.equitysharecheckservice.dao.EquityShareCheckMapper;
import com.joyintech.tams.report.common.constant.BatchBeanName;


/**
 * 投资者份额检查批处理service
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年12月9日
 * @see EquityShareCheckMainService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EquityShareCheckMainService extends AutoSendCBBaseService{
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 投资者份额检查批处理mapper
	 */
	@Autowired
	private EquityShareCheckMapper mapper;
	
	
	/**
	 * 投资者份额检查批处理方法
	 * 
	 * @param executeDateStr
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public void doEquityShareCheck(String executeDateStr) {
		setBatchBeanName(BatchBeanName.投资者份额检查批处理);
		
			logInfoBatchStart();
			logger.info("清除"+executeDateStr+"日期投资者份额检查结果开始！");
			mapper.deleteChbEqudetCheckResult(executeDateStr);
			logger.info("清除"+executeDateStr+"日期投资者份额检查结果结束！");
			logger.info("插入"+executeDateStr+"日期投资者份额检查结果开始！");
			mapper.insertChbEqudetCheckResult(executeDateStr);
			logger.info("插入"+executeDateStr+"日期投资者份额检查结果结束！");
			logInfoBatchEnd();
		
	}
}
