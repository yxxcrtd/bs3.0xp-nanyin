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



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.trademanage.investtradequery.dao.ValOrdersQuery002Mapper;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.ValOrdersDto;

@Service(value="serviceValOrdersQuery002Service")
public class ValOrdersQuery002Service extends BaseService<ValOrdersDto>{

   @Autowired
   private ValOrdersQuery002Mapper valOrdersQuery002Mapper;
    
    
    /**
     * 根据交易id获取交易指令列表(分页)
     * @param tradeId
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<ValOrdersDto> findBycondition(int page, int length, Map<String, Object> map) throws Exception{
        return super.queryForPage(page, length,()->valOrdersQuery002Mapper.selectBycondition(map));
    }
    /**
    * 根据交易id获取存款类资产交易的结算指令列表(分页)
    * @param tradeId
    * @return
    * @throws Exception 
    * @see
    * @since 1.0
    */
   public Page<ValOrdersDto> findDepBycondition(int page, int length, Map<String, Object> map) throws Exception{
       return super.queryForPage(page, length,()->valOrdersQuery002Mapper.depSelectBycondition(map));
   }
    /**
     * 根据交易id获取交易指令列表(分页)
     * @param tradeId
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<ValOrdersDto> selectFeeBycondition(int page, int length, Map<String, Object> map) throws Exception{
    	String feeType = (String)map.get("feeType");
        //交易方向多选，转为数组
        String[] feeTypeList = null;
        if(StringUtil.isNotEmpty(feeType)){
        	feeTypeList = feeType.split(",");
        }
        map.put("feeTypeList", feeTypeList);
        return super.queryForPage(page, length, ()->valOrdersQuery002Mapper.selectFeeBycondition(map));
    }
    
    
     
    /**
     * 根据tradeId判断，是否含有交易指令
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public int countOrders(Map<String, Object> map) {
        return valOrdersQuery002Mapper.countOrders(map);
    }
    
    
}
