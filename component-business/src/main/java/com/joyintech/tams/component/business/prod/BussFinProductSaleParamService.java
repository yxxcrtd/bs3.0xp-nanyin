/*
 * 文件名：BussFinProductSaleParamService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod;

import com.joyintech.tams.component.business.prod.dto.BussFinProductSaleParamDto;

/**
 * 销售参数接口
 * 
 * @author zhangliang
 * @version 1.0 2018年10月22日
 * @see BussFinProductSaleParamService
 * @since 1.0
 */
public interface BussFinProductSaleParamService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductSaleParamDto";
    
    /**
     * 根据金融产品代码查询销售参数信息
     * 
     * @param finprodId 金融产品代码
     * @return 销售参数信息
     * @see
     * @since 1.0
     */
    BussFinProductSaleParamDto querySaleParamInfoByFinprodId(String finprodId);

}
