/*
 * 文件名：BussFinProductValuationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod;

import com.joyintech.tams.component.business.prod.dto.BussFinProductInvestDto;

/**
 * 产品估值信息组件接口
 * @author yanya
 * @version 1.0 2019年8月22日
 * @see BussFinProductInvestService
 * @since 1.0
 */
public interface BussFinProductInvestService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductInvestDto";

    /**
     * 产品投向参数获取
     * @param finprodId 金融产品代码
     * @return PrdValParam
     */
    BussFinProductInvestDto getByProductId(String finprodId);
    
    /**
     * 删除产品投向
     * @param finprodId 金融产品代码
     */
    void delete(String finprodId);
    
    /**
     * 添加产品估值信息
     * @param goAlInfo 产品投向信息
     */
    void insert(BussFinProductInvestDto investInfo);

    /**
     * 修改产品估值信息
     * @param goAlInfo 产品投向信息
     */
    void update(BussFinProductInvestDto investInfo);
    
}
