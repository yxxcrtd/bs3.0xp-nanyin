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

import com.joyintech.tams.common.base.entity.PrdValParam;
import com.joyintech.tams.component.business.prod.dto.BussFinProductValuationDto;

/**
 * 产品估值信息组件接口
 * @author 何阳阳
 * @version 1.0 2018年9月16日
 * @see BussFinProductValuationService
 * @since 1.0
 */
public interface BussFinProductValuationService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductValuationDto";

    /**
     * 理财产品估值参数获取
     * @param prudoctId 金融产品代码
     * @return PrdValParam
     */
    PrdValParam getByProductId(String prudoctId);
    
    /**
     * 删除产品估值信息
     * @param valuationInfo 产品估值信息
     */
    void delete(String prudoctId);
    
    /**
     * 添加产品估值信息
     * @param valuationInfo 产品估值信息
     */
    void insert(BussFinProductValuationDto valuationInfo);

    /**
     * 修改产品估值信息
     * @param valuationInfo 产品估值信息
     */
    void update(BussFinProductValuationDto valuationInfo);
    
}
