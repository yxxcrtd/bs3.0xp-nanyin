/*
 * 文件名：Interf449195Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449195.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.i449195.entity.Interf449195InEntity;

/**
 * 滚动N天产品到期接口(449195)-查询mapper
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月7日
 * @see Interf449195Mapper
 * @since 1.0
 */
@Repository
public interface Interf449195Mapper {

    /**
     * 获取滚动N天的子期次产品信息
     * 
     * @param prodId 金融产品代码
     * @param sysDate 平台日
     * @return 子期次产品信息
     * @see
     * @since 1.0
     */
    Interf449195InEntity selectProdInfo(@Param("prodId") String prodId,
                                        @Param("sysDate") String sysDate);
}
