/*
 * 文件名：FinPayPlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.asset.common.dto.FinPayPlanDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月23日
 * @see FinPayPlanMapper
 * @since 1.0
 */

@Repository
public interface FinPayPlanMapper {

    FinPayPlanDto getBasicInfo(Map<String,Object> param);
    
    void deleteCashPlanByFinprodId(@Param("finprodId") String finprodId);
    
    /**
     * 
     * 查询是否有流程中数据
     * <功能详细描述>
     * @param bTradeId
     * @return
     * @see
     * @since 1.0
     */
    Integer isNotIng(Map<String, Object> param);
}
