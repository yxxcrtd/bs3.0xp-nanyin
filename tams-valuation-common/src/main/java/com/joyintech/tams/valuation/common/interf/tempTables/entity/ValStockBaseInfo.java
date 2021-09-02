package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * @ClassName ValStockBaseInfo
 * @Description: 股票标的接口
 * @Author yanya
 * @Date 2021-08-27
 * @Version V1.0
 **/
public class ValStockBaseInfo extends BaseEntity {

    /**资产代码**/
    private String assetId;
    /**股票代码**/
    private String stockId;
    /**股票名称**/
    private String stockName;
    /**数据动作标志**/
    private String dataActionSign;
    /**公司代码**/
    private String customerId;
    /**交易场所**/
    private String tradePlace;
    /**上市板**/
    private String board;
    /**上市日期**/
    private Date vdate;
    /**退市日期**/
    private Date mdate;
    /**是否通道**/
    private String isChl;
    /**通道代码**/
    private String chlId;
    /**港股通市场**/
    private String shOrZh;
    /**是否融资融券标的**/
    private String isMarginFinprod;
    /**币种**/
    private String ccy;


    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getDataActionSign() {
        return dataActionSign;
    }

    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTradePlace() {
        return tradePlace;
    }

    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getIsChl() {
        return isChl;
    }

    public void setIsChl(String isChl) {
        this.isChl = isChl;
    }

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getIsMarginFinprod() {
        return isMarginFinprod;
    }

    public void setIsMarginFinprod(String isMarginFinprod) {
        this.isMarginFinprod = isMarginFinprod;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getShOrZh() {
        return shOrZh;
    }

    public void setShOrZh(String shOrZh) {
        this.shOrZh = shOrZh;
    }
}
