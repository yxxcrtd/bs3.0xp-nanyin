/*
 * 文件名：ReportUpInterfMonitor001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统
 * 修改人：hujing
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统
 */
package com.joyintech.tams.report.system.upinterfmonitor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.upinterfmonitor.dao.ReportUpInterfMonitor001Mapper;
import com.joyintech.tams.report.system.upinterfmonitor.dto.ReportUpInterfMonitor001Dto;
import com.joyintech.tams.constant.DictCode;

/**
 * 上游接口文件读取处理监控Service 〈功能详细描述〉
 * 
 * @author hujing
 * @version 1.0 2018年8月10日
 * @see ReportUpInterfMonitor001Service
 * @since 1.0
 */
@Service
public class ReportUpInterfMonitor001Service extends BaseService<ReportUpInterfMonitor001Dto> {

	// 文件监控Mapper
	@Autowired
	private ReportUpInterfMonitor001Mapper reportUpInterfMonitor001;

	/**
	 * 获取数据文件名
	 * 
	 * @return 结果list
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> queryHandleFileNameList() {

		List<SelectOptionDto> dataList=reportUpInterfMonitor001.queryHandleFileNameList();
		
		return reportUpInterfMonitor001.queryHandleFileNameList();
	}

	/**
	 * 查询列表
	 * 
	 * @return 结果page
	 * @see
	 * @since 1.0
	 */
	public Page<ReportUpInterfMonitor001Dto> searchForPage(Map<String, Object> param) {
		//处理查询参数
//		String handleFileName=(String) param.get("handleFileName");
//		param.put("handleFileNameList", StringUtil.splitToList(GlobalConstant.COMMA, handleFileName));
		//文件处理类型
		String handleFileType = (String) param.get("handleFileType");
		//文件处理类型
		String fileHandleStatus = (String) param.get("fileHandleStatus");
		param.put("handleFileTypeList", StringUtil.splitToList(GlobalConstant.COMMA, handleFileType));
		param.put("fileHandleStatusList", StringUtil.splitToList(GlobalConstant.COMMA, fileHandleStatus));
		// 查询数据
		int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
		Page<ReportUpInterfMonitor001Dto> pageList = super.queryForPage(
				pagenum,legth,()->reportUpInterfMonitor001.searchForPage(param));
		// cnBondUpInterfTaskService.execute();
		// 数据处理
		format(pageList.getRoot(), true);
		return pageList;
	}

	// 格式化数据字典
	private void format(List<ReportUpInterfMonitor001Dto> dataList, boolean isSearch) {
		if (null != dataList) {
			for (ReportUpInterfMonitor001Dto reportUpInterfMonitor001Dto : dataList) {
				if (isSearch) {
					reportUpInterfMonitor001Dto.setHandleFileType(SysDataUtil.getNameFromDataDict(
							DictCode.上游接口文件类型, reportUpInterfMonitor001Dto.getHandleFileType()));
					reportUpInterfMonitor001Dto.setFileHandleStatus(SysDataUtil.getNameFromDataDict(
							DictCode.上游接口文件读取处理状态, reportUpInterfMonitor001Dto.getFileHandleStatus()));
				}
			}
		}

	}
}
