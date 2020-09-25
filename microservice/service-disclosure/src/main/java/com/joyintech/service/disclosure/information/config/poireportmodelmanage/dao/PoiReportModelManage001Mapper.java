/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.disclosure.information.config.poireportmodelmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.config.reportmodelmanage.ReportModelManage001Dto;
import com.joyintech.service.disclosure.information.config.reportpage.dao.ReportPageManage001Mapper;
import com.joyintech.tams.common.base.entity.DisPoiTab;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author dongxiangyang
 * @version 1.0 2018年11月7日
 * @see ReportPageManage001Mapper
 * @since 1.0
 */
@Repository(value="servicePoiReportModelManage001Mapper")
public interface PoiReportModelManage001Mapper {

	List<DisPoiTab> searchReports(Map<String, Object> params);
	
    int deleteByPrimaryKey(String tabNum);


}
