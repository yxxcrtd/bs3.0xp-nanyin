/*
 * 文件名：ValOrdersQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.trademanage.investtradequery.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.trademanage.investtradequery.dto.ValOrdersDto;

@Repository(value="serviceValOrdersQuery003Mapper")
public interface ValOrdersQuery003Mapper {

   /**
    * 根据指令标号获取交易指令详情
    * @param orderId
    * @return
    * @see
    * @since 1.0
    */
    ValOrdersDto selectByOrderId(String orderId);
    
    /**
     * 根据指令标号获取存款类资产交易指令详情
     * @param orderId
     * @return
     * @see
     * @since 1.0
     */
     ValOrdersDto depSelectByOrderId(String orderId);
    
    
}
