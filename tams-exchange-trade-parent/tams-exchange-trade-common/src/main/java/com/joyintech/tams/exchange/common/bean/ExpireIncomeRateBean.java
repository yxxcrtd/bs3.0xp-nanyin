package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "到期收益率计算接口请求报文", description = "到期收益率计算接口请求报文")
public class ExpireIncomeRateBean implements Serializable {
    private static final long serialVersionUID = -1027162939930687815L;

    @ApiModelProperty(required = false, name = "status", value = "状态")
    private String status;
    @ApiModelProperty(required = false, name = "createUser", value = "创建人")
    private String createUser;
    @ApiModelProperty(required = false, name = "createTime", value = "创建时间")
    private String createTime;
    @ApiModelProperty(required = false, name = "updateUser", value = "修改人")
    private String updateUser;
    @ApiModelProperty(required = false, name = "updateTime", value = "修改时间")
    private String updateTime;
    @ApiModelProperty(required = true, name = "finprodId", value = "资产代码")
    private String finprodId;
    @ApiModelProperty(required = true, name = "settleDate", value = "交割日")
    private String settleDate;
    @ApiModelProperty(required = true, name = "unitCprice", value = "净价")
    private Double unitCprice;
    @ApiModelProperty(required = false, name = "unitInt", value = "单位利息")
    private Double unitInt;
    @ApiModelProperty(required = false, name = "unitFprice", value = "全价")
    private Double unitFprice;
    @ApiModelProperty(required = false, name = "cpriceAmt", value = "净价总额")
    private Double cpriceAmt;
    @ApiModelProperty(required = false, name = "prinAmt", value = "券面总额")
    private Double prinAmt;
    @ApiModelProperty(required = false, name = "intAmt", value = "利息总额")
    private Double intAmt;
    @ApiModelProperty(required = false, name = "fpriceAmt", value = "全价总额")
    private Double fpriceAmt;
    @ApiModelProperty(required = true, name = "tradeAmt", value = "交易总额")
    private Double tradeAmt;
    @ApiModelProperty(required = false, name = "yield", value = "到期收益率")
    private Double yield;
    @ApiModelProperty(required = true, name = "invAim", value = "投资目的")
    private String invAim;
    @ApiModelProperty(required = true, name = "tradeMarket", value = " 交易市场（03-上海证券交易所，04-深圳证券交易所）")
    private String tradeMarket;
    @ApiModelProperty(required = true, name = "tradeMarketMode", value = "市场等级（01-一级市场，02-二级市场）")
    private String tradeMarketMode;
    @ApiModelProperty(required = false, name = "calculateField", value = "参与计算字段")
    private String calculateField;
    @ApiModelProperty(required = false, name = "integrateIntAmt", value = "综合利息总额")
    private Double integrateIntAmt;
    @ApiModelProperty(required = false, name = "integrateFpriceAmt", value = "综合全价总额")
    private Double integrateFpriceAmt;
    @ApiModelProperty(required = false, name = "integrateCpriceAmt", value = "综合净价总额")
    private Double integrateCpriceAmt;
    @ApiModelProperty(required = false, name = "mdateStr", value = "到期日")
    private String mdateStr;
    @ApiModelProperty(required = false, name = "calculateType", value = "计算模式（1-净价，2-全价，3-到期收益率）",example ="3")
    private int calculateType = 3;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public Double getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(Double unitCprice) {
        this.unitCprice = unitCprice;
    }

    public Double getUnitInt() {
        return unitInt;
    }

    public void setUnitInt(Double unitInt) {
        this.unitInt = unitInt;
    }

    public Double getUnitFprice() {
        return unitFprice;
    }

    public void setUnitFprice(Double unitFprice) {
        this.unitFprice = unitFprice;
    }

    public Double getCpriceAmt() {
        return cpriceAmt;
    }

    public void setCpriceAmt(Double cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    public Double getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(Double prinAmt) {
        this.prinAmt = prinAmt;
    }

    public Double getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(Double intAmt) {
        this.intAmt = intAmt;
    }

    public Double getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(Double fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public Double getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(Double tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode;
    }

    public String getCalculateField() {
        return calculateField;
    }

    public void setCalculateField(String calculateField) {
        this.calculateField = calculateField;
    }

    public Double getIntegrateIntAmt() {
        return integrateIntAmt;
    }

    public void setIntegrateIntAmt(Double integrateIntAmt) {
        this.integrateIntAmt = integrateIntAmt;
    }

    public Double getIntegrateFpriceAmt() {
        return integrateFpriceAmt;
    }

    public void setIntegrateFpriceAmt(Double integrateFpriceAmt) {
        this.integrateFpriceAmt = integrateFpriceAmt;
    }

    public Double getIntegrateCpriceAmt() {
        return integrateCpriceAmt;
    }

    public void setIntegrateCpriceAmt(Double integrateCpriceAmt) {
        this.integrateCpriceAmt = integrateCpriceAmt;
    }

    public String getMdateStr() {
        return mdateStr;
    }

    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }

    public int getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(int calculateType) {
        this.calculateType = calculateType;
    }
}
