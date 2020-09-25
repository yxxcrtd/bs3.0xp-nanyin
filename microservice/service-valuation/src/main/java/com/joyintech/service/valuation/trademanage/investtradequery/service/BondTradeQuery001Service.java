/*
 * 文件名：BondTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.trademanage.investtradequery.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.service.valuation.trademanage.investtradequery.dao.BondTradeQuery001Mapper;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.BondTradeDto;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.TradeFeeDetailDto;

/**
 *  债券交易查询
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see BondTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceBondTradeQuery001Service")
public class BondTradeQuery001Service extends BaseService<BondTradeDto>{

    @Autowired
    private BondTradeQuery001Mapper bondTradeQuery001Mapper;
    
    
    public BondTradeDto findByTradeId(Map<String, Object> params) {
    	BondTradeDto dto = bondTradeQuery001Mapper.selectByTradeId(params);
    	String tradeId = (String)params.get("tradeId");
    	
    	String tradeMarket = dto.getTradeMarket();
    	String feeTypes = "";
    	if(StringUtils.isNoneEmpty(tradeMarket)) {
    		if( tradeMarket.contains("上海") || tradeMarket.contains("深圳")) {
        		feeTypes = "B02,B01,B03,B05";// 印花税、经手费、证管税、佣金
        	}else {
        		feeTypes = "B06,B08";// 交易手续费、结算手续费
        	}
    	}
    	
    	List<TradeFeeDetailDto> feeList =  bondTradeQuery001Mapper.selectFeeList(tradeId, feeTypes);
    	dto.setTradeFeeDetailDtoList(feeList);
        return dto;
    }
    
}
