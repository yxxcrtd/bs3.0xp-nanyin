/*
 * 文件名：CheckReport001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitexamination.checkreport.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;

/**
 * 报送前检查画面
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see CheckReportMService
 * @since 1.0
 */
@Service
public class CheckReportMService extends CommonExportService<CheckReport001Dto>{	
    /**
     * 查询列表
     *
     * @return Page
     */
    public Page<CheckReport001Dto> searchTable(Map<String, Object> param){
    	return restTemplate.postForPage(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/CheckReport/searchTable",param,
        		CheckReport001Dto.class);
    }

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config(
	            "/com/joyintech/tams/report/submitexamination/checkreport/conf/export_conf.xml").dataSource(
	                DataSourceType.MASERT);
	}

	@Override
	public Page<CheckReport001Dto> selectPageData(Map<String, Object> selectParam) {
		// TODO Auto-generated method stub
		return searchTable(selectParam);
	}

}
