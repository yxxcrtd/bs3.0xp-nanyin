/*
 * 文件名：BaseMstFinCashInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashInfoDto;


/**
 * 金融产品现金流信息组件Serivice
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see BaseFinProductCashInfoService
 * @since 1.0
 */
public interface BaseFinProductCashInfoService {

    /**
     * 
     * 删除金融产品现金流信息
     * <功能详细描述>
     * @param finCashInfo FinCashInfo
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteByPrimaryKey(FinCashInfo finCashInfo);
    /**
     * 
     * 新建金融产品现金流信息
     * <功能详细描述>
     * @param list List<FinCashInfo>
     * @return String id
     * @see
     * @since 1.0
     */
    boolean insertList(List<FinCashInfo> list);
    /**
     * 
     * 修改金融产品现金流信息
     * <功能详细描述>
     * @param dto BaseFinProductCashInfoDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean update(BaseFinProductCashInfoDto dto);
    /**
     * 
     * 查询金融产品现金流信息
     * <功能详细描述>
     * @param dto BaseFinProductCashInfoDto
     * @return List
     * @see
     * @since 1.0
     */
    List<FinCashInfo> select(FinCashInfo dto);


}
