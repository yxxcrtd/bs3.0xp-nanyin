/*
 * 文件名：ValOrdersQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.trademanage.investtradequery.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.trademanage.investtradequery.dao.ValOrdersQuery003Mapper;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.ValOrdersDto;

@Service(value="serviceValOrdersQuery003Service")
public class ValOrdersQuery003Service extends BaseService<ValOrdersDto>{

   @Autowired
   private ValOrdersQuery003Mapper valOrdersQuery003Mapper;
    
    
    /**
     * 根据交易id获取交易指令列表(分页)
     * @param tradeId
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public ValOrdersDto findByOrderId(String orderId,String dealType){
        
        if(StringUtil.isNotEmpty(dealType)){
            return valOrdersQuery003Mapper.depSelectByOrderId(orderId);
        }else{
            return valOrdersQuery003Mapper.selectByOrderId(orderId);
        }
        
    }
     
    /**
     * 根据交易id获取存款类资产交易指令列表(分页)
     * @param tradeId
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
//    public ValOrdersDto findDepByOrderId(String orderId){
//        
//    }
    
}
