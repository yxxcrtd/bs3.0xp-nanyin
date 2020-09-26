/*
 * 文件名：ReportUpInterfMonitor001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统
 * 修改人：hujing
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统
 */
package com.joyintech.tams.report.system.upinterfmonitor.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.system.upinterfmonitor.dto.ReportUpInterfMonitor001Dto;

/**
 * 上游接口文件处理监控mapper文件
 * 〈功能详细描述〉
 * @author hujing
 * @version 1.0 2018年8月10日
 * @see ReportUpInterfMonitor001Mapper
 * @since 1.0
 */
@Repository
public interface ReportUpInterfMonitor001Mapper {

	 /**
     * 获取 数据文件名
     * @return 结果list
     * @see
     * @since 1.0
     */
	List<SelectOptionDto> queryHandleFileNameList();

	List<ReportUpInterfMonitor001Dto> searchForPage(Map<String, Object> param);
	 
	List<Map<String,String>> getFilePath(@Param("listSeqNo") List<String> listSeqNo);

}
