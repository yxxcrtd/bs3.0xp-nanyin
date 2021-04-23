/*
 * 文件名：CmpComponentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.database.dao;

import com.joyintech.background.component.order.exchange.common.dto.EtrBaseDto;

/**
 * 投资指令组件Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see CmpComponentMapper
 * @since 1.0
 */
public interface CmpComponentMapper<V extends EtrBaseDto> {

    /**
     * 查询指令信息
     * 
     * @param entrustId 指令编号
     * @return 指令信息
     */
    V select(String entrustId);
}
