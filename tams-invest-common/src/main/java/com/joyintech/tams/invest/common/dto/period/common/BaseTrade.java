package com.joyintech.tams.invest.common.dto.period.common;

import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: tams-invest-parent
 * @description: 基础交易相关
 * @author: 强成西
 * @create: 2018-11-02 09:46
 **/
public class BaseTrade {
    /**
     * 金融产品交易
     */
    private TrdProductDeal trdProductDeal;

    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;
    
    /**
     * 计划支付日
     */
    private Date payDate;
    
    /**
     * 应收款类型，还本结转，收息结转，还本不带息利息结转
     */
    private String recType;
    
    /**
     * 实际应更新应收款数量（deal表为实收信息，此处存入实际更新应收款数量）
     */
    private BigDecimal amount;

    /**
     * 获取 trdProductDeal
     *
     * @return trdProductDeal
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 trdProductDeal
     *
     * @param trdProductDeal trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 trdProductDealAdd
     *
     * @return trdProductDealAdd
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    /**
     * 设置 trdProductDealAdd
     *
     * @param trdProductDealAdd trdProductDealAdd
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 paTrdProductDeal
     *
     * @return paTrdProductDeal
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     *
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
    }
    /**
     * 获取 ptlTrade
     *
     * @return ptlTrade
     */
    public List<PtlTrade> getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     *
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
