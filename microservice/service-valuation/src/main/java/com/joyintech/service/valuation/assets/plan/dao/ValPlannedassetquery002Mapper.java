/*
 * 文件名：ValBondassetquery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.plan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.assets.bondasset.dao.ValBondassetquery002Mapper;
import com.joyintech.service.valuation.assets.plan.dto.ValPlannedassetquery001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValBondassetquery002Mapper
 * @since 1.0
 */
@Repository(value="serviceValPlannedassetquery002Mapper")
public interface ValPlannedassetquery002Mapper {
    

    /**
     * 根据产品findprodid获取债券详细信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    ValPlannedassetquery001Dto queryBondasetByfinprodId(Map<String, Object> map);

    /**
     * 详情页表单数据的获取-利率报价信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValPlannedassetquery001Dto> queryintrate(Map<String, Object> map);

    /**
     * 详情页表单数据的获取-付息计划列表
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValPlannedassetquery001Dto> queryintpay(Map<String, Object> map);

    /**
     * 详情页表单数据的获取-还本计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValPlannedassetquery001Dto> queryrepay(Map<String, Object> map);

    /**
     * 详情页表单数据的获取-分红计划信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValPlannedassetquery001Dto> querybonus(Map<String, Object> map);

    /**
     * 付息信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    ValPlannedassetquery001Dto queryIntInfo(Map<String, Object> map);

}
