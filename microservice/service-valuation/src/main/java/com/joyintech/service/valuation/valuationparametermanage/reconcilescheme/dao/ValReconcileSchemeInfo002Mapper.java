/*
 * 文件名：ReconcileSchemeInfo002Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo002Dto;


/**
 * 对账规则配置新建Mapper
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo002Mapper
 * @since 1.0
 */
@Repository(value="serviceValReconcileSchemeInfo002Mapper")
public interface ValReconcileSchemeInfo002Mapper {

    /**
     * 查询方案名称是否已存在
     * 
     * @param schemeDto 方案信息
     * @return 已存在true,否则false
     * @since 1.0
     */
    int findSchemeName(ValReconcileSchemeInfo002Dto schemeDto);
    
    /**
     * 根据方案类型检索已存在的默认方案名称，不校验当前操作的数据本身
     * 
     * @param infoDto 画面元素
     * @return 默认方案信息
     * @since 1.0
     */
    ValReconcileSchemeInfo002Dto selectDefaultSchemeByType(ValReconcileSchemeInfo002Dto infoDto);

    /**
     * 查询同一（方案类型+所属账套）下是否存在方案
     * 
     * @param infoDto 画面元素
     * @return 件数
     * @since 1.0
     */
    int getAllSchemeCount(ValReconcileSchemeInfo002Dto infoDto);

}
