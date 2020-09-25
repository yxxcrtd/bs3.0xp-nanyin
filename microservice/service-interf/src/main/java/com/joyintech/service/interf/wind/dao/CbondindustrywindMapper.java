/*
 * 文件名：CbondindustrywindMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.InterfCbondindustrywind;

/**
 * 中国 Wind 债券分类板块Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月7日
 * @see CbondindustrywindMapper
 * @since 1.0
 */
@Repository
public interface CbondindustrywindMapper extends WindMapper<InterfCbondindustrywind> {

    /**
     * {@inheritDoc}
     */
    @Override
    List<InterfCbondindustrywind> select(@Param("sysDate") String sysDate,
                                         @Param("startNum") Long startNum,
                                         @Param("endNum") Long endNum);
}
