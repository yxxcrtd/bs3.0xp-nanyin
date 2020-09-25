/*
 * 文件名：InfoDiscAutoConfirmMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoconfirm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoconfirm.dao.InfoDiscAutoConfirmMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;

/**
 * 自动确认service
 * @author wangmin
 * @version 1.0 2018年12月8日
 * @see InfoDiscAutoConfirmMainService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InfoDiscAutoConfirmMainService extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * mapper
     */
    @Autowired
    private InfoDiscAutoConfirmMapper infoDiscAutoConfirmMapper;
    
    /**
	/**
     * 自动确认
     * @param executeDate 
     *
     * @see
     * @since 1.0
     */
    public void generateData(String executeDateStr) {
    	setBatchBeanName(BatchBeanName.自动确认批处理);

    	logInfoBatchStart();
    	try{
    		int result = infoDiscAutoConfirmMapper.updateAutoConfirm(DictCodeListExt1.披露确认状态_已确认.getValue(),
   				 DictCodeListExt1.确认类型_自动.getValue(),DictCodeListExt1.披露确认状态_未确认.getValue());
    		logger.info("自动确认"+result+"条数据！");
    	}
    	catch(Exception e){
//    		e.printStackTrace();
    		logger.error("error:", e);
    		logger.info("自动确认失败"+DateUtil.getCurrentDateTime());
    	}
    	logInfoBatchEnd();
    }
}
