/*
 * 文件名：Inter448001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448001.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.i448001.entity.Interf448001InEntity;

/**
 * 接口448001查询Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月18日
 * @see Interf448001Mapper
 * @since 1.0
 */
@Repository
public interface Interf448001Mapper {

    /**
     * 获取产品信息
     * 
     * @param param 查询参数
     * @return 产品信息
     * @see
     * @since 1.0
     */
    Interf448001InEntity selectProdInfo(Map<String, Object> param);

    /**
     * 查询分红方式和分红频率
     * 
     * @param param 查询参数
     * @return 分红方式和分红频率
     * @see
     * @since 1.0
     */
    Interf448001InEntity selectCashPayRule(Map<String, Object> param);
}
