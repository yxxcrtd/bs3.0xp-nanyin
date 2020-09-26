package com.joyintech.tams.valuation.financialstatement.assetliailitystatement.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

public class AssetLiabilityStatementQueryDto extends BaseEntity{

	private static final long serialVersionUID = -1493892052117293624L;
	
	
	/**
	 * 资产.项目名
	 */
	private String itemName;

	/**
	 * 资产.期末余额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal endingBalance = BigDecimal.ZERO;

	/**
	 * 资产.年初余额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal initialBalance = BigDecimal.ZERO;
	
	/**
	 * 负债和所有者权益.项目名
	 */
	private String itemName1;
	
	/**
	 * 负债和所有者权益.期末余额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal endingBalance1 = BigDecimal.ZERO;

	/**
	 * 负债和所有者权益.年初余额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal initialBalance1 = BigDecimal.ZERO;

	/**
	 * 账套代码
	 */
	private String booksetId;

	/**
	 * 科目号
	 */
	private String subjectNo;

	

	/**
	 * 科目名称
	 */
	private String subjectName;

	/**
	 * 余额日期
	 */
	private Date balanceDate;

	/**
	 * 余额方向
	 */
	private String balFlag;

	/**
	 * 使用金额类型
	 */
	private String useAmtType;

//	/**
//	 * 原币余额
//	 */
//	@JsonSerialize(using = BigDecimalSerializer.class)
//	private BigDecimal oAmt;
//
//	/**
//	 * 原币余额贷方
//	 */
//	@JsonSerialize(using = BigDecimalSerializer.class)
//	private BigDecimal oCAmt;
//
//	/**
//	 * 原币余额借方
//	 */
//	@JsonSerialize(using = BigDecimalSerializer.class)
//	private BigDecimal oDAmt;

    /**
     * 本位币余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAmt;

    /**
     * 本位币余额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCAmt;

    /**
     * 本位币余额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bDAmt;

    /**
     * 核算方式，收益、净值、货币
     */
    private String acctMode;

    public String getAcctMode() {
        return acctMode;
    }

    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }

    public BigDecimal getbAmt() {
        return bAmt;
    }

    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }

    public BigDecimal getbCAmt() {
        return bCAmt;
    }

    public void setbCAmt(BigDecimal bCAmt) {
        this.bCAmt = bCAmt;
    }

    public BigDecimal getbDAmt() {
        return bDAmt;
    }

    public void setbDAmt(BigDecimal bDAmt) {
        this.bDAmt = bDAmt;
    }

    public String getUseAmtType() {
		return useAmtType;
	}

	public void setUseAmtType(String useAmtType) {
		this.useAmtType = useAmtType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getEndingBalance() {
		return endingBalance;
	}

	public void setEndingBalance(BigDecimal endingBalance) {
		this.endingBalance = endingBalance;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	/**
	 * 获取 itemName1
	 * @return itemName1.
	 */
	public String getItemName1() {
		return itemName1;
	}

	/**
	 * 设置 itemName1
	 * @param itemName1 itemName1
	 */
	public void setItemName1(String itemName1) {
		this.itemName1 = itemName1;
	}

	/**
	 * 获取 endingBalance1
	 * @return endingBalance1.
	 */
	public BigDecimal getEndingBalance1() {
		return endingBalance1;
	}

	/**
	 * 设置 endingBalance1
	 * @param endingBalance1 endingBalance1
	 */
	public void setEndingBalance1(BigDecimal endingBalance1) {
		this.endingBalance1 = endingBalance1;
	}

	/**
	 * 获取 initialBalance1
	 * @return initialBalance1.
	 */
	public BigDecimal getInitialBalance1() {
		return initialBalance1;
	}

	/**
	 * 设置 initialBalance1
	 * @param initialBalance1 initialBalance1
	 */
	public void setInitialBalance1(BigDecimal initialBalance1) {
		this.initialBalance1 = initialBalance1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getBalFlag() {
		return balFlag;
	}

	public void setBalFlag(String balFlag) {
		this.balFlag = balFlag;
	}

//	public BigDecimal getoAmt() {
//		return oAmt;
//	}
//
//	public void setoAmt(BigDecimal oAmt) {
//		this.oAmt = oAmt;
//	}
//
//	public BigDecimal getoCAmt() {
//		return oCAmt;
//	}
//
//	public void setoCAmt(BigDecimal oCAmt) {
//		this.oCAmt = oCAmt;
//	}
//
//	public BigDecimal getoDAmt() {
//		return oDAmt;
//	}
//
//	public void setoDAmt(BigDecimal oDAmt) {
//		this.oDAmt = oDAmt;
//	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
}
