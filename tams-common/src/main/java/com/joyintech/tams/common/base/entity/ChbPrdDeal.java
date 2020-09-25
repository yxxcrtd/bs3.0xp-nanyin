package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_PRD_DEAL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-24
 */
public class ChbPrdDeal extends BaseEntity implements Cloneable{
    /**
     * 交易编号
     */
    private String seqno;
    
    /**
     * 上游系统业务类型N:净值型TA交易   M:货币型TA交易    R:预期收益型TA交易
     */
    private String busiType;

    /**
     * 上游系统交易编码
     */
    private String tradeId;

    /**
     * 上游系统交易汇总交易编码
     */
    private String totalTradeId;

    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 
     */
    private String prodType;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 销售区域
     */
    private String buyPlace;

    /**
     * 交易类型(01 募集期认购/02 存续期申购/03 存续期赎回/04 收益分配/05 收益再投资/14 份额强增/15 份额强减/16 到期兑付/17 提前赎回；)
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
     * 利息金额
     */
    private BigDecimal intAmt;

    /**
     * 交易本金(赎回时存净金额=交易本金-赎回费)
     */
    private BigDecimal tradePrince;

    /**
     * 单位分红金额
     */
    private BigDecimal unitBonusAmount;

    /**
     * 赎回费
     */
    private BigDecimal redemptionFee;

    /**
     * 交易份额
     */
    private BigDecimal tradeQty;

    /**
     * 币种
     */
    private String ccy;

    /**
     *收益币种
     */
    private String interestCcy;

    /**
     * 数据状态(新建、修改、删除)
     */
    private String status;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

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
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 客户一级类型
     */
    private String customerType1;
    
    /**
     * 客户二级类型
     */
    private String customerType2;
    
    /**
     * 保有客户数
     */
    private BigDecimal custCounts;
    /**
     * 交易成本
     */
    private BigDecimal tradeCost;
    /**
     * 是否代销
     */
    private String isagency;
    
    private BigDecimal redCost;
    
    private BigDecimal redprofit;
    
    

    /**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月20日 下午5:25:47  
	 * @return redCost  
	 */
	
	public BigDecimal getRedCost() {
		return redCost;
	}

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月20日 下午5:25:47  
	 * @param redCost redCost  
	 */
	
	public void setRedCost(BigDecimal redCost) {
		this.redCost = redCost;
	}

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月20日 下午5:25:47  
	 * @return redprofit  
	 */
	
	public BigDecimal getRedprofit() {
		return redprofit;
	}

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月20日 下午5:25:47  
	 * @param redprofit redprofit  
	 */
	
	public void setRedprofit(BigDecimal redprofit) {
		this.redprofit = redprofit;
	}

	public String getCustomerType1() {
		return customerType1;
	}

	public void setCustomerType1(String customerType1) {
		this.customerType1 = customerType1;
	}

	public String getCustomerType2() {
		return customerType2;
	}

	public void setCustomerType2(String customerType2) {
		this.customerType2 = customerType2;
	}
	public BigDecimal getCustCounts() {
		return custCounts;
	}

	public void setCustCounts(BigDecimal custCounts) {
		this.custCounts = custCounts;
	}

	public BigDecimal getTradeCost() {
		return tradeCost;
	}

	public void setTradeCost(BigDecimal tradeCost) {
		this.tradeCost = tradeCost;
	}

	public String getIsagency() {
		return isagency;
	}

	public void setIsagency(String isagency) {
		this.isagency = isagency== null ? null : isagency.trim();
	}
	/**
     * 交易编号
     * @return SEQNO 交易编号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 交易编号
     * @param seqno 交易编号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	/**
     * 上游系统交易编码
     * @return TRADE_ID 上游系统交易编码
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 上游系统交易编码
     * @param tradeId 上游系统交易编码
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    
    public String getTotalTradeId() {
		return totalTradeId;
	}

	public void setTotalTradeId(String totalTradeId) {
		this.totalTradeId = totalTradeId;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	/**
     * 父产品代码
     * @return FPROD_ID 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 识别标识
     * @return INDENTIFY_MARK 识别标识
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 识别标识
     * @param indentifyMark 识别标识
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
    }

    /**
     * 销售区域
     * @return BUY_PLACE 销售区域
     */
    public String getBuyPlace() {
        return buyPlace;
    }

