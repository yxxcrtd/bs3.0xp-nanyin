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

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;



/**
 * 报表模板管理新建mapper 
 * 
 * @author longhao
 * @version 1.0 2018年11月7日
 * @see ReportPageManage001Mapper
 * @since 1.0
 */
@Repository
public interface PoiReportModelManage002Mapper {
	
	   List<SelectOptionDto>	getInfoSelect();

}
