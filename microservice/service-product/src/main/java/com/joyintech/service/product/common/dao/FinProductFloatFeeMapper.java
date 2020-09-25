/*
 * 文件名：FinProductFloatFeeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.service.product.common.dto.FinProductFloatFeeDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年10月19日
 * @see FinProductFloatFeeMapper
 * @since 1.0
 */
@Repository
public interface FinProductFloatFeeMapper {

    /**
     * 根据现金流代码删除业绩基准表
     * <功能详细描述>
     * @param cashId
     * @see
     * @since 1.0
     */
    int delete(String cashId);
    
    /**
     * 查询浮动管理费相关信息
     * @param map 参数
     * @return 浮动管理费相关信息
     */
    List<FinFloatCalcRuleDetail> queryFloatFeeDetailByFinprodId(Map<String, Object> map);
    
    /**
     * 查询浮动管理费相关信息
     * @param map 参数
     * @return 浮动管理费相关信息
     */
    FinProductFloatFeeDto queryFloatFeeInfoByFinprodId(Map<String, Object> map);
    
    /**
     * 查询浮动管理费相关信息
     * @param map 参数
     * @return 浮动管理费相关信息
     */
    List<FinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId(Map<String, Object> map);
    
    /**
     * 根据金融产品代码查询浮动管理费现金代码
     * @param map 金融产品代码
     * @return 浮动管理费现金代码
     */
    String queryFloatCashIdByFinprodId(Map<String, Object> map);
    
    /**
     * 根据现金流代码删除业绩报酬计算规则
     * @param cashId 现金流代码
     * @return DB变更个数
     */
    int deleteFloatFeeCalcRuleByCashId(String cashId);
    
    /**
     * 根据现金流代码删除现金流计提规则
     * @param cashId 现金流代码
     * @return DB变更个数
     */
    int deleteFloatFeeCountingRuleByCashId(String cashId);
    
    /**
     * 根据现金流代码和生效日删除明细信息
     * @param cashId 现金流代码
     * @return DB变更个数
     */
    int deleteFloatFeeDetailByCashId(String cashId);
    
// // 一体化对应etliu start
//    /**
//     * 根据现金流代码删除计提计划信息
//     * @param cashId 现金流代码
//     * @return DB变更个数
//     */
//    int deleteFloatFeeAccrScheByCashId(String cashId);
 // 一体化对应etliu end
    
    /**
     * 根据现金流代码删除业绩报酬计提计划
     * @param cashId 现金流代码
     * @return DB变更个数
     */
    int deleteFloatAccrualScheByCashId(String cashId);

    /**
     * 根据现金流代码和生效日取得最大的层级
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return 最大的层级
     * @see
     * @since 1.0
     */
    Integer getMaxLevByCashIdAndEffDate(Map<String,Object> param);
}
