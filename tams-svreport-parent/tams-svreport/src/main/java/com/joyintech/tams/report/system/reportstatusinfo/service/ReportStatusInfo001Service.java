/*
 * 文件名：ReportStatusInfo001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：徽银理财资管系统
 * 修改人：wanghai
 * 修改时间：2021年5月10日
 * 修改内容：新建
 * 系统名称：徽银理财资管系统
 */
 
package com.joyintech.tams.report.system.reportstatusinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.system.reportstatusinfo.dto.ReportStatusInfo001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 发送状态查询页面
 * 〈功能详细描述〉
  * @author wanghai
 * @version 1.0 2021年5月10日
 * @see ReportStatusInfo001Service
 * @since 1.0
 */
@Service
public class ReportStatusInfo001Service extends BaseService{
    
    /**
    *
    * 取得查询数据
    *
    * @return 存在于查询条件的数据
    */
    public Page<ReportStatusInfo001Dto> searchInfo(Map<String, Object> param) {
    	return simpleRestTemplate.postForPage(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ReportStatusInfo/findPage", param,
                ReportStatusInfo001Dto.class);
    }

}
