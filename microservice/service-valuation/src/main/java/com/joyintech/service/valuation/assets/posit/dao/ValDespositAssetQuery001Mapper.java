package com.joyintech.service.valuation.assets.posit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.posit.dto.ValDespositAssetQuery001Dto;

/**
 * 存款类资产查询
 * 
 * @author wangtingting
 * @version 1.0 2018年7月19日
 * @see ValDespositAssetQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValDespositAssetQuery001Mapper")
public interface ValDespositAssetQuery001Mapper {

    /**
     * 根据条件查询列表
     * 
     * @param params Map<String, Object>
     * @return List<ValDespositAssetQuery001Dto>
     * @see
     * @since 1.0
     */
    List<ValDespositAssetQuery001Dto> queryPageList(Map<String, Object> params);

    /**
     * 产品下拉框初始化
     * 
     * @param map Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryValFinproId(Map<String, Object> map);

    /**
     * checkFinprodId
     * 
     * @param finprodId finprodId
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean checkFinprodId(String finprodId);

    /**
     * 现金流信息表
     * 
     * @param params Map<String, Object>
     * @return int
     * @see
     * @since 1.0
     */
    int deleteFinCashInfo(Map<String, Object> params);

    /**
     * 现金流计算规则表
     * 
     * @param params Map<String, Object>
     * @return int
     * @see
     * @since 1.0
     */
    int deleteFinCashCalcRule(Map<String, Object> params);

    /**
     * 现金流支付规则表
     * 
     * @param params Map<String, Object>
     * @return int
     * @see
     * @since 1.0
     */
    int deleteFinCashPayRule(Map<String, Object> params);

    /**
     * Map<String, Object>
     * 
     * @param params Map<String, Object>
     * @return int
     * @see
     * @since 1.0
     */
    int deleteFinCashPlan(Map<String, Object> params);

    /**
     * 标的类金融产品
     * 
     * @param params Map<String, Object>
     * @return int
     * @see
     * @since 1.0
     */
    int deleteFinProduct(Map<String, Object> params);

    /**
     * 金融产品分类表
     * 
     * @param params Map<String, Object>
     * @return int
     * @see
     * @since 1.0
     */
    int deleteFinProductType(Map<String, Object> params);

}
