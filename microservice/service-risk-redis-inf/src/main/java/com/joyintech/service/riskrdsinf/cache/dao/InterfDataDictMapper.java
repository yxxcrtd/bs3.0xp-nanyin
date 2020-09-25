/*
 * 文件名：InterfDataDictMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.cache.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.service.riskrdsinf.cache.entity.InterfDataDictInfo;

/**
 * 接口数据字典查询Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年3月13日
 * @see InterfDataDictMapper
 * @since 1.0
 */
@Repository
public interface InterfDataDictMapper {

    /**
     * 根据条件查询数据字典（外围系统数据字典-投组系统数据字典）
     * 
     * @param info 检索条件
     * @return 数据字典（外围系统数据字典-投组系统数据字典）
     */
    InterfDataDictInfo selectDataDictInfo(InterfDataDictInfo info);

    /**
     * 查询所有数据字典对应关系
     * 
     * @return 数据字典对应关系
     */
    List<InterfDataDictInfo> selectInfDataDictList();
}
