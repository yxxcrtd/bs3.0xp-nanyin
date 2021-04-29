package com.joyintech.tams.svreport.chb.monthreport.dto;

import java.math.BigDecimal;

/**
 * 描述:G06b资产头寸Dto
 * @version
 * @author:  wanshiwei
 * @创建时间: 2021-04-28
 */
public class ChbMonthG06bPosition {

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 资产二级分类
     */
    private String assetType2;

    /**
     * 期初余额
     */
    private BigDecimal qcAssetValue;

    /**
     * 期末余额
     */
    private BigDecimal qmAssetValue;

    /**
     * 融资人主体评级
     */
    private String rating;

    /**
     * 担保方式
     *
     * 01 信用担保/ 02 第三方保证/ 03 抵押/ 04 质押/ 05 信用增级/ 99 其他
     */
    private String guaType;

    /**
     * 交易流通场所
     *
     * 01 银行间债券市场/02 商业银行柜台市场/03 上海证券交易所/04 深圳证券交易所/05 全国中小企业股份转让系统/
     * 06 上海期货交易所/07 郑州商品交易所/08 大连商品交易所/09 中国金融期货交易所/10 上海黄金交易所/
     * 11 区域性产权/资产交易市场/12 区域性商品交易所/13 区域性贵金属交易所/14 艺术品市场/15 基金市场/
     * 16 信托市场/17全国银行间同业拆借市场 /18 上海票据交易所/99 其他交易流通场所
     */
    private String tradeMarket;

    /**
     * 是否结构化分层产品
     */
    private String isConstracte;

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public BigDecimal getQcAssetValue() {
        return qcAssetValue;
    }

    public void setQcAssetValue(BigDecimal qcAssetValue) {
        this.qcAssetValue = qcAssetValue;
    }

    public BigDecimal getQmAssetValue() {
        return qmAssetValue;
    }

    public void setQmAssetValue(BigDecimal qmAssetValue) {
        this.qmAssetValue = qmAssetValue;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGuaType() {
        return guaType;
    }

    public void setGuaType(String guaType) {
        this.guaType = guaType;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getIsConstracte() {
        return isConstracte;
    }

    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte;
    }
}