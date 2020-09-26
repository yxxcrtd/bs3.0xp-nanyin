/*
 * 文件名：TaxFlowQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class TaxFlowQuery001Dto extends BaseEntity implements ITableRow {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -2016667813772620305L;

	// @Override
	// @NotEmpty(fieldName = "ValBalanceStatementQuery001.booksetId")
	// public String getBooksetId() {
	// return super.getBooksetId();
	// }
	/**
	 * 账套代码
	 */
	private String booksetId;
	
	/**
	 * 账套名称
	 */
	private String booksetName;
	/**
	 * 账务明细代码
	 */
	private String bokDetailId;

	/**
	 * 资产类型（确认标识）
	 */
	private String taxPType;

	/**
	 * 金融产品简称
	 */
	private String finProdAbbr;

	/**
	 * 本金收益特征
	 */
	private String profitType;

	/**
	 * 资产分类
	 */
	private String finType;

	/**
	 * 收入类型(增值税类型)
	 */
	private String vatType;

	/**
	 * 应税标识(计税方式)
	 */
	private String calcType;
	/**
	 * 发生日期
	 */
	private  String happenDate;
	/**
	 * 发生开始日期
	 */
	private  Date happenDateBegin;
	/**
	 * 发生结束日期
	 */
	private Date happenDateEnd;

	/**
	 * 发生含税销售额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal happenAmt;
	/**
	 * 计税余额发生额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal balAmt;
	
	  /**
     * 账套集合
     */
    private String[] booksetIdArr;
    
    /**
     * 销售额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal sellAmt; 
    
    /**
     * 应税税率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal taxRate;
    
    /**
     * 含税销售额本币
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal happenAmtB;
    
    /**
     * 销售额本币
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal taxAmtB;
    
    /**
     * 增值税税额本币
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal balAmtB;
    
    public BigDecimal getHappenAmtB() {
		return happenAmtB;
	}


	public void setHappenAmtB(BigDecimal happenAmtB) {
		this.happenAmtB = happenAmtB;
	}


	public BigDecimal getTaxAmtB() {
		return taxAmtB;
	}


	public void setTaxAmtB(BigDecimal taxAmtB) {
		this.taxAmtB = taxAmtB;
	}


	public BigDecimal getBalAmtB() {
		return balAmtB;
	}


	public void setBalAmtB(BigDecimal balAmtB) {
		this.balAmtB = balAmtB;
	}


	/**
     * 获取 sellAmt
     * @return sellAmt.
     */
    public BigDecimal getSellAmt() {
        return sellAmt;
    }

    
    /**
     * 设置 sellAmt
     * @param sellAmt sellAmt
     */
    public void setSellAmt(BigDecimal sellAmt) {
        this.sellAmt = sellAmt;
    }

    
    /**
     * 获取 taxRate
     * @return taxRate.
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    
    /**
     * 设置 taxRate
     * @param taxRate taxRate
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
	 * 获取 booksetId
	 * 
	 * @return booksetId.
	 */
	public String getBooksetId() {
		return booksetId;
	}

	/**
	 * 设置 booksetId
	 * 
	 * @param booksetId
	 *            booksetId
	 */
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	
	
	
    /**
     * 获取 booksetName
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }


    
    /**
     * 设置 booksetName
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }


    /**
	 * 获取 bokDetailId
	 * 
	 * @return bokDetailId.
	 */
	public String getBokDetailId() {
		return bokDetailId;
	}

	/**
	 * 设置 bokDetailId
	 * 
	 * @param bokDetailId
	 *            bokDetailId
	 */
	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}

	/**
	 * 获取 taxPType 
	 * 
	 * @return taxPType.
	 */
	public String getTaxPType() {
		 return taxPType;
	}
	public String getTaxPTypeFmt() {
	    return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.增值税_确认类型, taxPType))?
	          SysDataUtil.getNameFromDataDict(DictCode.增值税_确认类型, taxPType):taxPType;
	    
//		 return SysDataUtil.getNameFromDataDict(DictCode.增值税_确认类型,taxPType);
	}

	/**
	 * 设置 taxPType
	 * 
	 * @param taxPType
	 *            taxPType
	 */
	public void setTaxPType(String taxPType) {
		this.taxPType = taxPType;
	}

	/**
	 * 获取 finProdAbbr
	 * 
	 * @return finProdAbbr.
	 */
	public String getFinProdAbbr() {
		return finProdAbbr;
	}

	/**
	 * 设置 finProdAbbr
	 * 
	 * @param finProdAbbr
	 *            finProdAbbr
	 */
	public void setFinProdAbbr(String finProdAbbr) {
		this.finProdAbbr = finProdAbbr;
	}

	/**
	 * 获取 profitType
	 * 
	 * @return profitType.
	 */
	public String getProfitType() {
		return profitType;
	}
	
	public String getProfitTypeFmt(){
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.本金收益特征, profitType))?
            SysDataUtil.getNameFromDataDict(DictCode.本金收益特征, profitType):profitType;	    
	    
