/*
 * 文件名：BussFinRepaymentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.asset;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.business.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;

/**
 *  还本信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月14日
 * @see BussFinRepaymentService
 * @since 1.0
 */
public interface BussFinRepaymentService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinRepaymentDto";
    
    /**
     * 初始化还本计划
     * <功能详细描述>
     * @param finprodId
     * @param cashType
     * @param finprodType
     * @return
     * @see
     * @since 1.0
     */
    List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId,String cashType,String finprodType,String finprodType2);
    
    /**
     * 生成还本计划
     * <功能详细描述>
     * @param dto
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    List<FinCashPlanDto> createAssetRePaymentPlan(BussFinRepaymentDto dto) throws ParseException;
	    
    /**
     * 根据新的计息截止日获取还款日（支付日）
     *
     * @return 还款日（支付日）
     * @see
     * @since 1.0
     */
    public String genPayDate(Map<String, Object> paramMap);
}
