/*
 * 文件名：ShadowPriceScheme002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.shadowpricescheme.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.valuationparametermanage.shadowpricescheme.dto.ShadowPriceScheme001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.shadowpricescheme.dto.ShadowPriceScheme002Dto;
/**
 * 影子方案明细mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月25日
 * @see ShadowPriceScheme002Mapper
 * @since 1.0
 */
@Repository
public interface ShadowPriceScheme002Mapper {
    /**
     * 影子方案明细查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     */
	List<ShadowPriceScheme002Dto> findShadowPriceDetial(Map<String, Object> pageParam);
    /**
     * 影子方案明细新增
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     */
	void insert(ShadowPriceScheme002Dto dto);
	
	/**
	 * 影子方案明细修改
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	void updateByPrimaryKeySelective(ShadowPriceScheme002Dto dto);
	/**
	 * 影子方案明细修改根据id查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param schemeDetailId
	 * @return
	 */
	ShadowPriceScheme002Dto selectSchemeDetailBySchId(String schemeDetailId);
	
	/**
	 * 明细批量删除
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param schemeDetailId
	 * @return
	 */
	int deleteShadowPriceDetailBatch(String schemeDetailId);
	/**
	 * 通过schName获取schId
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param schemeId
	 * @return
	 */
	ShadowPriceScheme001Dto getschemeIdByname(String schemeId);
   
	/**
	 * 校验是否重复数据
	 * @param pageParam 参数
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<ShadowPriceScheme002Dto> checkShadowPriceDetial(Map<String, Object> pageParam);
}
