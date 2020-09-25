/*
 * 文件名：InterfG1003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1003.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.ig1003.entity.InterfG1003InTransferEntity;

/**
 * G1003mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see InterfG1003Mapper
 * @since 1.0
 */
@Repository
public interface InterfG1003Mapper {

    /**
     * 查询转账数组
     * 
     * @param orderid 指令编号
     * @param ccycode 币种code
     * @return 转账数组
     * @see
     * @since 1.0
     */
    List<InterfG1003InTransferEntity> selectTransfer(@Param("orderid") String orderid,
                                                     @Param("ccycode") String ccycode);

}
