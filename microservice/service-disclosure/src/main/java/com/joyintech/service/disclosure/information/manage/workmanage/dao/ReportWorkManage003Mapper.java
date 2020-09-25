package com.joyintech.service.disclosure.information.manage.workmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage003Dto;
/**
 * 
 * 作业任务管理-产品预览Mapper
 * @author wanghb
 * @version 1.0 2018年12月6日
 * @see ReportWorkManage003Mapper
 * @since 1.0
 */
@Repository(value="serviceReportWorkManage003Mapper")
public interface ReportWorkManage003Mapper {
	/**
	 * 查询预览产品列表
	 * @param settedRule
	 * @return
	 */
	List<ReportWorkManage003Dto> findProdPage(Map<String,Object> map);
}
