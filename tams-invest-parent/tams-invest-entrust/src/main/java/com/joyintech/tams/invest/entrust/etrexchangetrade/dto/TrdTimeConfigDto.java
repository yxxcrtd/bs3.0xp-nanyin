package com.joyintech.tams.invest.entrust.etrexchangetrade.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @Classname TrdTimeConfigDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/10/13 20:29 
 * @version 1.0 
 */
public class TrdTimeConfigDto extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tradeMarket; //交易场所
    private String  tradePlat; //交易平台
    private String assetType; //一级分类
    private String assetType1; //二级分类
    private String assetType2; //三级分类
    private String earlyOpenTime;//早盘开市时间
    private String earlyCloseTime;//早盘收市时间
    private String noonOpenTime;//午盘开市时间
    private String noonCloseTime;//午盘收市时间
    private String nightOpenTime;//夜盘开市时间
    private String nightCloseTime;//夜盘收市时间
    private String isLastTradeDate;//是否最后交易日

    public String getIsLastTradeDate() {
        return isLastTradeDate;
    }

    public void setIsLastTradeDate(String isLastTradeDate) {
        this.isLastTradeDate = isLastTradeDate;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public String getEarlyOpenTime() {
        return earlyOpenTime;
    }

    public void setEarlyOpenTime(String earlyOpenTime) {
        this.earlyOpenTime = earlyOpenTime;
    }

    public String getEarlyCloseTime() {
        return earlyCloseTime;
    }

    public void setEarlyCloseTime(String earlyCloseTime) {
        this.earlyCloseTime = earlyCloseTime;
    }

    public String getNoonOpenTime() {
        return noonOpenTime;
    }

    public void setNoonOpenTime(String noonOpenTime) {
        this.noonOpenTime = noonOpenTime;
    }

    public String getNoonCloseTime() {
        return noonCloseTime;
    }

    public void setNoonCloseTime(String noonCloseTime) {
        this.noonCloseTime = noonCloseTime;
    }

    public String getNightOpenTime() {
        return nightOpenTime;
    }

    public void setNightOpenTime(String nightOpenTime) {
        this.nightOpenTime = nightOpenTime;
    }

    public String getNightCloseTime() {
        return nightCloseTime;
    }

    public void setNightCloseTime(String nightCloseTime) {
        this.nightCloseTime = nightCloseTime;
    }
}
