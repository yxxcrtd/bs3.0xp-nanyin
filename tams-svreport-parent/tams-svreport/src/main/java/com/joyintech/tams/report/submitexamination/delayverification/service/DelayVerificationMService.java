/*
 * 文件名：DelayVerification001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitexamination.delayverification.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification001Dto;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification002Dto;

/**
 * 延时报送检查
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see DelayVerificationMService
 * @since 1.0
 */
@Service
public class DelayVerificationMService extends BaseService<DelayVerification001Dto>{
	
    /**
     * 查询列表
     * 
     * @return Map<String,DelayVerification001Dto>
     */
    @SuppressWarnings("unchecked")
    public Map<String,DelayVerification001Dto> searchDate(Map<String, Object> param,String indexType){
    	Map<String, Object> map = new HashMap<>();
    	map.put("param", param);
    	map.put("indexType", indexType);
    	return restTemplate.postForObject(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/DelayVerification/searchDate",map,
        		Map.class);
    }
    
    /**
     * 查询列表
     * 
     * @return Page
     */
    public Page<DelayVerification002Dto> searchTable(Map<String, Object> param){
    	return restTemplate.postForPage(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/DelayVerification/searchTable",param,
        		DelayVerification002Dto.class);
    }
}
