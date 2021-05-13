/*
 * 文件名：ValuationSolutionManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokValScheme;
import com.joyintech.tams.constant.DictCode;


/**
 * 估值方法方案
 * @author zhangwei
 * @version 1.0 2018年8月27日
 * @see ValuationMethodScheme001Dto
 * @since 1.0
 */
public class ValuationMethodScheme001Dto extends BokValScheme {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8983390044596661647L;

    /**
     * 方案简述
     */
    private String scheme;
    
    /** 资产代码 **/
    private String finprodId;
    
    /** 资产名称 **/
    private String finprodName;
    
    /**
     * 估值方案明细代码
     */
    private String schemeDetailId;
    
    /**
     * 交易市场Code
     */
    private String tradeMarketCode;
    
    /**
     * 交易市场
     */
    private String tradeMarket;

    /**
     * 证券品种Code
     */
    private String secTypeCode;
    
    /**
     * 证券品种
     */
    private String secType;

    /**
     * 证券细分类Code
     */
    private String secDetailTypeCode;
    
    /**
     * 证券细分类
     */
    private String secDetailType;

    /**
     * 投资目的Code
     */
    private String invAimCode;
    
    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 估值方法Code
     */
    private String valuationMethodCode;
    
    /**
     * 估值方法
     */
    private String valuationMethod;

    /**
     * 估值市价Code
     */
    private String valuationPriceCode;
    
    /**
     * 估值市价
     */
    private String valuationPrice;
    
    /**
     * 估值第二市价Code
     */
    private String valuationPrice2Code;
    
    /**
     * 估值第二市价
     */
    private String valuationPrice2;

    /**
     * 行情位数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal quotaDigit;

    /**
     * 估值日期 - 行情日期Code
     */
    private String valuationDateCode;
    
    /**
     * 估值日期 - 行情日期
     */
    private String valuationDate;
    
    /**
     * 行情尾数处理方式
     */
    private String priceTailMode;
    
    /**
     * 行情尾数处理方式Code
     */
    private String priceTailModeCode;
    
    /**
     * 价格类型
     */
    private String priceClass;
    
    /**
     * 价格类型Code
     */
    private String priceClassCode;

    /**
     * 开始日

     */
    private String vdate;

    /**
     * 结束日

     */
    private String mdate;
    
    /**
     * 处理操作
     */
    private String operateType;

	/**
	 *金融产品类型（估值核算）
	 */
	private String finprodType;
    
	
    public String getOperateType() {
        return operateType;
    }

    
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getSchemeDetailId() {
		return schemeDetailId;
	}

	public void setSchemeDetailId(String schemeDetailId) {
		this.schemeDetailId = schemeDetailId;
	}

	public String getTradeMarketCode() {
		return tradeMarketCode;
	}

	public void setTradeMarketCode(String tradeMarketCode) {
		this.tradeMarketCode = tradeMarketCode;
	}

	public String getTradeMarket() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, tradeMarket))?
				SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, tradeMarket):tradeMarket;

//		return SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, tradeMarket);
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getSecTypeCode() {
		return secTypeCode;
	}

	public void setSecTypeCode(String secTypeCode) {
		this.secTypeCode = secTypeCode;
	}

	public String getSecType() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.证券品种, secType))?
				SysDataUtil.getNameFromDataDict(DictCode.证券品种, secType):secType;

//		return SysDataUtil.getNameFromDataDict(DictCode.证券品种, secType);
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	public String getSecDetailTypeCode() {
		return secDetailTypeCode;
	}

	public void setSecDetailTypeCode(String secDetailTypeCode) {
		this.secDetailTypeCode = secDetailTypeCode;
	}

	public String getSecDetailType() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.债券类型, secDetailType))?
				SysDataUtil.getNameFromDataDict(DictCode.债券类型, secDetailType):secDetailType;

//		return SysDataUtil.getNameFromDataDict(DictCode.债券类型, secDetailType);
	}

	public void setSecDetailType(String secDetailType) {
		this.secDetailType = secDetailType;
	}

	public String getInvAimCode() {
		return invAimCode;
	}

	public void setInvAimCode(String invAimCode) {
		this.invAimCode = invAimCode;
	}

	public String getInvAim() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.投资目的, invAim))?
				SysDataUtil.getNameFromDataDict(DictCode.投资目的, invAim):invAim;

