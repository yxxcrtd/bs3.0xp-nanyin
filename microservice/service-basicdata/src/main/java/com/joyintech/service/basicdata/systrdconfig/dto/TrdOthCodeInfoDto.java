package com.joyintech.service.basicdata.systrdconfig.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname DeclarationInfoDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/10/14 17:22 
 * @version 1.0 
 */
public class TrdOthCodeInfoDto extends BaseEntity {
    private String finprodId;            //金融产品代码
    private String assetType;            //产品类型
    private String finprodMarketId;      // 市场代码
    private String othCode;              //申报代码
    private String othCodeType;          //申报类型
    private String othName;              //申报资产名称
    private Date declaredStartDate;      // 申报开始日
    private Date declaredEndDate;        //申报终止日

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal declaredPrice;        //申报价格

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getOthCode() {
        return othCode;
    }

    public void setOthCode(String othCode) {
        this.othCode = othCode;
    }

    public String getOthCodeType() {
        return othCodeType;
    }

    public void setOthCodeType(String othCodeType) {
        this.othCodeType = othCodeType;
    }

    public String getOthName() {
        return othName;
    }

    public void setOthName(String othName) {
        this.othName = othName;
    }

    public BigDecimal getDeclaredPrice() {
        return declaredPrice;
    }

    public void setDeclaredPrice(BigDecimal declaredPrice) {
        this.declaredPrice = declaredPrice;
    }

    public Date getDeclaredStartDate() {
        return declaredStartDate;
    }

    public void setDeclaredStartDate(Date declaredStartDate) {
        this.declaredStartDate = declaredStartDate;
    }

    public Date getDeclaredEndDate() {
        return declaredEndDate;
    }

    public void setDeclaredEndDate(Date declaredEndDate) {
        this.declaredEndDate = declaredEndDate;
    }
}
