/*
 * 文件名：CompintroductionMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.InterfCompintroduction;

/**
 * WIND-机构信息查询Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月27日
 * @see CompintroductionMapper
 * @since 1.0
 */
public interface CompintroductionMapper extends WindMapper<InterfCompintroduction> {

    /**
     * {@inheritDoc}
     */
    @Override
    List<InterfCompintroduction> select(@Param("sysDate") String sysDate,
                                        @Param("startNum") Long startNum,
                                        @Param("endNum") Long endNum);
}
