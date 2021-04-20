/*
 * 文件名：CmpComponentCommMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.order.exchange.database.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.background.component.order.exchange.common.dto.EtrBaseDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrDealDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;
import com.joyintech.background.component.order.exchange.database.entity.EntrustAddComponent;
import com.joyintech.background.component.order.exchange.database.entity.FinishStatusComponent;

/**
 * 投资组件共通Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月9日
 * @see CmpComponentCommMapper
 * @since 1.0
 */
@Repository
public interface CmpComponentCommMapper extends CmpComponentMapper<EtrSinglePortfolioDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    EtrSinglePortfolioDto select(String entrustId);

    /**
     * 更新指令状态
     * 
     * @param dto 指令信息
     * @return 更新结果
     */
    int updateEntrustStatus(EtrBaseDto dto);

    /**
     * 查询指令成交明细
     * 
     * @param entrustId 指令代码
     * @return 指令成交明细
     */
    List<EtrDealDto> seleteDeal(String entrustId);

    /**
     * 指令成交明细登录
     * 
     * @param dto 指令成交明细
     * @return 登录结果
     */
    int insertDeal(FinishStatusComponent dto);

    /**
     * 指令成交明细删除
     * 
     * @param dto 指令成交明细
     * @return 删除结果
     */
    int deleteDeal(EtrDealDto dto);

    /**
     * 查询指令成交进度
     * 
     * @param entrustId 指令编号
     * @return 指令成交进度
     */
    BigDecimal selectEtrProcess(String entrustId);

    /**
     * 更新指令分发状态
     * 
     * @param entrustId
     * @return 更新结果
     */
    int updateDistribute(EtrBaseDto dto);

    /**
     * 删除分发临时表数据
     * 
     * @param entrustId 指令编号
     * @return 删除结果
     */
    int deleteDistributeTemp(String entrustId);

    /**
     * 将分发数据保存到临时表
     * 
     * @param entrustId 指令编号
     * @return 登录结果
     */
    int insertDistributeTemp(String entrustId);

    /**
     * 将临时表数据保存到业务表
     * 
     * @param entrustId 指令编号
     * @return 登录结果
     */
    int insertDistribute(String entrustId);

    /**
     * 删除分发给其他交易员的数据
     * 
     * @param dto 指令信息
     * @return 删除结果
     */
    int deleteDistribute(EtrBaseDto dto);

    /**
     * 从资产表取相关资产信息
     * 
     * @param assetCode 资产代码
     * @return 相关资产信息
     */
    EntrustAddComponent selectAddProperty(String assetCode);
}
