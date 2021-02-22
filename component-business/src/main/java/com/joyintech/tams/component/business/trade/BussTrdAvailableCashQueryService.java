/*
 * 文件名：BussTrdAvailableCashQueryService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.trade;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 组合可用现金查询业务共通组件Service
 */
public interface BussTrdAvailableCashQueryService {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussTrdAvailableCashQuery";
    Map getAvailableCash(Map<String, Object> param);

    BigDecimal getSecManageAvailableCash(Map<String, Object> param);


    /**
     * 本方券可用持仓：实际 - 质押 +可用（流水表）-融出 +预占用 OWN_POSITION
     * 融入券可用持仓：融入-卖空-卖空可用（流水表） INTEGRATE_POSITION
     * 融入券卖空可用持仓：卖空 SHOWHANDS_POSITION
     */
    Map<String, Object> getShowHandNum(Map<String, Object> pageParam);

    /**
     * 获得资产维度的持仓
     * @param map 资产代码，日期
     * @return 持仓，融资金额
     */
    Map<String, Object>  getFinAvaPosition(Map<String, Object> map);

    /**
     * 根据组合id，资产id，日期查询可用持仓
     * @param map
     * @return
     */
    BigDecimal getSecPosition(Map<String, Object> map);
}
