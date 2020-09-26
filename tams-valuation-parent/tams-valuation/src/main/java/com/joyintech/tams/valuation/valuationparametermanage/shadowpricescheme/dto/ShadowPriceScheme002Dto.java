/*
 * 文件名：ShadowPriceSchemeDetial.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.shadowpricescheme.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class ShadowPriceScheme002Dto  extends BaseEntity implements ProcessEntity{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 操作类型
	 */
	private String operateType;
	
	 /**
     * 方案明细代码
     */
	@PrimaryKey(value=Field.PK_1)
    private String schemeDetailId;

    /**
     * 方案代码
     */
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.schemeId", groups= {ValidateGroupEdit.class})
    private String schemeId;

    /**
     * 交易市场
     */
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.tradeMarket")
    private String tradeMarket;

    /**
     * 证券品种
     */
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.secType")
    private String secType;

    /**
     * 证券细分类
     */
    private String secDetailType;

    /**
     * 估值市价
     */
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.valuationPrice")
    private String valuationPrice;

    /**
     * 估值第二市价
     */
    private String valuationPrice2;

    /**
     * 行情位数
     */
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.quotaDigit")
    private BigDecimal quotaDigit;

    /**
     * 行情尾数处理
     */
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.quotaDecType")
    private String quotaDecType;

    /**
     * 行情日期
     */
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.valuationDate")
    private String valuationDate;

    /**
     * 开始日
     */
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.vdate")
    private String vdate;
	
    /**
     * 价格类型
     */
    private String priceClass;
	
	/**
	 * 获取 operateType
	 * @return operateType.
	 */
	public String getOperateType() {
		return operateType;
	}
	/**
	 * 设置 operateType
	 * @param operateType operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	/**
	 * 获取 schemeDetailId
	 * @return schemeDetailId.
	 */
	public String getSchemeDetailId() {
		return schemeDetailId;
	}
	/**
	 * 设置 schemeDetailId
	 * @param schemeDetailId schemeDetailId
	 */
	public void setSchemeDetailId(String schemeDetailId) {
		this.schemeDetailId = schemeDetailId;
	}
	/**
	 * 获取 schemeId
	 * @return schemeId.
	 */
	public String getSchemeId() {
		return schemeId;
	}
	/**
	 * 设置 schemeId
	 * @param schemeId schemeId
	 */
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	/**
	 * 获取 tradeMarket
	 * @return tradeMarket.
	 */
	public String getTradeMarket() {
		return  tradeMarket;
	}
	
	public String getTradeMarketFmt() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值交易市场,tradeMarket))?
				SysDataUtil.getNameFromDataDict(DictCode.估值交易市场,tradeMarket):tradeMarket;
//		return  SysDataUtil.getNameFromDataDict(DictCode.估值交易市场,tradeMarket);
	}
	/**
	 * 设置 tradeMarket
	 * @param tradeMarket tradeMarket
	 */
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	/**
	 * 获取 secType
	 * @return secType.
	 */
	public String getSecType() {
		return secType;
	}
	public String getSecTypeFmt() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.证券品种,secType))?
				SysDataUtil.getNameFromDataDict(DictCode.证券品种,secType):secType;
//		return SysDataUtil.getNameFromDataDict(DictCode.证券品种,secType);
	}
	/**
	 * 设置 secType
	 * @param secType secType
	 */
	public void setSecType(String secType) {
		this.secType = secType;
	}
	/**
	 * 获取 secDetailType
	 * @return secDetailType.
	 */
	public String getSecDetailType() {
		return secDetailType;
	}
	public String getSecDetailTypeFmt() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.债券类型,secDetailType))?
				SysDataUtil.getNameFromDataDict(DictCode.债券类型,secDetailType):secDetailType;
