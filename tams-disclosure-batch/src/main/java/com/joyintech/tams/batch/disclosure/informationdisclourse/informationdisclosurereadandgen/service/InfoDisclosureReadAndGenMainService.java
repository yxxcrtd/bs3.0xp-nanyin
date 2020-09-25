/*
 * 文件名：InfoDisclosureReadAndGenMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月03日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.informationdisclosurereadandgen.service;



import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.base.entity.RepJob;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.service.InfoDisclosureReadAndGenDetailService;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;

/**
 * 信息披露生成报表并发送service
 * @author wangmin
 * @version 1.0 2018年12月03日
 * @see InfoDisclosureReadAndGenMainService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InfoDisclosureReadAndGenMainService extends AutoSendCBBaseService{
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
    	
    	setBatchBeanName(BatchBeanName.读取作业信息并生成待处理信息);
    	
    	logInfoBatchStart();
    	//1.查询出满足要求的作业信息
    	List<RepJob> repJobList =  infoDisclosureReadAndGenMapper.getJobList(executeDateStr);
    	if(CollectionUtils.isEmpty(repJobList)){
    		logger.info("无可执行的作业");
    	}
    	else{
    		//循环遍历作业表
    		//Map<String,Date> dateMap = new HashMap<String,Date>();
    		for (RepJob repJob : repJobList) {
				try {
					//infoDisclosureReadAndGenDetailService.readAndGenDetail(repJob, dateMap, DateUtil.parse(executeDateStr));
					infoDisclosureReadAndGenDetailService.readAndGenMain(repJob, DictCodeListExt2.执行标志_首次执行.getValue(), null, DateUtil.parse(executeDateStr));
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
