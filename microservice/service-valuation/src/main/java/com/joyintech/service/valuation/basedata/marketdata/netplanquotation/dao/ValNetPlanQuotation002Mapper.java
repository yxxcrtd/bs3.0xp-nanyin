/*
 * 文件名：ValNetPlanQuotation002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation002Dto;

@Repository(value="serviceValNetPlanQuotation002Mapper")
public interface ValNetPlanQuotation002Mapper {
	
	  /**
	   * 根据资产代码和行情日期查看计划类资产净值信息
	   * <一句话功能简述>
	   * <功能详细描述>
	   * @param finprodId
	   * @param valueDate
	   * @return
	   */
	  List<ValNetPlanQuotation002Dto> searchList(ValNetPlanQuotation002Dto dto);
}
