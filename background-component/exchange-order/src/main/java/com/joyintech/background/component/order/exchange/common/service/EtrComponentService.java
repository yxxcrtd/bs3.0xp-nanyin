/*
 * 文件名：EtrComponentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.service;

import com.joyintech.background.component.order.exchange.common.dto.EtrBaseDto;

/**
 * 投资指令组件接口
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月9日
 * @see EtrComponentService
 * @since 1.0
 */
interface EtrComponentService<V extends EtrBaseDto> {

    /**
     * 指令新建
     * 
     * @param dto 投资指令信息
     * @return 投资指令信息
     */
    V entrustNew(V dto);

    /**
     * 指令修改
     * 
     * @param dto 投资指令列表
     * @return 投资指令列表
     */
    V entrustEdit(V dto);

    /**
     * 指令删除
     * 
     * @param dto 投资指令列表
     * @return 投资指令列表
     */
    V entrustDel(V dto);

    /**
     * 根据指令编号获取指令
     * 
     * @param entrustId 指令编号
     * @return 投资指令信息
     */
    V entrustSelect(String entrustId);

}
