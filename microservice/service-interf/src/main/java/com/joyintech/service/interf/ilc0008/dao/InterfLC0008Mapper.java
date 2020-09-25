/*
 * 文件名：InterfLC0008Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0008.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.ilc0008.entity.InterfLc0008InEntity;

/**
 * 信贷卖出余额展期推送接口LC0008mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年11月13日
 * @see InterfLC0008Mapper
 * @since 1.0
 */
@Repository
public interface InterfLC0008Mapper {

    /**
     * 将输入数据在表中更新
     * 
     * @param entity 信贷卖出余额展期推送接口LC0008输入
     * @return 查询个数
     * @see
     * @since 1.0
     */
    int updateData(InterfLc0008InEntity entity);

}
