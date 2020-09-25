/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dto.PoiReportModelManage003Dto;

/**
 * 报表模板管理导出报表mapper
 * 
 * @author zhangyf
 * @version 2.0 2020年2月21日
 * @see PoiReportModelManage003Mapper
 * @since 1.0
 */
@Repository
public interface PoiReportModelManage003Mapper {

	/**
	 * 获取报表生成条件集合
	 * 
	 * @param dto
	 * @return
	 */
	List<PoiReportModelManage003Dto> getReportSearchConditions(Map<String, Object> map);

	/**
	 * 根据主键查询生成条件信息
	 * @param map
	 * @return
	 */
	PoiReportModelManage003Dto selectDisPramsByKey(Map<String, Object> map);

	/**
	 * 查询已确认的数据
	 * 
	 * @param repReveal
	 * @return
	 */
	int selectConfirmStatus(Map<String, Object> params);
	
	/**
	 * 查询相同的确认数据
	 * 
	 * @param params
	 * @return
	 */
	int querySameCount(Map<String, Object> params);
}
