/*
 * 文件名：InfoDiscPreReadAndGenMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscprereadandgen.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.base.entity.RepJob;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.service.InfoDisclosureReadAndGenDetailService;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

/**
 * 信息披露生成报表并发送service
 * @author wangmin
 * @version 1.0 2018年12月03日
 * @see InfoDiscPreReadAndGenMainService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InfoDiscPreReadAndGenMainService extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 详细处理service
     */
    @Autowired
    private InfoDisclosureReadAndGenDetailService infoDisclosureReadAndGenDetailService;
    /**
     * 详细处理mapper
     */
    @Autowired
    private InfoDisclosureReadAndGenMapper infoDisclosureReadAndGenMapper;
    
    public void generateData(String executeDateStr) {
    	
    	setBatchBeanName(BatchBeanName.预处理作业信息);
    	
    	logInfoBatchStart();
    	//1.查询出满足要求的作业信息
    	//预处理天数
		int preGenDays = Integer.parseInt(CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.PRE_GEN_DATE));
		//预处理时间为加上配置的天数
		String preGenDate = DateUtil.addDay(executeDateStr, preGenDays);
		
    	List<RepJob> repJobList =  infoDisclosureReadAndGenMapper.getJobList(preGenDate);
    	if(CollectionUtils.isEmpty(repJobList)){
    		logger.info("无可执行的作业");
    	}
    	else{
    		//循环遍历作业表
    		Map<String,Date> dateMap = new HashMap<String,Date>();
    		for (RepJob repJob : repJobList) {
				try {
					infoDisclosureReadAndGenDetailService.preReadAndGenDetail(repJob, dateMap, DateUtil.parse(preGenDate));
				} catch (Exception e) {
//					e.printStackTrace();
					logger.error("error:", e);
					logger.info("作业id为:"+repJob.getJobId()+",作业name为:"+repJob.getJobName()+"的作业生成失败");
				}
			}
    	}
    	logInfoBatchEnd();
    }
}
