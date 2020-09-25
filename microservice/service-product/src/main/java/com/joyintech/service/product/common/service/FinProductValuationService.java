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

import com.joyintech.tams.common.base.dao.PrdValParamMapper;
import com.joyintech.tams.common.base.entity.PrdValParam;

/**
 * 产品估值信息共通
 * @author 张亮
 * @version 1.0 2018年10月08日
 * @see FinProductValuationService
 * @since 1.0
 */
@Service("backFinProductValuationService")
public class FinProductValuationService {
	
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private PrdValParamMapper prdValParamMapper;

	/**
	 * 根据金融产品代码查询产品估值信息
	 * @param finprodId 金融产品代码
	 * @return DB变更行数
	 */
	public PrdValParam select(String finprodId){
		return prdValParamMapper.selectByPrimaryKey(finprodId);
	}
	
	/**
	 * 根据金融产品代码删除产品估值信息
	 * @param finprodId 金融产品代码
	 * @return DB变更行数
	 */
	public Integer delete(String finprodId){
		return prdValParamMapper.deleteByPrimaryKey(finprodId);
	}
	
	/**
	 * 更新产品估值信息
	 * @param prdValParam 产品估值信息
	 * @return DB变更行数
	 */
	public Integer update(PrdValParam prdValParam){
		return prdValParamMapper.updateByPrimaryKey(prdValParam);
	}
	
	/**
	 * 新增产品估值信息
	 * @param prdValParam 产品估值信息
	 * @return DB变更行数
	 */
	public Integer insert(PrdValParam prdValParam){
		return prdValParamMapper.insert(prdValParam);
	}
}
