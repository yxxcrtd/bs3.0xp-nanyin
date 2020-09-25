/*
 * 文件名：WindMapper.java
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

/**
 * wind表查询接口
 * 
 * @param <E> 参数
 * @author zhangrui1
 * @version 1.0 2018年11月11日
 * @see WindMapper
 * @since 1.0
 */
public interface WindMapper<E> {

    /**
     * 查询数据
     * 
     * @param sysDate 查询参数（平台日期）
     * @param startNum 开始行（不包含）
     * @param endNum 结束行（包含）
     * @return 结果列表
     * @see
     * @since 1.0
     */
    List<E> select(@Param("sysDate") String sysDate,
    		       @Param("startNum") Long startNum,
    		       @Param("endNum") Long endNum);
}
