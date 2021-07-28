/*
 * 文件名：FinOpenPlanMaintenanceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.maintenance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenRule;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.maintenance.dto.FinOpenPlanMaintenanceDto;
import com.joyintech.service.product.messageadjust.maintenance.dto.OpenPlanParamDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月22日
 * @see FinOpenPlanMaintenanceMapper
 * @since 1.0
 */
@Repository
public interface FinOpenPlanMaintenanceMapper {

    /**
     * 产品代码的联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodIds(Map<String, Object> param);

    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<FinOpenPlanMaintenanceDto> selectForPage(Map<String, Object> pageParam);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    String getByCashId(Map<String, Object> param);

    /**
     * 查询封闭期限
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    Long getByFinprodId(String finprodId);

    /**
     * 获取交易日历代码
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    String getCalendarIdByFinprodId(String finprodId);

    /**
     * 根据金融产品代码查询市场代码
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    String getFinprodMarketIdByFinprodId(String finprodId);
    
    /**
     * 根据母产品代码查询子产品代码和开放计划现金流代码
     * <功能详细描述>
     * @param pfinprodId
     * @return
     * @see
     * @since 1.0
     */
    List<FinOpenPlan> queryLayerFinprodIdAndCashId(Map<String, Object> pageParam);
    
    /**
     * 删除开放规则
     */
    int deleteFinOpenRule(Map<String,Object> paraMap);
    
    /**
     * 删除开放计划
     */
    int deleteNewOpenPlan(Map<String,Object> paraMap);
    
    /**
     * 新增开放规则
     */
    int insertFinOpenRule(FinOpenRule finOpenRule);
    
    /**
     * 新开放计划生成
     */
    int insertNewOpenPlan(@Param("finOpenPlanList")List<FinOpenPlan> finOpenPlanList);
    
    /**
    * 产品代码联想下拉框
    */
   List<SelectOptionDto> productCodeList(Map<String, Object> pageParam);
   
   /**
    * 开放计划信息参数查询 String finprodId
    */
   OpenPlanParamDto selectOpenPlanParamDto(Map<String,Object> paraMap);
}
