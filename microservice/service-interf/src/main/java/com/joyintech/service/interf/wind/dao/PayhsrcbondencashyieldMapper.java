/*
 * 文件名：PayhsrcbondencashyieldMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.InterfPayhsrcbondencashyield;

/**
 * 收益率曲线表Mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see PayhsrcbondencashyieldMapper
 * @since 1.0
 */
@Repository
public interface PayhsrcbondencashyieldMapper extends WindMapper<InterfPayhsrcbondencashyield> {

    /**
     * {@inheritDoc}
     */
    @Override
    List<InterfPayhsrcbondencashyield> select(@Param("sysDate") String sysDate,
                                              @Param("startNum") Long startNum,
                                              @Param("endNum") Long endNum);
}
