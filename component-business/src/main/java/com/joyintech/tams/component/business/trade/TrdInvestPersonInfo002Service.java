/*
 * 文件名：TrdInvestPersonInfo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.trade;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

import java.util.List;
import java.util.Map;

public interface TrdInvestPersonInfo002Service {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "trdInvestPersonInfo";

    /**
     * 检索交易对手
     * @param param 参数
     * @return 交易对手
     */
    Page<SelectOptionDto> selectCounterId(Map<String, Object> param);
}