//		return SysDataUtil.getNameFromDataDict(DictCode.投资目的, invAim);
	}

	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	public String getValuationMethodCode() {
		return valuationMethodCode;
	}

	public void setValuationMethodCode(String valuationMethodCode) {
		this.valuationMethodCode = valuationMethodCode;
	}

	public String getValuationMethod() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值方法, valuationMethod))?
				SysDataUtil.getNameFromDataDict(DictCode.估值方法, valuationMethod):valuationMethod;

//		return SysDataUtil.getNameFromDataDict(DictCode.估值方法, valuationMethod);
	}

	public void setValuationMethod(String valuationMethod) {
		this.valuationMethod = valuationMethod;
	}

	public String getValuationPriceCode() {
		return valuationPriceCode;
	}

	public void setValuationPriceCode(String valuationPriceCode) {
		this.valuationPriceCode = valuationPriceCode;
	}

	public String getValuationPrice() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值市价, valuationPrice))?
				SysDataUtil.getNameFromDataDict(DictCode.估值市价, valuationPrice):valuationPrice;

//		return SysDataUtil.getNameFromDataDict(DictCode.估值市价, valuationPrice);
	}

	public void setValuationPrice(String valuationPrice) {
		this.valuationPrice = valuationPrice;
	}

	public String getValuationPrice2Code() {
		return valuationPrice2Code;
	}

	public void setValuationPrice2Code(String valuationPrice2Code) {
		this.valuationPrice2Code = valuationPrice2Code;
	}

	public String getValuationPrice2() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值市价, valuationPrice2))?
				SysDataUtil.getNameFromDataDict(DictCode.估值市价, valuationPrice2):valuationPrice2;

//		return SysDataUtil.getNameFromDataDict(DictCode.估值市价, valuationPrice2);
	}

	public void setValuationPrice2(String valuationPrice2) {
		this.valuationPrice2 = valuationPrice2;
	}

	public BigDecimal getQuotaDigit() {
		return quotaDigit;
	}

	public void setQuotaDigit(BigDecimal quotaDigit) {
		this.quotaDigit = quotaDigit;
	}

	public String getValuationDate() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值日期, valuationDate))?
				SysDataUtil.getNameFromDataDict(DictCode.估值日期, valuationDate):valuationDate;

//		return SysDataUtil.getNameFromDataDict(DictCode.估值日期, valuationDate);
	}

	public void setValuationDate(String valuationDate) {
		this.valuationDate = valuationDate;
	}

	public String getVdate() {
		return vdate;
	}

	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getValuationDateCode() {
		return valuationDateCode;
	}

	public void setValuationDateCode(String valuationDateCode) {
		this.valuationDateCode = valuationDateCode;
	}
	
    public String getPriceTailModeCode() {
        return priceTailModeCode;
    }

    public void setPriceTailModeCode(String priceTailModeCode) {
        this.priceTailModeCode = priceTailModeCode;
    }
    
	public String getPriceTailMode() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值舍入规则, priceTailMode))?
				SysDataUtil.getNameFromDataDict(DictCode.估值舍入规则, priceTailMode):priceTailMode;
	}
	
    public void setPriceTailMode(String priceTailMode) {
        this.priceTailMode = priceTailMode;
    }
    
    public String getPriceClass() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.价格类别, priceClass))?
            SysDataUtil.getNameFromDataDict(DictCode.价格类别, priceClass):priceClass;
    }

    public void setPriceClass(String priceClass) {
        this.priceClass = priceClass;
    }
    
    public String getPriceClassCode() {
        return priceClassCode;
    }

    public void setPriceClassCode(String priceClassCode) {
        this.priceClassCode = priceClassCode;
    }
    
    /**
     * 选中的数据
     */
    private transient List<ValuationMethodScheme001Dto> listValuationMethodSchemeDto;


    public List<ValuationMethodScheme001Dto> getListValuationMethodSchemeDto() {
        return listValuationMethodSchemeDto;
    }

    public void setListValuationMethodSchemeDto(List<ValuationMethodScheme001Dto> listValuationMethodSchemeDto) {
        this.listValuationMethodSchemeDto = listValuationMethodSchemeDto;
    }

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}
}