//		return SysDataUtil.getNameFromDataDict(DictCode.本金收益特征, profitType);
	}

	/**
	 * 设置 profitType
	 * 
	 * @param profitType
	 *            profitType
	 */
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	/**
	 * 获取 finType
	 * 
	 * @return finType.
	 */
	public String getFinType() {
		return finType;
	}
	public String getFinTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.增值税资产分类, finType))?
            SysDataUtil.getNameFromDataDict(DictCode.增值税资产分类, finType):finType;    
            
//		return SysDataUtil.getNameFromDataDict(DictCode.增值税_资产分类, finType);
	}
	/**
	 * 设置 finType
	 * 
	 * @param finType
	 *            finType
	 */
	public void setFinType(String finType) {
		this.finType = finType;
	}

	/**
	 * 获取 vatType
	 * 
	 * @return vatType.
	 */
	public String getVatType() {
		 return vatType;
	}
	public String getVatTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.增值税类型, vatType))?
            SysDataUtil.getNameFromDataDict(DictCode.增值税类型, vatType):vatType;    
	    
//		 return SysDataUtil.getNameFromDataDict(DictCode.增值税类型,vatType);
	}
	/**
	 * 设置 vatType
	 * 
	 * @param vatType
	 *            vatType
	 */
	public void setVatType(String vatType) {
		this.vatType = vatType;
	}

	/**
	 * 获取 calcType
	 * 
	 * @return calcType.
	 */
	public String getCalcType() {
		return calcType;
	}
	
	public String getCalcTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.计税方式, calcType))?
            SysDataUtil.getNameFromDataDict(DictCode.计税方式, calcType):calcType; 
	    
//		return SysDataUtil.getNameFromDataDict(DictCode.计税方式,calcType);
	}
	/**
	 * 设置 calcType
	 * 
	 * @param calcType
	 *            calcType
	 */
	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}

	/**
	 * 获取 happenAmt
	 * 
	 * @return happenAmt.
	 */
	public BigDecimal getHappenAmt() {
		return happenAmt;
	}

	/**
	 * 设置 happenAmt
	 * 
	 * @param happenAmt
	 *            happenAmt
	 */
	public void setHappenAmt(BigDecimal happenAmt) {
		this.happenAmt = happenAmt;
	}

	/**
	 * 获取 balAmt
	 * 
	 * @return balAmt.
	 */
	public BigDecimal getBalAmt() {
		return balAmt;
	}

	/**
	 * 设置 balAmt
	 * 
	 * @param balAmt
	 *            balAmt
	 */
	public void setBalAmt(BigDecimal balAmt) {
		this.balAmt = balAmt;
	}

	/**
	 * 获取 happenDateBegin
	 * @return happenDateBegin.
	 */
	public Date getHappenDateBegin() {
		return happenDateBegin;
	}

	/**
	 * 设置 happenDateBegin
	 * @param happenDateBegin happenDateBegin
	 */
	public void setHappenDateBegin(Date happenDateBegin) {
		this.happenDateBegin = happenDateBegin;
	}

	/**
	 * 获取 happenDateEnd
	 * @return happenDateEnd.
	 */
	public Date getHappenDateEnd() {
		return happenDateEnd;
	}

	/**
	 * 设置 happenDateEnd
	 * @param happenDateEnd happenDateEnd
	 */
	public void setHappenDateEnd(Date happenDateEnd) {
		this.happenDateEnd = happenDateEnd;
	}

	/**
	 * 获取 booksetIdArr
	 * @return booksetIdArr.
	 */
	public String[] getBooksetIdArr() {
		return booksetIdArr;
	}

	/**
	 * 设置 booksetIdArr
	 * @param booksetIdArr booksetIdArr
	 */
	public void setBooksetIdArr(String[] booksetIdArr) {
		this.booksetIdArr = booksetIdArr;
	}

	/**
	 * 获取 happenDate
	 * @return happenDate.
	 */
	public String getHappenDate() {
		return happenDate;
	}

	/**
	 * 设置 happenDate
	 * @param happenDate happenDate
	 */
	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}
	
	public String getHappenDateFmt(){
		if(StringUtil.isNotEmpty(happenDate)){
			return DateUtil.format(DateUtil.parse(happenDate),"yyyy-MM-dd");
		}
		return "";
	}


}
