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

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme001Dto;

/**
 * 税务方案 - 主界面公用 
 * @author zhangwei
 * @version 1.0 2018年10月11日
 * @see TaxScheme001Mapper
 * @since 1.0
 */
@Repository(value="serviceTaxScheme001Mapper")
public interface TaxScheme001Mapper {

	/**
     * 查询税务方案
     * @param parameters
     * @return
     */
    List<TaxScheme001Dto> selectScheme(Map<String, Object> parameters);
	
    /**
     * 获取增值税则表
     * @param parameters
     * @return
     */
    List<TaxScheme001Dto> selectVatTax(Map<String, Object> parameters);
    
    /**
     * 获取税则表
     * @param parameters
     * @return
     */
    List<TaxScheme001Dto> selectAdtTax(Map<String, Object> parameters);

    /**
     * 二级法人  模式    获取   附加税则表
     * @param parameters
     * @return
     */
    List<TaxScheme001Dto> findAdtTaxEJFR(Map<String, Object> parameters);


    /**
     * 获取资产应税表
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<TaxScheme001Dto> selectTaxable(Map<String, Object> pageParam);
    
    /**
     * 查询参数设置
     */
    List<TaxScheme001Dto> selectConfig(Map<String, Object> pageParam);

    /**
     * 会计场景计税配置
     * @param pageParam
     * @return
     */
    List<TaxScheme001Dto> selectTaxSchaBokConfig(Map<String, Object> pageParam);
}
