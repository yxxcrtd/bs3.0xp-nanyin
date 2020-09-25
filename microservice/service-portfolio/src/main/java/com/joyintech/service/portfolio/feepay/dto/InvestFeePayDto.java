/**
 * 
 */
package com.joyintech.service.portfolio.feepay.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;


/**
 * @author malinlin
 *
 */
public class InvestFeePayDto extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 7605593327899545754L;

    /**
     * 证券账户
     */
    private String secAcctId;
    
    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 状态
     */
    private String effectStatus;
    
    /**
     * 组合代码
     */
    private String ptlId;
    
    /**
     * 市场代码
     */
    private String finprodMarketId;
    
    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 销售部门
     */
    private String saleDepartment;
    
    /**
     * 费用代码
     */
    private String feeId;
    
    /**
     * 费用名称
     */
    private String feeName;
    
    /**
     * 费用类型
     */
    private String feeType;
    
    /**
     * 计息基础
     */
    private String basis;
    
    /**
     * 开始日期
     */
    private Date vdate;
    
    /**
     * 结束日期
     */
    private Date mdate;
    
    /**
     * 支付日
     */
    private Date payDate;
    
    /**
     * 费用金额
     */
    private BigDecimal feeAmt;
    
    /**
     * 现金流代码
     */
    private String cashId;
    
    /**
     * 现金流序号
     */
    private String cashNum;
    
    /**
     * 费用账户
     */
    private String feeAccount;
    
    /**
     * 理财托管户
     */
    private String manageAcctount;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 计提开始日
     */
    private Date positionStartDate;
    
    /**
     * 计提结束日
     */
    private Date positionEndDate;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    /**
     * 未结清费用
     */
    private BigDecimal unclearAmt;
    /**
     * 产品收益类型
     */
    private String profitType;
    /**
     * 处理模式
     */
    private String dealMode;

    //产品费用支付问题guyunfei 20190923 start
    /**
     * 费用类型
     */
    private String feeType1;
    
    /**
     * 实付费用金额
     */
    private BigDecimal actPayAmt;

    /**
     * 机构代码
     */
    private String institutionCode;

    public String getFeeType1() {
        return feeType1;
    }

    public void setFeeType1(String feeType1) {
        this.feeType1 = feeType1;
    }

    //产品费用支付问题 guyunfei 20190923 end

    public String getEffectStatus() {
        return effectStatus;
    }

    
    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    /**
     * 获取 secAcctId
     * @return secAcctId.
     */
    public String getSecAcctId() {
        return secAcctId;
    }
    
    /**
     * 设置 secAcctId
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getPtlId() {
        return ptlId;
    }

    
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    public String getFinprodId() {
        return finprodId;
    }

    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    public String getSaleDepartment() {
        return saleDepartment;
    }

    
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    
    public String getFeeId() {
        return feeId;
    }

    
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    
    public String getFeeName() {
        return feeName;
    }

    
    
    public String getFeeType() {
        return feeType;
    }


    
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }


    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    
    
    public Date getVdate() {
        return vdate;
    }


    
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }


    
    public Date getMdate() {
        return mdate;
    }


    
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

	public BigDecimal getFeeAmt() {
        return feeAmt;
    }
    
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }


    
    public String getCashId() {
        return cashId;
    }


    
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }


    
    public String getCashNum() {
        return cashNum;
    }


    
    public void setCashNum(String cashNum) {
        this.cashNum = cashNum;
    }


    
    public String getBasis() {
        return basis;
    }


    
    public void setBasis(String basis) {
        this.basis = basis;
    }


    
    public String getTradeId() {
        return tradeId;
    }


    
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }


	public String getFeeAccount() {
		return feeAccount;
	}


	public void setFeeAccount(String feeAccount) {
		this.feeAccount = feeAccount;
	}


	public String getManageAcctount() {
		return manageAcctount;
	}


	public void setManageAcctount(String manageAcctount) {
		this.manageAcctount = manageAcctount;
	}


	public String getCcy() {
		return ccy;
	}


	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}


	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}


	public Date getPayDate() {
		return payDate;
	}


	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}


	public Date getPositionStartDate() {
		return positionStartDate;
	}


	public void setPositionStartDate(Date positionStartDate) {
		this.positionStartDate = positionStartDate;
	}


	public Date getPositionEndDate() {
		return positionEndDate;
	}


	public void setPositionEndDate(Date positionEndDate) {
		this.positionEndDate = positionEndDate;
	}


	public BigDecimal getUnclearAmt() {
		return unclearAmt;
	}


	public void setUnclearAmt(BigDecimal unclearAmt) {
		this.unclearAmt = unclearAmt;
	}


	public String getProfitType() {
		return profitType;
	}


	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}


	public String getDealMode() {
		return dealMode;
	}


	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

    
    /**
     * 获取 actPayAmt
     * @return actPayAmt.
     */
    public BigDecimal getActPayAmt() {
        return actPayAmt;
    }

    
    /**
     * 设置 actPayAmt
     * @param actPayAmt actPayAmt
     */
    public void setActPayAmt(BigDecimal actPayAmt) {
        this.actPayAmt = actPayAmt;
    }

    /**
     * 获取 institutionCode
     *
     * @return institutionCode
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 institutionCode
     *
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
}
