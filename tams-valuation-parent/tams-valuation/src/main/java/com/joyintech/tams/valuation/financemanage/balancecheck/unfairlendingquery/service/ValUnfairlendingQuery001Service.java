/*
 * 文件名：ValUnfairlendingQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2019年01月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.financemanage.balancecheck.unfairlendingquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.financemanage.balancecheck.unfairlendingquery.dao.ValUnfairlendingQuery001Mapper;
import com.joyintech.tams.valuation.financemanage.balancecheck.unfairlendingquery.dto.ValUnfairlendingQuery001Dto;

/**
 * 借贷不平账务查询
 * 
 * @author wanwgei1
 * @version 1.0 2019年01月28日
 * @see ValUnfairlendingQuery001Service
 * @since 1.0
 */
@Service
public class ValUnfairlendingQuery001Service extends CommonExportService<ValUnfairlendingQuery001Dto> {

	@Autowired
	private ValUnfairlendingQuery001Mapper valUnfairlendingQuery001Mapper;

	/**
	 * 查询所有账套 <br/>
	 * 用于所属账套控件初期化
	 * 
	 * @return SelectOptionDto list
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> findBooksets() {
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValUnfairlendingQuery001/findBooksets",
                null, SelectOptionDto.class);
	}

	/**
	 * 格式化创建SelectOptionDto对象 <功能详细描述>
	 * 
	 * @param selectId
	 *            下拉Id值
	 * @param text
	 *            下拉文本值
	 * @return dto
	 * @see
	 * @since 1.0
	 */
	public SelectOptionDto createSelect(String selectId, String text) {
		SelectOptionDto dto = new SelectOptionDto();
		dto.setId(selectId);
		dto.setText(text);
		return dto;
	}

	/**
	 * 根据条件获取表格信息
	 * 
	 * @param params
	 *            筛选条件
	 * @return 表格信息
	 * @throws Exception
	 */
	public Page<ValUnfairlendingQuery001Dto> findPage(Map<String, Object> params) throws Exception {
		
		 return restTemplate.postForPage(
	                ServiceApplication.VALUATION_SERVICE_NAME + "/ValUnfairlendingQuery001/findPage",
	                params, ValUnfairlendingQuery001Dto.class);
	}

	public List<ValUnfairlendingQuery001Dto> queryUnfairlendingList(ValUnfairlendingQuery001Dto dto) {
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValUnfairlendingQuery001/queryUnfairlendingList",
                dto, ValUnfairlendingQuery001Dto.class);
	}

}
