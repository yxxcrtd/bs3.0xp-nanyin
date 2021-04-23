/*
 * 文件名：EtrComponentCommService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.service;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.background.component.order.exchange.common.constant.ProcessUpdateType;
import com.joyintech.background.component.order.exchange.common.dto.EtrBaseDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrDealDto;

/**
 * 投资指令组件接口共通
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrComponentCommService
 * @since 1.0
 */
public interface EtrComponentCommService<V extends EtrBaseDto> extends EtrComponentCheckService<V>, EtrComponentService<V> {

    /**
     * 指令退回 <br>
     * 投资指令中若设值原更新时间（oldUpdateTime）则作为排他条件
     * 
     * @param dto 投资指令信息
     * @return 投资指令信息
     */
    EtrBaseDto entrustBack(EtrBaseDto dto);

    /**
     * 指令关闭<br>
     * 投资指令中若设值原更新时间（oldUpdateTime）则作为排他条件
     * 
     * @param dto 投资指令列表
     * @return 投资指令列表
     */
    EtrBaseDto entrustClose(EtrBaseDto dto);

    /**
     * 指令撤回<br>
     * 投资指令中若设值原更新时间（oldUpdateTime）则作为排他条件
     * 
     * @param dto 投资指令列表
     * @return 投资指令列表
     */
    EtrBaseDto entrustRevoke(EtrBaseDto dto);

    /**
     * 指令接收<br>
     * 投资指令中若设值原更新时间（oldUpdateTime）则作为排他条件
     * 
     * @param dto 投资指令
     * @return 投资指令列表
     */
    EtrBaseDto entrustReceive(EtrBaseDto dto);

    /**
     * 指令取消接收<br>
     * 投资指令中若设值原更新时间（oldUpdateTime）则作为排他条件
     * 
     * @param dto 投资指令
     * @return 投资指令列表
     */
    EtrBaseDto entrustReceiveCancel(EtrBaseDto dto);

    /**
     * 指令成交进度更新
     * 
     * @param dtoList 指令成交明细列表
     * @param type 更新类型 {@link ProcessUpdateType}
     */
    void entrustProcessUpdate(List<EtrDealDto> dtoList, ProcessUpdateType type);

    /**
     * 指令成交明细查询
     * 
     * @param entrustId 指令编号
     * @return 指令成交明细列表
     */
    List<EtrDealDto> entrustDealSelect(String entrustId);

    /**
     * 指令成交进度查询
     * 
     * @param entrustId 指令编号
     * @return 最新完成度
     */
    BigDecimal entrustProcessSelect(String entrustId);

    /**
     * 配置参数更新
     * 
     * @param dto 参数配置项
     */
    void updateSettings(EtrParameterDto dto);
}
