/*
 * 文件名：AssetLiabilityStatementQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.busicommon.common.dto.BokBalance;
import com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.dto.DisAssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.dto.DisAssetLiabilityStatementQuery001SearchDto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisProfitCalculationDto;

/**
 * 资产负债表
 * @author zhangwei
 * @version 1.0 2018年9月25日
 * @see AssetLiabilityStatementQuery001Mapper
 * @since 1.0
 */
public interface DisAssetLiabilityStatementQuery001Mapper {

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
	List<DisAssetLiabilityStatementQuery001Dto> selectBokBalance(DisAssetLiabilityStatementQuery001SearchDto dto);
	
	/**
	 * 科目余额表查询（全账套）
	 * @param dto
	 * @return
	 */
	List<DisAssetLiabilityStatementQuery001Dto> selectBokBalanceAll(DisAssetLiabilityStatementQuery001SearchDto dto);
	
	/**
	 * 查询资产负债表表样
	 * 
	 * @return
	 */
	List<DisProfitCalculationDto> selectProfitCalculationDtoList();
	
	/**期末余额
	 * 
	 */
	 List<BokBalance>   selectBalanceXY(DisAssetLiabilityStatementQuery001SearchDto dto);
	
	 
	 /**
	  * 余额查询本位币
	  */
	 List<BokBalance>   selectBalance(DisAssetLiabilityStatementQuery001SearchDto dto);
	 
	 /** 一体化查询资产负债表表样   add by  dongxiangyang  20200211  start*/
	 List<DisProfitCalculationDto> selectProfitCalculationDtoListYth();
	 /** 一体化查询资产负债表表样   add by  dongxiangyang  20200211  end*/
}
