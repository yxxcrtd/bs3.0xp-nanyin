/*
 * 文件名：ProductValuationReconciliation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valuareconcy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.valuareconcy.dao.ProductValuationReconciliation001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valuareconcy.dto.ProductValuationReconciliation001Dto;

/**
 * 产品估值对账 - service
 * 
 * @author yangjian
 * @version 1.0 2018年11月13日
 * @see ProductValuationReconciliation001Service
 * @since 1.0
 */
@Service(value="serviceProductValuationReconciliation001Service")
public class ProductValuationReconciliation001Service extends CommonExportService<ProductValuationReconciliation001Dto> {
    
    /**
     * ValProductInfoQuery001Mapper
     */
    @Autowired
    private ProductValuationReconciliation001Mapper mapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ProductValuationReconciliation001Dto> findPage(int page,int length, Map<String, Object> params) throws Exception {
        
        //所选产品
        String productIds = (String)params.get("productIds");
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(productIds)){
            finprodIdArr = productIds.split(",");
        }
        params.put("finprodIdArr", finprodIdArr);

        return super.queryForPage(page,length,()-> mapper.queryPageList(params));
    }
    
}
