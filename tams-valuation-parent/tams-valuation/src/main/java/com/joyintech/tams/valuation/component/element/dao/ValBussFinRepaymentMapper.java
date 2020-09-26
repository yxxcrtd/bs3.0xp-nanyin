/*
 * 文件名：ValBussFinRepaymentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;



/**
 *  还本信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月14日
 * @see ValBussFinRepaymentMapper
 * @since 1.0
 */
@Repository
public interface ValBussFinRepaymentMapper {

    List<FinCashPlanDto> getFinCashPlanByProductId(@Param("finprodId")String finprodId,@Param("cashType") String cashType,@Param("finprodType") String finprodType);
    
    List<FinCashPlanDto> getFinCashPlanByProductIdOther(Map<String, Object> params);
    
    /**
     * 查询资产付息营业日准则
     *
     * @param paramMap 参数
     * @return 资产付息营业日准则
     * @see
     * @since 1.0
     */
    String selectAssetPayTdayRule(Map<String, Object> paramMap);

}
