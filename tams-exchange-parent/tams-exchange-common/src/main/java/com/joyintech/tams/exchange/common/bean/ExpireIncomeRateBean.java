package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;

/**
 * create By MAXX on 2020/5/8
 */
public class ExpireIncomeRateBean implements Serializable {

    private String status;          // 状态
    private String createUser;      // 创建人
    private String createTime;      // 创建时间
    private String updateUser;      // 修改人
    private String updateTime;      // 修改时间
    private String finprodId;       // 资产代码
    private String settleDate;      // 交割日
    private Double unitCprice;      // 净价
    private Double unitInt;         // 单位利息
    private Double unitFprice;      // 全价
    private Double cpriceAmt;       // 净价总额
    private Double prinAmt;         // 券面总额
    private Double intAmt;          // 利息总额
    private Double fpriceAmt;       // 全价总额
    private Double tradeAmt;        // 交易总额
    private Double yield;           // 到期收益率
    private String invAim;          // 投资目的
    private String tradeMarket;     // 交易市场 03-上海证券交易所;04-深圳证券交易所
    private String tradeMarketMode; // 市场等级 一级市场 01 二级市场
    private String calculateField;  // 参与计算字段
    private Double integrateIntAmt; // 综合利息总额
    private Double integrateFpriceAmt;  // 综合全价总额
    private Double integrateCpriceAmt;  // 综合净价总额
    private String mdateStr;            // 到期日

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
}
