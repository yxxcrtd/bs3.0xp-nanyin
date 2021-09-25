/*
 * 文件名：ChbFeedbackFileManagement001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：徽银理财资管系统
 * 修改人：wanghai
 * 修改时间：2021年5月10日
 * 修改内容：新建
 * 系统名称：徽银理财资管系统
 */
 
package com.joyintech.tams.report.system.chbfeedbackfilemanagement.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagement001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 中债系统报送反馈文件信息
 * 〈功能详细描述〉
  * @author wanghai
 * @version 1.0 2021年5月10日
 * @see ChbFeedbackFileManagement001Service
 * @since 1.0
 */
@Service
public class ChbFeedbackFileManagement001Service extends BaseService<ChbFeedbackFileManagement001Dto>{
    
    /**
    *
    * 取得查询数据
    *
    * @return 存在于查询条件的数据
    */
    public Page<ChbFeedbackFileManagement001Dto> searchInfo(Map<String, Object> param) {
    	return simpleRestTemplate.postForPage(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbFeedbackFileManagement/findPage", param,
				ChbFeedbackFileManagement001Dto.class);
    }

    /**
     * 备份文件
     * @param dto
     * @return
     */
    public AjaxResult backup(ChbFeedbackFileManagement001Dto dto) {
        String msg = simpleRestTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbFeedbackFileManagement/backupFile", dto,
                String.class);
        return new AjaxResult(msg);
    }
}
