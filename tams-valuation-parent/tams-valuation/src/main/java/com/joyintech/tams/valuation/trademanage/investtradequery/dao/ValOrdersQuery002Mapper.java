/*
 * 文件名：ValOrdersQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.investtradequery.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.trademanage.investtradequery.dto.ValOrdersDto;



public interface ValOrdersQuery002Mapper {

   /**
    * 根据交易id获取交易指令列表
    * @param tradeId
    * @return
    * @see
    * @since 1.0
    */
   List<ValOrdersDto> selectBycondition(Map<String, Object> map);
   
   /**
    * 根据交易id获取交易指令列表
    * @param tradeId
    * @return
    * @see
    * @since 1.0
    */
   List<ValOrdersDto> selectFeeBycondition(Map<String, Object> map);
   
   /**
    * 根据交易id获取交易指令列表
    * @param tradeId
    * @return
    * @see
    * @since 1.0
    */
   List<ValOrdersDto> depSelectBycondition(Map<String, Object> map);
   
   
   /**
    * 根据tradeId判断，是否含有交易指令
    * @param tradeId
    * @return
    * @see
    * @since 1.0
    */
   int countOrders(Map<String, Object> map);
    
}
