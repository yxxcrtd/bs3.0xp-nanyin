/*
 * 文件名：AssetLiabilityStatementQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.financialstatement.assetliailitystatement.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001SearchDto;

/**
 * 资产负债表
 * @author zhangwei
 * @version 1.0 2018年9月25日
 * @see AssetLiabilityStatementQuery001Mapper
 * @since 1.0
 */
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
	List<AssetLiabilityStatementQuery001Dto> selectBokBalance(AssetLiabilityStatementQuery001SearchDto dto);
	
	/**
	 * 科目余额表查询（全账套）
	 * @param dto
	 * @return
	 */
	List<AssetLiabilityStatementQuery001Dto> selectBokBalanceAll(AssetLiabilityStatementQuery001SearchDto dto);
	
}
