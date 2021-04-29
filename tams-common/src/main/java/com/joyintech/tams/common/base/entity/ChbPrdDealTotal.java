package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdDealTotal extends BaseEntity {
    /**
     * 主键
     */
    private String seqno;

    /**
     * 上游系统交易编码
     */
    private String tradeId;

    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 确认日
     */
    private Date settdateDate;

    /**
     * 本金支付日
     */
    private Date payDate;

    /**
     * 利息支付日
     */
    private Date interestDate;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 交易份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeQty;

    /**
     * 赎回费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redemptionFee;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodValue;

    /**
     * 交易成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeCost;

    /**
     * 利息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 收益币种
     */
    private String  interestCcy;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 备注
     */
    private String remark;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    
    private  BigDecimal  redCost;
    
    private  BigDecimal  redprofit;
    
    private  String  busiType;
    
    private  String  totalTradeId;
    /**
     * 取得 主键
     * @return 主键
     */
    public String getSeqno() {
        return seqno;
    }
    
    /**
     * 设置 主键
     * @param seqno 主键
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 上游系统交易编码
     * @return 上游系统交易编码
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 上游系统交易编码
     * @param tradeId 上游系统交易编码
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 父产品代码
     * @return 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 设置 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 交易类型
     * @return 交易类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 交易类型
     * @param tradeType 交易类型
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 确认日
     * @return 确认日
     */
    public Date getSettdateDate() {
        return settdateDate;
    }

    /**
     * 设置 确认日
     * @param settdateDate 确认日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettdateDate(Date settdateDate) {
        this.settdateDate = settdateDate;
    }

    /**
     * 取得 本金支付日
     * @return 本金支付日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 本金支付日
     * @param payDate 本金支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 利息支付日
     * @return 利息支付日
     */
    public Date getInterestDate() {
        return interestDate;
    }

    /**
     * 设置 利息支付日
     * @param interestDate 利息支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    /**
     * 取得 交易金额
     * @return 交易金额
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 交易金额
     * @param tradeAmt 交易金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 取得 交易份额
     * @return 交易份额
     */
    public BigDecimal getTradeQty() {
        return tradeQty;
    }

    /**
     * 设置 交易份额
     * @param tradeQty 交易份额
     */
    public void setTradeQty(BigDecimal tradeQty) {
        this.tradeQty = tradeQty;
    }

    /**
     * 取得 赎回费
     * @return 赎回费
     */
    public BigDecimal getRedemptionFee() {
        return redemptionFee;
    }

    /**
     * 设置 赎回费
     * @param redemptionFee 赎回费
     */
    public void setRedemptionFee(BigDecimal redemptionFee) {
        this.redemptionFee = redemptionFee;
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getProdValue() {
        return prodValue;
    }

    /**
     * 设置 单位净值
     * @param prodValue 单位净值
     */
    public void setProdValue(BigDecimal prodValue) {
        this.prodValue = prodValue;
    }

    /**
     * 取得 交易成本
     * @return 交易成本
     */
    public BigDecimal getTradeCost() {
        return tradeCost;
    }

    /**
     * 设置 交易成本
     * @param tradeCost 交易成本
     */
    public void setTradeCost(BigDecimal tradeCost) {
        this.tradeCost = tradeCost;
    }

    /**
     * 取得 利息金额
     * @return 利息金额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 利息金额
     * @param intAmt 利息金额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 撤销日
     * @return 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 设置 撤销日
     * @param revDate 撤销日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

	public BigDecimal getRedCost() {
		return redCost;
	}

	public void setRedCost(BigDecimal redCost) {
		this.redCost = redCost;
	}

	public BigDecimal getRedprofit() {
		return redprofit;
	}

	public void setRedprofit(BigDecimal redprofit) {
		this.redprofit = redprofit;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getInterestCcy() {
		return interestCcy;
	}

	public void setInterestCcy(String interestCcy) {
		this.interestCcy = interestCcy;
	}

	public String getTotalTradeId() {
		return totalTradeId;
	}

	public void setTotalTradeId(String totalTradeId) {
		this.totalTradeId = totalTradeId;
	}
    
    
}