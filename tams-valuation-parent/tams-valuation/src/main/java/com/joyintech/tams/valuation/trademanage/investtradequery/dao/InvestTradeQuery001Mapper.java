/*
 * 文件名：InvestTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.trademanage.investtradequery.dto.BondLendDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.CurrentDepositDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.DepositLoanTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.FundCurrencyDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.InvestTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.LendTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.RepurchaseTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.TradeAccountDto;

/**
 * 投资交易查询 - 数据查询
 * 
 * @author yangjian
 * @version 1.0 2018年7月25日
 * @see InvestTradeQuery001Mapper
 * @since 1.0
 */
@Repository
public interface InvestTradeQuery001Mapper {

    /**
     * 分页查询 - 投资查询多类型公用
     * 
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    List<InvestTradeDto> queryPageListCommon(Map<String, Object> params);
   
    
    List<DepositLoanTradeDto>queryPageList(Map<String,Object>params);


	List<DepositLoanTradeDto> selectBytradeId(Map<String, Object> params);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	InvestTradeDto queryFinProductDealBytradeId(Map<String, Object> map);
	
	
	/**
	 * 查询拆借列表
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<LendTradeDto> selectLendList(Map<String, Object> map);
	
	/**
     * 分页查询-基金类资产查询
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<FundCurrencyDto> queryFundCurrencyList(Map<String, Object> params);
    
    /**
     * 查询回购列表
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<RepurchaseTradeDto> selectRepurchaseList(Map<String, Object> params);
    /**
     * 
     * 分页查询 - 活期存款收息交易查询
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<CurrentDepositDto> queryCurrentDepositList(Map<String, Object> params);



    /**
     * 现金类交易查询
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<InvestTradeDto> queryCashTransactions(Map<String, Object> params);
    
    /**
     * 增值税查询
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<InvestTradeDto> queryValueAddedTaxs(Map<String, Object> params);
    

    
    /**
     * 
     * 分页查询 - 交易账户收划款
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<TradeAccountDto> queryTradeAccountList(Map<String, Object> params);
    

	/**
	 * 查询债券交易列表
	 * @param map
	 * @return
	 */
	List<InvestTradeDto> selectBondList(Map<String, Object> map);
	
	/**
	 * 查询计划类资产（净值型）列表
	 * @param map
	 * @return
	 */
	List<InvestTradeDto> selectPlanNetvalueList(Map<String, Object> map);
	
	/**
	 * 查询计划类资产（收益型）列表
	 * @param map
	 * @return
	 */
	List<InvestTradeDto> selectPlanProfitList(Map<String, Object> map);
	
	/**
	 * 查询计划类资产（货币型）列表
	 * @param map
	 * @return
	 */
	List<InvestTradeDto> selectPlanCureencyList(Map<String, Object> map);
	
	
	/**
	 * 资产重分类
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<InvestTradeDto> selectReclassList(Map<String, Object> map);
	
	/**
	 * 
	 * 债券借贷交易列表
	 * 
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<BondLendDto> selectBondLendList(Map<String, Object> map); 
}
