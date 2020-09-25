/*
 * 文件名：TrdAvailableCashQueryService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.common.dao;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface TrdAvailableCashQueryMapper {

    List<String> selectSecManager(Map<String, Object> param);
    /**
     * 获得资产维度的持仓
     * @param map 资产代码，日期
     * @return 持仓
     */
    Map<String, Object> getFinAvaPosition(Map<String, Object> map);
}
