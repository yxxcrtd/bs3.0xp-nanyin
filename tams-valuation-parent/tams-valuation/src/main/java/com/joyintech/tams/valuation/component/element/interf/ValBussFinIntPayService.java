/*
 * 文件名：BussFinIntPayService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.interf;

import com.joyintech.tams.valuation.component.element.dto.ValBussFinIntPayDto;

/**
 * 分红计划组件接口
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月20日
 * @see ValBussFinIntPayService
 * @since 1.0
 */
public interface ValBussFinIntPayService {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinIntPay";

    /**
     * 通过金融产品代码查询分红计划
     * <功能详细描述>
     * @param finProductId
     * @return
     * @see
     * @since 1.0
     */
    ValBussFinIntPayDto getByFinprodId(String finprodId);
}
