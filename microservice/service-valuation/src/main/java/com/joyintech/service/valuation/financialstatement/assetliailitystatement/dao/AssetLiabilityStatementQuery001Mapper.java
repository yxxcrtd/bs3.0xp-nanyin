/*
 * 文件名：AssetLiabilityStatementQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.financialstatement.assetliailitystatement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001Dto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001SearchDto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQueryDto;

/**
 * 资产负债表
 * @author zhangwei
 * @version 1.0 2018年9月25日
 * @see AssetLiabilityStatementQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceAssetLiabilityStatementQuery001Mapper")
public interface AssetLiabilityStatementQuery001Mapper {

	/**
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
	List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param);

	/**
	 * 科目余额表查询（单账套）
	 * @param dto
	 * @return
	 */
	List<AssetLiabilityStatementQueryDto> selectBokBalance(AssetLiabilityStatementQuery001SearchDto dto);
	
	/**
	 * 科目余额表查询（全账套）
	 * @param dto
	 * @return
	 */
	List<AssetLiabilityStatementQueryDto> selectBokBalanceAll(AssetLiabilityStatementQuery001SearchDto dto);
	
    /**
     * 根据账套代码获取默认日期信息
     * 
     * @param pageParam
     * @return AssetLiabilityStatementQuery001SearchDto
     */
    AssetLiabilityStatementQuery001SearchDto getDateInfo1 (Map<String, Object> pageParam);
    
    /**
     * 根据账套组ID获取默认日期信息
     * 
     * @param pageParam
     * @return AssetLiabilityStatementQuery001SearchDto
     */
    List<AssetLiabilityStatementQuery001SearchDto> getDateInfo2 (Map<String, Object> pageParam);
	
}
