/*
 * 文件名：ValuationMethodScheme001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.SelectPageOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme001Dto;

/**
 * 
 * 估值方法方案
 * @author zhangwei
 * @version 1.0 2018年8月27日
 * @see ValuationMethodScheme001Mapper
 * @since 1.0
 */
@Repository(value="serviceValuationMethodScheme001Mapper")
public interface ValuationMethodScheme001Mapper {

	/**
     * 查询估值方法方案
     * @param parameters
     * @return
     */
    List<ValuationMethodScheme001Dto> selectScheme(Map<String, Object> parameters);
    
    /**
     * 查询估值方法方案详情
     * @param parameters
     * @return
     */
    List<ValuationMethodScheme001Dto> selectSchemeDetail(Map<String, Object> parameters);
    
    
    /**
     * 查询估值方法方案详情 - 特殊资产估值
     * @param parameters
     * @return
     */
    List<ValuationMethodScheme001Dto> selectSpecialAssets(Map<String, Object> parameters);

    /**
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
	List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param);

	
	/**
     * 获取产品代码 - 特殊资产查询下拉框
     * @return
     * @throws Exception
     */
	List<ValuationMethodScheme001Dto> selectFinprod(Map<String, Object> parameters);
	
	/**modify by dongjun on 20181218 start **/
    /**
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    List<SelectPageOptionDto> getLinkSelectPageOption(Map<String, Object> param);
    /**modify by dongjun on 20181218 end **/
	
    /**
     * 是否存在默认估值方案
     * @param param 
     * @return
     */
	Integer getIsdefault(Map<String, Object> param);
}
