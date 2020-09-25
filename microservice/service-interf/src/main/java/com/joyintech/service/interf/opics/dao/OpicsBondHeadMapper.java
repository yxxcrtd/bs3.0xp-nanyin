/*
 * 文件名：OpicsBondHeadMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.opics.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * OPICS-债券寸头更新Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see OpicsBondHeadMapper
 * @since 1.0
 */
@Repository
public interface OpicsBondHeadMapper {

    /**
     * OPICS数据接入
     * 
     * @param params 参数
     * @return 接入数据件数
     * @see
     * @since 1.0
     */
    int saveOpicsData(Map<String, Object> params);

}
