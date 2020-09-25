/*
 * 文件名：ReconcileSchemeInfo001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.dto.ValProdSchemeConfig002Dto;


/**
 * 产品对账方案配置Mapper
 * 
 * @author weipeng
 * @version 1.0 2018年11月20日
 * @see ValProdSchemeConfig002Mapper
 * @since 1.0
 */
@Repository(value="serviceValProdSchemeConfig002Mapper")
public interface ValProdSchemeConfig002Mapper {

    /**
     * 分页查询数据
     * @param params 参数
     * @return 数据集合
     * @see
     * @since 1.0
     */
    List<ValProdSchemeConfig002Dto> queryPageList(Map<String, Object> params);
    
}
