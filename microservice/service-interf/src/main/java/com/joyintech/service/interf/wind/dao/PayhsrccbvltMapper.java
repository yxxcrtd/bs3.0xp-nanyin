/*
 * 文件名：PayhsrccbvltMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.InterfPayhsrccbvlt;

/**
 * 债券市价估值mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年11月19日
 * @see PayhsrccbvltMapper
 * @since 1.0
 */
@Repository
public interface PayhsrccbvltMapper extends WindMapper<InterfPayhsrccbvlt> {

    /**
     * {@inheritDoc}
     */
    @Override
    List<InterfPayhsrccbvlt> select(@Param("sysDate") String sysDate,
                                    @Param("startNum") Long startNum,
                                    @Param("endNum") Long endNum);
}
