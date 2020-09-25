package com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:27
 */
public class ValFxValuztionInfo001Dto extends BaseEntity {

    /**
     * 金融产品代码（市场代码）
     */
    private String finprodId2;
    
    /**
     * 外汇类型
     */
    private String  foreignexChangeType;

    /**
     * 数据来源
     */
    private String datasourceType;

    /**
     * 估值日期
     */
    private String valDate;

    /**
     * 每日估值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal valuation;

    /**
     * 估值模型
     */
    private String valmodelId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 录入方式，接口、手工等
     */
    private String inputType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * 处理操作
     */
    private String operateType;


    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 取得 金融产品代码（市场代码）
     * @return 金融产品代码（市场代码）
     */
    public String getFinprodId2() {
        return finprodId2;
    }

    /**
     * 设置 金融产品代码（市场代码）
     * @param finprodId2 金融产品代码（市场代码）
     */
    public void setFinprodId2(String finprodId2) {
        this.finprodId2 = finprodId2 == null ? null : finprodId2.trim();
    }

    
    public String getForeignexChangeType() {
		return foreignexChangeType;
	}

	public void setForeignexChangeType(String foreignexChangeType) {
		this.foreignexChangeType = foreignexChangeType == null ? null : foreignexChangeType.trim();
	}

	/**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDatasourceType() {
        return datasourceType;
    }

    /**
     * 设置 数据来源
     * @param datasourceType 数据来源
     */
    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType == null ? null : datasourceType.trim();
    }

    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 每日估值
     * @return 每日估值
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    /**
     * 设置 每日估值
     * @param valuation 每日估值
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 取得 估值模型
     * @return 估值模型
     */
    public String getValmodelId() {
        return valmodelId;
    }

    /**
     * 设置 估值模型
     * @param valmodelId 估值模型
     */
    public void setValmodelId(String valmodelId) {
        this.valmodelId = valmodelId == null ? null : valmodelId.trim();
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
     * 取得 录入方式，接口、手工等
     * @return 录入方式，接口、手工等
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式，接口、手工等
     * @param inputType 录入方式，接口、手工等
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

}
