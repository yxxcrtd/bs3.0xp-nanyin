/*
 * 文件名：ValuationMethodScheme001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dao;

import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme003Dto;

/**
 * 
 * 估值方法方案 - 资产分类估值
 * @author zhangwei
 * @version 1.0 2018年8月27日
 * @see ValuationMethodScheme003Mapper
 * @since 1.0
 */
public interface ValuationMethodScheme003Mapper {

	ValuationMethodScheme003Dto selectByPrimaryKey(String schemeDetailId);
	
    int insert(ValuationMethodScheme003Dto entity);

    int updateByPrimaryKeySelective(ValuationMethodScheme003Dto entity);
    
    int deleteByPrimaryKey(String schemeDetailId);
	
    
    /**
     * 批量删除
     * @param schemeDetailId
     * @return
     */
    int deleteByBatch(String schemeDetailId);

    
//	List<SelectOptionDto> getTradeMarketSelectOption(Map<String, Object> param);
}
