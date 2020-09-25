/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto.ValAssetReclassification001Dto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPlan;

/**
 * 资产重分类
 * @author leipeng
 * @version 1.0 2018年10月24日
 * @see ValAssetReclassification001Mapper
 * @since 1.0
 */
@Repository(value="serviceValAssetReclassification001Mapper")
public interface ValAssetReclassification001Mapper {
    
    /**
     * 
     * 账套代码下拉框加载
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
   public List<SelectOptionDto> getBookSetId(Map<String, Object> paMap);
   
   /**
    * 
    * 标的类金融产品
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   public List<SelectOptionDto> getFinProduct(Map<String, Object> paMap);
   
   
   /**
    * 
    * <一句话功能简述>
    * <功能详细描述>
    * @param params
    * @return
    * @see
    * @since 1.0
    */
   public List<ValAssetReclassification001Dto> selectReclassList(Map<String,Object> params);
   
   
   /**
    * 查询标的现金流计划list
    * 
    * @param paMap 传递参数
    * @return List<TargetCashPlan>
    * @see
    * @since 1.0
    */
   List<TargetCashPlan> getTargetCashPlanList(Map<String, Object> paMap);
   
   
   /**
    * 获取相同的交易
    */
   String getSameDeal(ValAssetReclassification001Dto valAssetReclassification001Dto);
    
}
