/*
 * 文件名：FinProductValuationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年10月08日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.product.common.dao.PrdInvestParamMapper;
import com.joyintech.service.product.common.dto.BussFinProductInvestInfoDto;

/**
 * 产品投向信息共通
 * @author yanya
 * @version 1.0 2019年8月23日
 * @see FinProductInvestService
 * @since 1.0
 */
@Service("backFinProductInvestService")
public class FinProductInvestService {
	
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private PrdInvestParamMapper prdInvestParamMapper;

	/**
	 * 根据金融产品代码查询产品投向信息
	 * @param finprodId 金融产品代码
	 * @return DB变更行数
	 */
	public BussFinProductInvestInfoDto select(String finprodId){
		return prdInvestParamMapper.selectByPrimaryKey(finprodId);
	}
	
	/**
	 * 根据金融产品代码删除产品投向信息
	 * @param finprodId 金融产品代码
	 * @return DB变更行数
	 */
	public Integer delete(String finprodId){
		return prdInvestParamMapper.deleteByPrimaryKey(finprodId);
	}
	
	/**
	 * 更新产品投向信息
	 * @param prdValParam 产品投向信息
	 * @return DB变更行数
	 */
	public Integer update(BussFinProductInvestInfoDto bussFinProductInvestInfoDto){
		return prdInvestParamMapper.updateByPrimaryKey(bussFinProductInvestInfoDto);
	}
	
	/**
	 * 新增产品投向信息
	 * @param prdValParam 产品投向信息
	 * @return DB变更行数
	 */
	public Integer insert(BussFinProductInvestInfoDto bussFinProductInvestInfoDto){
		return prdInvestParamMapper.insert(bussFinProductInvestInfoDto);
	}
}
