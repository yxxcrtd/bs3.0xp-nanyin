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

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme004Dto;

/**
 * 
 * 估值方法方案 - 资产分类估值
 * @author zhangwei
 * @version 1.0 2018年8月27日
 * @see ValuationMethodScheme004Mapper
 * @since 1.0
 */
public interface ValuationMethodScheme004Mapper {

	ValuationMethodScheme004Dto selectByPrimaryKey(String schemeDetailId);
	
    int insert(ValuationMethodScheme004Dto entity);

    int updateByPrimaryKeySelective(ValuationMethodScheme004Dto entity);
    
    int deleteByPrimaryKey(String schemeDetailId);
    
    /**
     * 批量删除
     * @param schemeDetailId
     * @return
     */
    int deleteByBatch(String schemeDetailId);
    
    /**
     * 获取产品代码 - 下拉框
     * @param parameters
     * @return
     */
    List<ValuationMethodScheme004Dto> selectFinprod(Map<String, Object> parameters);

    /**
     * 通过产品 id 获得产品信息
     * @param pageParam
     * @return
     */
	ValuationMethodScheme004Dto getFinprodInfo(Map<String, Object> pageParam);
	
}
