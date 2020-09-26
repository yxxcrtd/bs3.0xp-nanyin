/*
 * 文件名：MoneyTaAutoCalReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.investratechange.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.batch.investratechange.dao.InvestRateChangeMapper;
import com.joyintech.tams.valuation.batch.investratechange.dto.InvestRateChangeReaderDto;

/**
 * 利率互换reader service
 * @author taoran
 * @version 20200114
 * @see InvestRateChangeReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class InvestRateChangeReaderService {
	
	@Autowired
    private InvestRateChangeMapper investRateChangeMapper;
    
    /**
     * 需要利率互换估值的交易数据
     */
    public List<InvestRateChangeReaderDto> getNeedChangeTrade(Map<String, Object> map){
    	
    	return investRateChangeMapper.selectNeedChangeTrade(map);
    }
    
}
