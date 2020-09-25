/*
 * 文件名：FinProductOpenPlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finproductopenplanadjust.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.finproductopenplanadjust.dto.FinProductOpenPlanAdjustDto;

/**
 * 信息调整管理_开放计划查询                                                        
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月17日
 * @see FinProductOpenPlanAdjustMapper
 * @since 1.0
 */
@Repository
public interface FinProductOpenPlanAdjustMapper {
    
    /**
     * 产品代码联想下拉框
     * @param pageParam Map<String, Object>
     * @return List<SelectOptionDto>
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> pageParam);
    
    /**
     * 开放计划查询
     * @param pageParam Map<String, Object>
     * @return List<FinProductOpenPlanDto>
     */
    List<FinProductOpenPlanAdjustDto> selectForPage(Map<String, Object> pageParam);

}
