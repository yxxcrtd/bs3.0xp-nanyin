/*
 * 文件名：InterfMOPT1001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.imopt1001.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.murex.imopt1001.entity.InterfMOPT1001TranOutEntity;

/**
 * 查询交易导入结果信息 接口mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年11月21日
 * @see InterfMOPT1001Mapper
 * @since 1.0
 */
@Repository
public interface InterfMOPT1001Mapper {

    /**
     * 更新发送murex处理结果信息表 信息
     * 
     * @param tranEntity 交易entity
     * @return 更改次数
     * @see
     * @since 1.0
     */
    int updateInfo(InterfMOPT1001TranOutEntity tranEntity);
}
