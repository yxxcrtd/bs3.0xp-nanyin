/*
 * 文件名：ValProfitQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.busicommon.common.dto.BokBalance;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisProfitCalculationDto;

/**
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery001Mapper
 * @since 1.0
 */
@Repository
public interface DisValProfitQuery001Mapper {
	/**
	 * 根据条件查询科目余额信息
	 * 
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<BokBalance> selectAllBalance(Map<String, Object> pageParam);

	/**
	 * 根据条件查询科目余额信息
	 * 
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<BokBalance> selectAllBalanceAll(Map<String, Object> pageParam);

	/**
	 * 通用联动查询
	 * 
	 * @param param
	 *            : dict_code_parent,dict_code_child,item_code_parent
	 * @return
	 */
	List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param);

	/**
	 * 查询利润表表样
	 * 
	 * @return
	 */
	List<DisProfitCalculationDto> selectProfitCalculationDtoList();

	/**
	 * 根据条件查询科目余额信息 xy
	 * 
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<BokBalance> selectAllBalanceXY(Map<String, Object> pageParam);

	/**
	 * 根据条件查询科目余额信息 本位币
	 * 
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<BokBalance> selectAllBalancebAmt(Map<String, Object> pageParam);

	/** 一體化 查询利润表表样 add by dongxiangyang 20200211 start */
	List<DisProfitCalculationDto> selectProfitCalculationDtoListYth();
	/** 一體化 查询利润表表样 add by dongxiangyang 20200211 end */
	
	List<BokBalance> selectAllBalancebAmtYth(Map<String, Object> pageParam);
}
