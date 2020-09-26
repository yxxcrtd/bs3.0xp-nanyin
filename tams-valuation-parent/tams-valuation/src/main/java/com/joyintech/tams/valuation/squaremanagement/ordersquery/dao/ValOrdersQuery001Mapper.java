/*
 * 文件名：ValOrdersQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.squaremanagement.ordersquery.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.squaremanagement.ordersquery.dto.ValOrdersQuery001Dto;

/**
 * 
 * 结算指令查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValOrdersQuery001Mapper
 * @since 1.0
 */
public interface ValOrdersQuery001Mapper {
    
    /**
     * 查询结算指令
     * @param params 参数map
     * @return 结算指令结果集
     * @see
     * @since 1.0
     */
    List<ValOrdersQuery001Dto> queryPageList(Map<String, Object> params);
    
}
