/*
 * 文件名：TaxScheme001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.taxparametermanage.taxscheme.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme002Dto;

/**
 * 税务方案 
 * @author zhangwei
 * @version 1.0 2018年10月11日
 * @see TaxScheme002Mapper
 * @since 1.0
 */
@Repository(value="serviceTaxScheme002Mapper")
public interface TaxScheme002Mapper {

	TaxScheme002Dto selectByPrimaryKey(String schId);
	
	int insertSelective(TaxScheme002Dto dto);
    
	int updateByPrimaryKeySelective(TaxScheme002Dto dto);

	int deleteByPrimaryKey(String schId);
	
	int updateIsDefault(TaxScheme002Dto dto);
	
	/**
	 * 复制 增值税则表
	 * @param map
	 * @return
	 */
	int copyTaxVatSchedule(Map<String, Object> map);
	
	/**
	 * 复制 附加税则表
	 * @param map
	 * @return
	 */
	int copyTaxAdtSchedule(Map<String, Object> map);
	
	/**
	 * 复制 应税信息表
	 * @param map
	 * @return
	 */
	int copyTaxTaxableInfo(Map<String, Object> map);
	
	/**
	 * 复制 方案配置值信息表 - 参数设置信息
	 * @param map
	 * @return
	 */
	int copyBokSchemeConfigVal(Map<String, Object> map);
	
    /**
     * 复制 方案配置值信息表 - 会计场景计税配置
     * @param map
     * @return
     */
    int copyTaxSchaBokConfig(Map<String, Object> map);

	/**
	 *  通过 查询账套关联表 中 的税务方案
	 * @param schName
	 * @return
	 */
	int countBokBookSetPlan(String schId);

	/**
	 * 删除 增值税则表
	 * @param schId
	 * @return
	 */
	int deleteTaxVatBySchId(String schId);
	
	/**
	 * 删除 附加税则表
	 * @param schId
	 * @return
	 */
	int deleteTaxAdtBySchId(String schId);
	
	/**
	 * 删除 应税信息表
	 * @param schId
	 * @return
	 */
	int deleteTaxAbleBySchId(String schId);
	
	/**
	 * 删除 方案配置值信息表 - 参数设置信息
	 * @param schId
	 * @return
	 */
	int deleteConfigBySchId(String schId);
	
    /**
     * 删除 方案配置值信息表 - 会计场景计税配置
     * @param schId
     * @return
     */
    int deleteTaxConfigBySchId(String schId);
	
	
	

}
