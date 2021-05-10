/*
 * 文件名：ValProfitQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.BokBalanceDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.dto.ProfitNewCalculationDto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquerynew.dto.ValProfitNewQuery001Dto;
import com.joyintech.tams.common.base.entity.BokBalance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;


/**
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitNewQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValProfitNewQuery001Mapper")
public interface ValProfitNewQuery001Mapper {
    /**
     * 根据条件查询科目余额信息
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<BokBalance> selectAllBalance(Map<String, Object> pageParam);
    
    /**
     * 根据条件查询科目余额信息
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<BokBalance> selectAllBalanceAll(Map<String, Object> pageParam);
    
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
	List<ProfitNewCalculationDto> selectProfitCalculationDtoList();
	
    /**
     * 根据账套代码获取默认日期信息
     * 
     * @param pageParam
     * @return ValProfitQuery001Dto
     */
	ValProfitNewQuery001Dto getDateInfo1(Map<String, Object> pageParam);
	
    /**
     * 根据账套组ID获取默认日期信息
     * 
     * @param pageParam
     * @return ValProfitQuery001Dto
     */
	List<ValProfitNewQuery001Dto> getDateInfo2(Map<String, Object> pageParam);

    /**
     * <查询利润表信息>
     * <根据时间，账套代码查询利润数据(单账套查询)>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<ValProfitNewQuery001Dto> selectProfit(Map<String, Object> pageParam);
    
    
    /**
     * <查询利润表信息>
     * <根据时间，账套代码查询利润数据(全账套查询)>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<ValProfitNewQuery001Dto> selectProfitAll(Map<String, Object> pageParam);
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetId(@Param("booksetType") String booksetType, @Param("profitType") String profitType);

    List<BokBalanceDto> selectAllBalance2(Map<String, Object> pageParam);
}
