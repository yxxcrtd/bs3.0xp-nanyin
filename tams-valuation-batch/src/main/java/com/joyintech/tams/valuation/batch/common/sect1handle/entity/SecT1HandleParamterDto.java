/*
 * 文件名：DealTypesFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.sect1handle.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 交易金额计算参数
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see SecT1HandleParamterDto
 * @since 1.0
 */
public class SecT1HandleParamterDto {
    
    /**
     * 数量余额
     */
    private BigDecimal numBalance;
    
    /**
     * 成本类科目余额
     */
    private BigDecimal cbSubjectBalance;
    
    /**
     * 应计利息科目余额
     */
    private BigDecimal yjlxSubjectBalance;                               
     
    /**
     * 公允价值变动类科目余额
     */
    private BigDecimal gyjzbdSubjectBalance;  
                                    
    /**
     * 溢折价类科目余额 
     */
    private BigDecimal zyjSubjectBalance;
    
    /**
    * 投资目的
    */
    private String invAim;
    
    /**
    * 交易日
    */
    private Date dealDate;                              
    
    /**
    * 交割日
    */
    private Date settleDate;                               
    
    /**
    * 业务明细代码
    */
    private String busiId;                             
    
    /**
    * 是否零息/贴现 
    */
    private boolean isNotLxtx;
    
    /**
    * 账套代码 
    */
    private String booksetId;
    
    /**
     * 交易场所
     */
    private String tradeMarket;
    
    /**
     * T+1买入交易
     */
    private Map<String, DealHappenAmtDto> t1BuyDeal;

    
    /**
     * T+1卖出交易
     */
    private Map<String, DealHappenAmtDto> t1SellDeal;
    
    
    /**
     * 日期重算集合
     * ADD BY WUJIE ON 20191216
     */
    private Map<Date,Boolean> retryDateMap;
    
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    

    
    /**
     * 获取 数量余额
     * @return numBalance.
     */
    public BigDecimal getNumBalance() {
        return numBalance;
    }


    
    /**
     * 设置 数量余额
     * @param numBalance 数量余额
     */
    public void setNumBalance(BigDecimal numBalance) {
        this.numBalance = numBalance;
    }


    
    /**
     * 获取 成本类科目余额
     * @return cbSubjectBalance.
     */
    public BigDecimal getCbSubjectBalance() {
        return cbSubjectBalance;
    }


    
    /**
     * 设置 成本类科目余额
     * @param cbSubjectBalance 成本类科目余额
     */
    public void setCbSubjectBalance(BigDecimal cbSubjectBalance) {
        this.cbSubjectBalance = cbSubjectBalance;
    }


    
    /**
     * 获取 应计利息科目余额
     * @return yjlxSubjectBalance.
     */
    public BigDecimal getYjlxSubjectBalance() {
        return yjlxSubjectBalance;
    }


    
    /**
     * 设置 应计利息科目余额
     * @param yjlxSubjectBalance 应计利息科目余额
     */
    public void setYjlxSubjectBalance(BigDecimal yjlxSubjectBalance) {
        this.yjlxSubjectBalance = yjlxSubjectBalance;
    }


    
    /**
     * 获取 公允价值变动类科目余额
     * @return gyjzbdSubjectBalance.
     */
    public BigDecimal getGyjzbdSubjectBalance() {
        return gyjzbdSubjectBalance;
    }


    
    /**
     * 设置 公允价值变动类科目余额
     * @param gyjzbdSubjectBalance 公允价值变动类科目余额
     */
    public void setGyjzbdSubjectBalance(BigDecimal gyjzbdSubjectBalance) {
        this.gyjzbdSubjectBalance = gyjzbdSubjectBalance;
    }


    
    /**
     * 获取 溢折价类科目余额 
     * @return zyjSubjectBalance.
     */
    public BigDecimal getZyjSubjectBalance() {
        return zyjSubjectBalance;
    }


    
    /**
     * 设置 溢折价类科目余额 
     * @param zyjSubjectBalance 溢折价类科目余额 
     */
    public void setZyjSubjectBalance(BigDecimal zyjSubjectBalance) {
        this.zyjSubjectBalance = zyjSubjectBalance;
    }


    
    /**
     * 获取 投资目的
     * @return invAim.
     */
    public String getInvAim() {
        return invAim;
    }


    
    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }


    
    /**
     * 获取 交易日
     * @return dealDate.
     */
    public Date getDealDate() {
        return dealDate;
    }


    
    /**
     * 设置 交易日
     * @param dealDate 交易日
     */
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }


    
    /**
     * 获取 交割日
     * @return settleDate.
     */
    public Date getSettleDate() {
        return settleDate;
    }


    
    /**
     * 设置 交割日
     * @param settleDate 交割日
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }


    
    /**
     * 获取 业务明细代码
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }


    
    /**
     * 设置 业务明细代码
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }


    
    /**
     * 获取 是否零息/贴现 
     * @return isNotLxtx.
     */
    public boolean isNotLxtx() {
        return isNotLxtx;
    }


    
    /**
     * 设置 是否零息/贴现 
     * @param isNotLxtx 是否零息/贴现 
     */
    public void setNotLxtx(boolean isNotLxtx) {
        this.isNotLxtx = isNotLxtx;
    }


    
    /**
     * 获取 账套代码 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }


    
    /**
     * 设置 账套代码 
     * @param booksetId 账套代码 
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }


    
    /**
     * 获取 T+1买入交易
     * @return t1BuyDeal.
     */
    public Map<String, DealHappenAmtDto> getT1BuyDeal() {
        return t1BuyDeal;
    }


    
    /**
     * 设置 T+1买入交易
     * @param t1BuyDeal T+1买入交易
     */
    public void setT1BuyDeal(Map<String, DealHappenAmtDto> t1BuyDeal) {
        this.t1BuyDeal = t1BuyDeal;
    }


    
    /**
     * 获取 T+1卖出交易
     * @return t1SellDeal.
     */
    public Map<String, DealHappenAmtDto> getT1SellDeal() {
        return t1SellDeal;
    }


    
    /**
     * 设置 T+1卖出交易
     * @param t1SellDeal T+1卖出交易
     */
    public void setT1SellDeal(Map<String, DealHappenAmtDto> t1SellDeal) {
        this.t1SellDeal = t1SellDeal;
    }                             

    /**
     * 获取 tradeMarket
     * 
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     * 
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }



	public Map<Date, Boolean> getRetryDateMap() {
		if(retryDateMap==null){
			retryDateMap = new HashMap<Date,Boolean>();
		}
		return retryDateMap;
	}



	public void setRetryDateMap(Map<Date, Boolean> retryDateMap) {
		if(retryDateMap==null){
			retryDateMap = new HashMap<Date,Boolean>();
		}
		this.retryDateMap = retryDateMap;
	}



	public String getBokDetailId() {
		return bokDetailId;
	}



	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}

    
}