    /**
     * 销售区域
     * @param buyPlace 销售区域
     */
    public void setBuyPlace(String buyPlace) {
        this.buyPlace = buyPlace == null ? null : buyPlace.trim();
    }

    /**
     * 交易类型(01 募集期认购/02 存续期申购/03 存续期赎回/04 收益分配/05 收益再投资/14 份额强增/15 份额强减/16 到期兑付/17 提前赎回；)
     * @return TRADE_TYPE 交易类型(01 募集期认购/02 存续期申购/03 存续期赎回/04 收益分配/05 收益再投资/14 份额强增/15 份额强减/16 到期兑付/17 提前赎回；)
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 交易类型(01 募集期认购/02 存续期申购/03 存续期赎回/04 收益分配/05 收益再投资/14 份额强增/15 份额强减/16 到期兑付/17 提前赎回；)
     * @param tradeType 交易类型(01 募集期认购/02 存续期申购/03 存续期赎回/04 收益分配/05 收益再投资/14 份额强增/15 份额强减/16 到期兑付/17 提前赎回；)
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 确认日
     * @return SETTDATE_DATE 确认日
     */
    public Date getSettdateDate() {
        return settdateDate;
    }

    /**
     * 确认日
     * @param settdateDate 确认日
     */
    public void setSettdateDate(Date settdateDate) {
        this.settdateDate = settdateDate;
    }

    /**
     * 本金支付日
     * @return PAY_DATE 本金支付日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 本金支付日
     * @param payDate 本金支付日
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 利息支付日
     * @return INTEREST_DATE 利息支付日
     */
    public Date getInterestDate() {
        return interestDate;
    }

    /**
     * 利息支付日
     * @param interestDate 利息支付日
     */
    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    /**
     * 利息金额
     * @return INT_AMT 利息金额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 利息金额
     * @param intAmt 利息金额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 交易本金(赎回时存净金额=交易本金-赎回费)
     * @return TRADE_PRINCE 交易本金(赎回时存净金额=交易本金-赎回费)
     */
    public BigDecimal getTradePrince() {
        return tradePrince==null?BigDecimal.ZERO:tradePrince;
    }

    /**
     * 交易本金(赎回时存净金额=交易本金-赎回费)
     * @param tradePrince 交易本金(赎回时存净金额=交易本金-赎回费)
     */
    public void setTradePrince(BigDecimal tradePrince) {
        this.tradePrince = tradePrince;
    }

    /**
     * 单位分红金额
     * @return UNIT_BONUS_AMOUNT 单位分红金额
     */
    public BigDecimal getUnitBonusAmount() {
        return unitBonusAmount;
    }

    /**
     * 单位分红金额
     * @param unitBonusAmount 单位分红金额
     */
    public void setUnitBonusAmount(BigDecimal unitBonusAmount) {
        this.unitBonusAmount = unitBonusAmount;
    }

    /**
     * 赎回费
     * @return REDEMPTION_FEE 赎回费
     */
    public BigDecimal getRedemptionFee() {
        return redemptionFee;
    }

    /**
     * 赎回费
     * @param redemptionFee 赎回费
     */
    public void setRedemptionFee(BigDecimal redemptionFee) {
        this.redemptionFee = redemptionFee;
    }

    /**
     * 交易份额
     * @return TRADE_QTY 交易份额
     */
    public BigDecimal getTradeQty() {
        return tradeQty==null?BigDecimal.ZERO:tradeQty;
    }

    /**
     * 交易份额
     * @param tradeQty 交易份额
     */
    public void setTradeQty(BigDecimal tradeQty) {
        this.tradeQty = tradeQty;
    }

    /**
     * 币种
     * @return PRINC_CCY 本金币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 本金币种
     * @param princCcy 本金币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    
    public String getInterestCcy() {
		return interestCcy;
	}

	public void setInterestCcy(String interestCcy) {
		this.interestCcy = interestCcy;
	}

	/**
     * 数据状态(新建、修改、删除)
     * @return STATUS 数据状态(新建、修改、删除)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态(新建、修改、删除)
     * @param status 数据状态(新建、修改、删除)
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 数据来源
     * @return DATA_SOURCE 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 撤销日
     * @return REV_DATE 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 撤销日
     * @param revDate 撤销日
     */
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
   	public Object clone() throws CloneNotSupportedException {
   		Object object = super.clone();
   		return object;
   	}	
}