/*
 * 文件名：BaseMstFinCashInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashInfo;

/**
 * 金融产品现金流信息组件mapper
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see ValBaseFinProductCashInfoMapper
 * @since 1.0
 */
@Repository
public interface ValBaseFinProductCashInfoMapper {
    /**
     * 
     * 删除金融产品现金流信息
     * <功能详细描述>
     * @param finCashInfo FinCashInfo
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(FinCashInfo finCashInfo);
    /**
     * 
     * 查询已生效的金融产品现金流信息
     * <功能详细描述>
     * @param dto BaseFinProductCashInfoDto 
     * @return List
     * @see
     * @since 1.0
     */
    List<FinCashInfo> selectDetail(FinCashInfo finCashInfo);



}
