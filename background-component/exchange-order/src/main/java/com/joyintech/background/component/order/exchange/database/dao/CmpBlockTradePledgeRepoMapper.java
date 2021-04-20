/*
 * 文件名：CmpBlockTradePledgeRepoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.database.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.background.component.order.exchange.business.dto.EtrBlockTradePledgeRepoDto;

/**
 * 大宗债券质押回购指令子组件-Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月9日
 * @see CmpBlockTradePledgeRepoMapper
 * @since 1.0
 */
@Repository
public interface CmpBlockTradePledgeRepoMapper extends CmpComponentMapper<EtrBlockTradePledgeRepoDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    EtrBlockTradePledgeRepoDto select(String entrustId);

}
