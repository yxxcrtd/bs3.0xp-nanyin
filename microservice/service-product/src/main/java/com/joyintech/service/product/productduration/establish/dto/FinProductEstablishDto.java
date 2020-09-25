/**
 * 
 */
package com.joyintech.service.product.productduration.establish.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * @author zhangliang
 *
 */
public class FinProductEstablishDto extends FinProduct{

    /**
     * 
     */
    private static final long serialVersionUID = -5107076629854232712L;

    
    /**
     * 有效状态
     */
    private String effectStatus;
    
    /**
     * 业务编号
     */
    private String bussId;
    
    /**
     * 组合代码
     */
    private String ptlId;
    
    /**
     * 销售部门
     */
    private String saleDepartment;
    
    /**
     * 子产品号
     */
    private String sonId;
    
    /**
     * 募集开始日
     */
    private Date firstSaleVdate;
    
    /**
     * 募集结束日
     */
    private Date firstSaleMdate;
    
    private String basis;
    private String tradeId;
    
    /**
     * 交易类型
     */
    private String dealType;
    
    /**
     * 预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;
    
    /**
     * 销售费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleFeeRate;
    
    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trustFeeRate;
    
    /**
     * 本期认购
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal currentSubscribe;
    /**
     * 本期认购
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal currentSub;
    
    /**
     * 到账资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal arrivalCapital;
    /**
     * 到账资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accountingAmt;
    
    /**
     * 上期余额
     */
    private BigDecimal lastAmt;
    
    /**
     * 上期赎回
     */
    private BigDecimal lastRed;
    
    /**
     * 上期滚入金额
     */
    private BigDecimal lastAmtSub;
    
    /**
     * 获取 lastAmt
     * @return lastAmt.
     */
    public BigDecimal getLastAmt() {
        return lastAmt;
    }


    /**
     * 设置 lastAmt
     * @param lastAmt lastAmt
     */
    public void setLastAmt(BigDecimal lastAmt) {
        this.lastAmt = lastAmt;
    }


    /**
     * 获取 lastRed
     * @return lastRed.
     */
    public BigDecimal getLastRed() {
        return lastRed;
    }


    /**
     * 设置 lastRed
     * @param lastRed lastRed
     */
    public void setLastRed(BigDecimal lastRed) {
        this.lastRed = lastRed;
    }


    /**
     * 获取 lastAmtSub
     * @return lastAmtSub.
     */
    public BigDecimal getLastAmtSub() {
        return lastAmtSub;
    }


    /**
     * 设置 lastAmtSub
     * @param lastAmtSub lastAmtSub
     */
    public void setLastAmtSub(BigDecimal lastAmtSub) {
        this.lastAmtSub = lastAmtSub;
    }


    public String getEffectStatus() {
        return effectStatus;
    }

    
    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    
    public String getPtlId() {
        return ptlId;
    }

    
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    
    public String getSaleDepartment() {
        return saleDepartment;
    }

    
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    
    public String getSonId() {
        return sonId;
    }

    
    public void setSonId(String sonId) {
        this.sonId = sonId;
    }

    
    public Date getFirstSaleVdate() {
        return firstSaleVdate;
    }

    
    public void setFirstSaleVdate(Date firstSaleVdate) {
        this.firstSaleVdate = firstSaleVdate;
    }

    
    public Date getFirstSaleMdate() {
        return firstSaleMdate;
    }

    
    public void setFirstSaleMdate(Date firstSaleMdate) {
        this.firstSaleMdate = firstSaleMdate;
    }

    
    public BigDecimal getYield() {
        return yield;
    }

    
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    
    public BigDecimal getSaleFeeRate() {
        return saleFeeRate;
    }

    
    public void setSaleFeeRate(BigDecimal saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    
    public BigDecimal getTrustFeeRate() {
        return trustFeeRate;
    }

    
    public void setTrustFeeRate(BigDecimal trustFeeRate) {
        this.trustFeeRate = trustFeeRate;
    }

    
    public String getBussId() {
        return bussId;
    }


    public void setBussId(String bussId) {
        this.bussId = bussId;
    }


	public BigDecimal getCurrentSubscribe() {
		return currentSubscribe;
	}


	public void setCurrentSubscribe(BigDecimal currentSubscribe) {
		this.currentSubscribe = currentSubscribe;
	}


	public BigDecimal getArrivalCapital() {
		return arrivalCapital;
	}


	public void setArrivalCapital(BigDecimal arrivalCapital) {
		this.arrivalCapital = arrivalCapital;
	}


	public String getBasis() {
		return basis;
	}


	public void setBasis(String basis) {
		this.basis = basis == null ? null : basis.trim();
	}


	public BigDecimal getCurrentSub() {
		return currentSub;
	}


	public void setCurrentSub(BigDecimal currentSub) {
		this.currentSub = currentSub;
	}


	public BigDecimal getAccountingAmt() {
		return accountingAmt;
	}


	public void setAccountingAmt(BigDecimal accountingAmt) {
		this.accountingAmt = accountingAmt;
	}


	public String getTradeId() {
		return tradeId;
	}


	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	//add by yejiejie 2019-2-20 start
	//期初客户数
	private BigDecimal customersBeginNum;

	public BigDecimal getCustomersBeginNum() {
		return customersBeginNum;
	}

	public void setCustomersBeginNum(BigDecimal customersBeginNum) {
		this.customersBeginNum = customersBeginNum;
	}

	//增加客户数
	private BigDecimal customersAddNum;

	public BigDecimal getCustomersAddNum() {
		return customersAddNum;
	}

	public void setCustomersAddNum(BigDecimal customersAddNum) {
		this.customersAddNum = customersAddNum;
	}

	//add by yejiejie 2019-2-20 end
	//add by yejiejie 2019-3-10 start
	//交易方向
	private String ps;

	public String getPs() {
		return ps;
	}


	public void setPs(String ps) {
		this.ps = ps;
	}
	//add by yejiejie 2019-3-10 end


    public String getDealType() {
        return dealType;
    }


    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
}
