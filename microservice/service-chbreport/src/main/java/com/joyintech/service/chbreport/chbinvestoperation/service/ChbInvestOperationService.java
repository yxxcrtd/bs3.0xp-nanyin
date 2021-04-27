/*
 * 文件名：OperationManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.chbreport.chbinvestoperation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.chbreport.chbinvestoperation.dao.ChbInvestOperationMapper;
import com.joyintech.service.chbreport.chbinvestoperation.dto.ChbInvestPrdInfo;
import com.joyintech.service.chbreport.chbinvestoperation.dto.ChbInvsetAssetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("serviceChbInvestOperationService")
public class ChbInvestOperationService extends BaseService<ChbInvestPrdInfo>{
	
	@Autowired
	private ChbInvestOperationMapper chbInvestOperationMapper;

	/**
	 * 分页查询产品下拉框
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 */
	public Page<SelectOptionDto> queryPrdInfoPage(int page, int length, Map<String, Object> param){
		return this.queryForPageForSelectReturn(page, length, () -> chbInvestOperationMapper.queryPrdInfoList(param));
	}
	/**
	 * 分页查询非标投资运行报告
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<ChbInvestPrdInfo> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
		if (StringUtil.isNotEmpty(pageParam.get("dealdateStart"))) {
			pageParam.put("dealdateStart", DateUtil.parse((String)pageParam.get("dealdateStart")));
		}
		else {
			pageParam.put("dealdateStart", null);
		}
		if (StringUtil.isNotEmpty(pageParam.get("dealdateEnd"))) {
			pageParam.put("dealdateEnd", DateUtil.parse((String)pageParam.get("dealdateEnd")));
		}
		else {
			pageParam.put("dealdateEnd", null);
		}
		if(StringUtil.isNotEmpty(pageParam.get("prodId"))) {
			pageParam.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String)pageParam.get("prodId")));
		}
		else {
			pageParam.put("prodIdList", null);
		}

		return super.queryForPage(page,length,() -> chbInvestOperationMapper.qryInvestOperation(pageParam));
	}

	/**
	 * 查询投资运行报告中产品相关信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ChbInvestPrdInfo qryChbInvestPrdInfo(Map<String, Object> params) throws Exception {
		List<ChbInvestPrdInfo> list = chbInvestOperationMapper.qryChbInvestPrdInfo(params);
		if(!StringUtil.isEmptyList(list)) {
			return list.get(0);
		}
		else {
			//防止报空错误
			return new ChbInvestPrdInfo();
		}
	}

	/**
	 * 查询投资运行报告中产品相关信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<ChbInvsetAssetInfo> qryChbInvsetAssetInfo(Map<String, Object> params) throws Exception {
		if (StringUtil.isNotEmpty(params.get("dealdateFmt"))) {
			params.put("dealdate", DateUtil.parse((String)params.get("dealdateFmt")));
		}
		else {
			params.put("dealdate", null);
		}
		return chbInvestOperationMapper.qryChbInvsetAssetInfo(params);
	}
}
