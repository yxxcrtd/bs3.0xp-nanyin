/*
 * 文件名：ValNetPlanQuotation002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
/**
 * 计划类资产净值信息双击查看service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月16日
 * @see ValNetPlanQuotation002Service
 * @since 1.0
 */
/**
 * 计划类资产净值信息双击查看
 */
@Service
public class ValNetPlanQuotation002Service extends CommonExportService<ValNetPlanQuotation002Dto> {

	/**
     * 双击查看service
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @param valueDate
     * @return
     */
	public List<ValNetPlanQuotation002Dto> searchList(ValNetPlanQuotation002Dto dto) {
      //  String valueDateFmt=  DateUtil.format(DateUtil.parse(valueDate),"yyyy-MM-dd");
//		return valNetPlanQuotation002Mapper.searchList(finprodId, valueDate);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetPlanQuotation002/searchList",
                dto, ValNetPlanQuotation002Dto.class);
	}

}
