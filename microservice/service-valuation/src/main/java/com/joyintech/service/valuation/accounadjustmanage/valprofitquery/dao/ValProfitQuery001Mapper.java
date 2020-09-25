/*
 * 文件名：ValProfitQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokBookset;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.BokBalanceDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ProfitCalculationDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ValProfitQuery001Dto;

/**
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValProfitQuery001Mapper")
public interface ValProfitQuery001Mapper {
    /**
     * 根据条件查询科目余额信息
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<BokBalanceDto> selectAllBalance(Map<String, Object> pageParam);
    
    /**
     * 根据条件查询科目余额信息
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<BokBalanceDto> selectAllBalanceAll(Map<String, Object> pageParam);
    
    /**
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
	List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param);

	/**
	 * 查询利润表表样
	 * 
	 * @return
	 */
	List<ProfitCalculationDto> selectProfitCalculationDtoList();


	/**
	 * 查询账套的核算方式
	 * @return List<BokBookset>
	 */
	List<BokBookset> getAcctMode(Map<String, Object> pageParam);

	/**
	 * 查询账套的核算方式
	 * @param pageParam
	 * @return BokBookset
	 */
	BokBookset getAcctModeTo(Map<String, Object> pageParam);
	
    /**
     * 根据账套代码获取默认日期信息
     * 
     * @param pageParam
     * @return ValProfitQuery001Dto
     */
	ValProfitQuery001Dto getDateInfo1 (Map<String, Object> pageParam);
	
    /**
     * 根据账套组ID获取默认日期信息
     * 
     * @param pageParam
     * @return ValProfitQuery001Dto
     */
	List<ValProfitQuery001Dto> getDateInfo2 (Map<String, Object> pageParam);
}
