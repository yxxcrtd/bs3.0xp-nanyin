/*
 * 文件名：PayhsrcsecinfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.InterfPayhsrcsecinfo;

/**
 * 查询债券基本资料
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月11日
 * @see PayhsrcsecinfoMapper
 * @since 1.0
 */
@Repository
public interface PayhsrcsecinfoMapper extends WindMapper<InterfPayhsrcsecinfo> {

    /**
     * {@inheritDoc}
     */
    @Override
    List<InterfPayhsrcsecinfo> select(@Param("sysDate") String sysDate,
                                      @Param("startNum") Long startNum,
                                      @Param("endNum") Long endNum);

}