//		return SysDataUtil.getNameFromDataDict(DictCode.债券类型,secDetailType);
	}
	/**
	 * 设置 secDetailType
	 * @param secDetailType secDetailType
	 */
	public void setSecDetailType(String secDetailType) {
		this.secDetailType = secDetailType;
	}
	/**
	 * 获取 valuationPrice
	 * @return valuationPrice.
	 */
	public String getValuationPrice() {
		return valuationPrice;
	}
	public String getValuationPriceFmt() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值市价,valuationPrice))?
				SysDataUtil.getNameFromDataDict(DictCode.估值市价,valuationPrice):valuationPrice;
//		return SysDataUtil.getNameFromDataDict(DictCode.估值市价,valuationPrice);
	}
	/**
	 * 设置 valuationPrice
	 * @param valuationPrice valuationPrice
	 */
	public void setValuationPrice(String valuationPrice) {
		this.valuationPrice = valuationPrice;
	}
	/**
	 * 获取 valuationPrice2
	 * @return valuationPrice2.
	 */
	public String getValuationPrice2() {
		return valuationPrice2;
	}
	
	public String getValuationPrice2Fmt() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值市价,valuationPrice2))?
				SysDataUtil.getNameFromDataDict(DictCode.估值市价,valuationPrice2):valuationPrice2;
//		return SysDataUtil.getNameFromDataDict(DictCode.估值市价,valuationPrice2);
	}
	/**
	 * 设置 valuationPrice2
	 * @param valuationPrice2 valuationPrice2
	 */
	public void setValuationPrice2(String valuationPrice2) {
		this.valuationPrice2 = valuationPrice2;
	}
	
	/**
	 * 获取 quotaDigit
	 * @return quotaDigit.
	 */
	public BigDecimal getQuotaDigit() {
		return quotaDigit;
	}
	/**
	 * 设置 quotaDigit
	 * @param quotaDigit quotaDigit
	 */
	public void setQuotaDigit(BigDecimal quotaDigit) {
		this.quotaDigit = quotaDigit;
	}
	/**
	 * 获取 quotaDecType
	 * @return quotaDecType.
	 */
	public String getQuotaDecType() {
		return quotaDecType;
	}
	/**
	 * 设置 quotaDecType
	 * @param quotaDecType quotaDecType
	 */
	public void setQuotaDecType(String quotaDecType) {
		this.quotaDecType = quotaDecType;
	}
	/**
	 * 获取 valuationDate
	 * @return valuationDate.
	 */
	public String getValuationDate() {
		return valuationDate;
	}
	public String getValuationDateFmt() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值日期,valuationDate))?
				SysDataUtil.getNameFromDataDict(DictCode.估值日期,valuationDate):valuationDate;
//		return SysDataUtil.getNameFromDataDict(DictCode.估值日期,valuationDate);
	}
	/**
	 * 设置 valuationDate
	 * @param valuationDate valuationDate
	 */
	public void setValuationDate(String valuationDate) {
		this.valuationDate = valuationDate;
	}
	/**
	 * 获取 vdate
	 * @return vdate.
	 */
	public String getVdate() {
		 return DateUtil.format(DateUtil.parse(vdate),"yyyy-MM-dd");
	}
	/**
	 * 设置 vdate
	 * @param vdate vdate
	 */
	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

    public String getPriceClass() {
        return priceClass;
    }

    public void setPriceClass(String priceClass) {
        this.priceClass = priceClass;
    }
    
    /**
     * 获取 行情尾数处理数据字典
     * 
     * @return 行情尾数处理数据字典.
     */
    public String getQuotaDecTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值舍入规则, this.getQuotaDecType()))?
                SysDataUtil.getNameFromDataDict(DictCode.估值舍入规则, this.getQuotaDecType()):this.getQuotaDecType();
    }
    
    /**
     * 获取 价格类型数据字典
     * 
     * @return 价格类型数据字典.
     */
    public String getPriceClassFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.价格类别, this.getPriceClass()))?
                SysDataUtil.getNameFromDataDict(DictCode.价格类别, this.getPriceClass()):this.getPriceClass();
    }

}
