/*
 * 文件名：CfetsBondDealDialogueQuoteDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.bankuplink.bond.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.bankuplink.bond.validator.CfetsBondDealDialogueQuoteDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 对话报价dto
 * 
 * @author zhangliang
 * @version 1.0 2020年5月13日
 * @see CfetsBondDealDialogueQuoteDto
 * @since 1.0
 */
@ValidLogic(targetClass = CfetsBondDealDialogueQuoteDtoValidator.class)
public class CfetsBondDealDialogueQuoteDto extends CfetsBondDealEntrustOrderDto implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6517441367865558290L;
    
    /**
     * 报价编号（内部代码）
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String quoteId;

    /**
     * 交割日期
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.settleDate")
    private Date settleDate;
    
    /**
     * 应计利息
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.unitInt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;
    
    /**
     * 全价
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.fprice")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitFprice;
    
    /**
     * 净价总额
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.cpriceAmt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;
    
    /**
     * 每百元金额(元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValue;
    
    /**
     * 应计利息总额
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.intAmt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;
    
    /**
     * 全价总额
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.fprinceAmt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;
    
    /**
     * 本金额(元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValueAmt;
    
    /**
     * 画面打开模式
     */
    private String quotaPageModel;
    
    /**
     * 操作类型
     */
    private String operatorType;
    
    /**
     * 回复量
     */
    private BigDecimal responseAmt;
    
    /**
     * 页面Id
     */
    private String pageId;
    
    /**
     * 元素Id
     */
    private String elementId;
    
    /**
     * 按钮操作类型
     */
    private String optType;
    
    /**
     * 组合分配信息
     */
    private List<CfetsBondDealDialogueQuoteDto> cfetsBondDealDialogueQuoteDtoList;

    /**
     * 是否多组合
     */
    private String isDzh;
    
    /**
     * 获取 quoteId
     * @return quoteId.
     */
    public String getQuoteId() {
        return quoteId;
    }


    /**
     * 设置 quoteId
     * @param quoteId quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }


    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public Date getSettleDate() {
        return settleDate;
    }

    
    /**
     * 设置 settleDate
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.myTradeId")
    public String getMySideTrader() {
        return super.getMySideTrader();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.ps")
    public String getPs() {
        return super.getPs();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.my")
    public String getMySide() {
        return super.getMySide();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.counterId")
    public String getCounterId() {
        return super.getCounterId();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.counterTradeId")
    public String getCounterTrader() {
        return super.getCounterTrader();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.bond")
    public String getFinprodId() {
        return super.getFinprodId();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.settleSpeed")
    public String getSettleSpreed() {
        return super.getSettleSpreed();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.bondPrinamt")
    public BigDecimal getAvaNum() {
        return super.getAvaNum();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.cleanPrice")
    public BigDecimal getPrice() {
        return super.getPrice();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.mYield")
    public BigDecimal getMatureYield() {
        return super.getMatureYield();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.settleMethod")
    public String getSettleMethod() {
        return super.getSettleMethod();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.settleType")
    public String getSettleType() {
        return super.getSettleType();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.settleCcy")
    public String getSettleCcy() {
        return super.getSettleCcy();
    }
    

    
    /**
     * 获取 unitInt
     * @return unitInt.
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }


    
    /**
     * 设置 unitInt
     * @param unitInt unitInt
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }


    
    /**
     * 获取 unitFprice
     * @return unitFprice.
     */
    public BigDecimal getUnitFprice() {
        return unitFprice;
    }


    
    /**
     * 设置 unitFprice
     * @param unitFprice unitFprice
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }


    
    /**
     * 获取 cpriceAmt
     * @return cpriceAmt.
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }


    
    /**
     * 设置 cpriceAmt
     * @param cpriceAmt cpriceAmt
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }


    
    /**
     * 获取 parValue
     * @return parValue.
     */
    public BigDecimal getParValue() {
        return parValue;
    }


    
    /**
     * 设置 parValue
     * @param parValue parValue
     */
    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }


    
    /**
     * 获取 intAmt
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }


    
    /**
     * 设置 intAmt
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }


    
    /**
     * 获取 fpriceAmt
     * @return fpriceAmt.
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }


    
    /**
     * 设置 fpriceAmt
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }


    
    /**
     * 获取 parValueAmt
     * @return parValueAmt.
     */
    public BigDecimal getParValueAmt() {
        return parValueAmt;
    }


    
    /**
     * 设置 parValueAmt
     * @param parValueAmt parValueAmt
     */
    public void setParValueAmt(BigDecimal parValueAmt) {
        this.parValueAmt = parValueAmt;
    }


    
    /**
     * 获取 quotaPageModel
     * @return quotaPageModel.
     */
    public String getQuotaPageModel() {
        return quotaPageModel;
    }


    
    /**
     * 设置 quotaPageModel
     * @param quotaPageModel quotaPageModel
     */
    public void setQuotaPageModel(String quotaPageModel) {
        this.quotaPageModel = quotaPageModel;
    }


    
    /**
     * 获取 operatorType
     * @return operatorType.
     */
    public String getOperatorType() {
        return operatorType;
    }


    
    /**
     * 设置 operatorType
     * @param operatorType operatorType
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }


    
    /**
     * 获取 responseAmt
     * @return responseAmt.
     */
    public BigDecimal getResponseAmt() {
        return responseAmt;
    }


    
    /**
     * 设置 responseAmt
     * @param responseAmt responseAmt
     */
    public void setResponseAmt(BigDecimal responseAmt) {
        this.responseAmt = responseAmt;
    }


    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public String getSettleDateStr() {
        return DateUtil.format(this.getSettleDate());
    }
    
    public BigDecimal getResponseAmtStr() {
        if (StringUtil.isNotEmpty(this.getResponseAmt())) {
            return ArithmeticUtil.divide(this.getResponseAmt(), new BigDecimal(10000), 6);
        }
        return null;
    }


	public String getPageId() {
		return pageId;
	}


	public void setPageId(String pageId) {
		this.pageId = pageId;
	}


	public String getElementId() {
		return elementId;
	}


	public void setElementId(String elementId) {
		this.elementId = elementId;
	}


	public String getOptType() {
		return optType;
	}


	public void setOptType(String optType) {
		this.optType = optType;
	}


	public List<CfetsBondDealDialogueQuoteDto> getCfetsBondDealDialogueQuoteDtoList() {
		return cfetsBondDealDialogueQuoteDtoList;
	}


	public void setCfetsBondDealDialogueQuoteDtoList(List<CfetsBondDealDialogueQuoteDto> cfetsBondDealDialogueQuoteDtoList) {
		this.cfetsBondDealDialogueQuoteDtoList = cfetsBondDealDialogueQuoteDtoList;
	}

    public String getIsDzh() {
        return isDzh;
    }

    public void setIsDzh(String isDzh) {
        this.isDzh = isDzh;
    }
}
