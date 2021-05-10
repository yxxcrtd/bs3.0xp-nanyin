/*
 * 文件名：AssetLiabilityStatementQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.common.AssetLiabilityStatementNewEnum;

/**
 * 资产负债表
 * 
 * @author zhangwei
 * @version 1.0 2018年9月18日
 * @see AssetLiabilityStatementNewQuery001Dto
 * @since 1.0
 */
public class AssetLiabilityStatementNewQuery001Dto extends BaseEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 资产.项目名
	 */
	private String itemName;
	private String itemCode;

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
	 * 科目类型列表
	 */
	private List<AssetLiabilityStatementNewEnum> subjectTypeList = new ArrayList<>();

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

	/**
	 * 原币余额
	 */
/*	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal oAmt;*/

	/**
	 * 原币余额贷方
	 */
/*	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal oCAmt;*/

	/**
	 * 原币余额借方
	 */
/*	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal oDAmt;*/
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
	 * 区分项目类型(资产类，负债类，负债者权益)
	 * 三表优化新增字段
	 */
	private String itemType;

	public AssetLiabilityStatementNewQuery001Dto() {
		super();
	}

	public AssetLiabilityStatementNewQuery001Dto(String itemName) {
		super();
		this.itemName = itemName;
	}

	public AssetLiabilityStatementNewQuery001Dto(String itemName, BigDecimal endingBalance, BigDecimal initialBalance) {
		super();
		this.itemName = itemName;
		this.endingBalance = endingBalance;
		this.initialBalance = initialBalance;
	}

	public AssetLiabilityStatementNewQuery001Dto(String balFlag, String useAmtType, AssetLiabilityStatementNewEnum... enums) {
		super();
		this.balFlag = balFlag;
		this.useAmtType = useAmtType;
		this.subjectTypeList = Arrays.asList(enums);
		this.itemName = enums[0].getAssetName();
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<AssetLiabilityStatementNewEnum> getSubjectTypeList() {
		return subjectTypeList;
	}

	public void setSubjectTypeList(List<AssetLiabilityStatementNewEnum> subjectTypeList) {
		this.subjectTypeList = subjectTypeList;
	}

    
    /**
     * 获取 itemType
     * @return itemType.
     */
    public String getItemType() {
        return itemType;
    }

    
    /**
     * 设置 itemType
     * @param itemType itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AssetLiabilityStatementNewQuery001Dto [itemName=" + itemName + ", endingBalance="
               + endingBalance + ", initialBalance=" + initialBalance + ", itemName1=" + itemName1
               + ", endingBalance1=" + endingBalance1 + ", initialBalance1=" + initialBalance1
               + ", booksetId=" + booksetId + ", subjectNo=" + subjectNo + ", subjectTypeList="
               + subjectTypeList + ", subjectName=" + subjectName + ", balanceDate=" + balanceDate
               + ", balFlag=" + balFlag + ", useAmtType=" + useAmtType + ", bAmt=" + bAmt
               + ", bCAmt=" + bCAmt + ", bDAmt=" + bDAmt + ", itemType=" + itemType + "]";
    }

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
}
