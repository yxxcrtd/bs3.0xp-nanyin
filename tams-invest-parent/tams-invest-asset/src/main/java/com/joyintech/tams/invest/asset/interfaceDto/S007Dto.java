package com.joyintech.tams.invest.asset.interfaceDto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 基金要素信息 DTO
 */
public class S007Dto {
    private  String FINPROD_ID; //资产代码
    private  String FINPROD_MARKET_ID;//市场代码
    private  String FINPROD_NAME;//资产名称
    private  String FINPROD_ABBR;//资产简称
    private  String PUR_RED_ABBR;//申购赎回简称
    private  String TRM_FUND_ABBR;//交易所基金简称
    private  String TRADE_MARKET;//交易场所
    private  String CCY;//币种
    private  String BLN_AREA;//境内外
    private  String ISSUE_TYPE;//发行方式
    private  String ISSUER;//基金管理人
    private  String IDATE;//发行日
    private  String VDATE;//成立日
    private  String MDATE;//到期日
    private  String CAPI_INCOME_FEATURE;//保本标识
    private  String PUR_SPEED;//申购确认速度
    private  String RED_SPEED;//赎回确认速度
    private  String INVEST_NATURE;//资产投资性质
    private  BigDecimal PUR_START_LOW;//单笔申购金额下限
    private  BigDecimal RED_START_LOW;//单笔赎回金额下限
    private  String PUR_RED_ID;//场内申赎代码
    private  BigDecimal MIN_PRUNIT;//最小申赎单位
    private  String PAY_TYPE;//分红方式
    private  String PAY_CYCLE;//结转频率
    private  String IS_COMPOUND_INT;//是否按日复利
    private  String REMARK;//备注
    private List<S001AssetTypeEntity> ASSET_TYPE;//资产分类

    public String getFINPROD_ID() {
        return FINPROD_ID;
    }

    public void setFINPROD_ID(String FINPROD_ID) {
        this.FINPROD_ID = FINPROD_ID;
    }

    public String getFINPROD_MARKET_ID() {
        return FINPROD_MARKET_ID;
    }

    public void setFINPROD_MARKET_ID(String FINPROD_MARKET_ID) {
        this.FINPROD_MARKET_ID = FINPROD_MARKET_ID;
    }

    public String getFINPROD_NAME() {
        return FINPROD_NAME;
    }

    public void setFINPROD_NAME(String FINPROD_NAME) {
        this.FINPROD_NAME = FINPROD_NAME;
    }

    public String getFINPROD_ABBR() {
        return FINPROD_ABBR;
    }

    public void setFINPROD_ABBR(String FINPROD_ABBR) {
        this.FINPROD_ABBR = FINPROD_ABBR;
    }

    public String getPUR_RED_ABBR() {
        return PUR_RED_ABBR;
    }

    public void setPUR_RED_ABBR(String PUR_RED_ABBR) {
        this.PUR_RED_ABBR = PUR_RED_ABBR;
    }

    public String getTRM_FUND_ABBR() {
        return TRM_FUND_ABBR;
    }

    public void setTRM_FUND_ABBR(String TRM_FUND_ABBR) {
        this.TRM_FUND_ABBR = TRM_FUND_ABBR;
    }

    public String getTRADE_MARKET() {
        return TRADE_MARKET;
    }

    public void setTRADE_MARKET(String TRADE_MARKET) {
        this.TRADE_MARKET = TRADE_MARKET;
    }

    public String getCCY() {
        return CCY;
    }

    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    public String getBLN_AREA() {
        return BLN_AREA;
    }

    public void setBLN_AREA(String BLN_AREA) {
        this.BLN_AREA = BLN_AREA;
    }

    public String getISSUE_TYPE() {
        return ISSUE_TYPE;
    }

    public void setISSUE_TYPE(String ISSUE_TYPE) {
        this.ISSUE_TYPE = ISSUE_TYPE;
    }

    public String getISSUER() {
        return ISSUER;
    }

    public void setISSUER(String ISSUER) {
        this.ISSUER = ISSUER;
    }

    public String getIDATE() {
        return IDATE;
    }

    public void setIDATE(String IDATE) {
        this.IDATE = IDATE;
    }

    public String getVDATE() {
        return VDATE;
    }

    public void setVDATE(String VDATE) {
        this.VDATE = VDATE;
    }

    public String getMDATE() {
        return MDATE;
    }

    public void setMDATE(String MDATE) {
        this.MDATE = MDATE;
    }

    public String getCAPI_INCOME_FEATURE() {
        return CAPI_INCOME_FEATURE;
    }

    public void setCAPI_INCOME_FEATURE(String CAPI_INCOME_FEATURE) {
        this.CAPI_INCOME_FEATURE = CAPI_INCOME_FEATURE;
    }

    public String getPUR_SPEED() {
        return PUR_SPEED;
    }

    public void setPUR_SPEED(String PUR_SPEED) {
        this.PUR_SPEED = PUR_SPEED;
    }

    public String getRED_SPEED() {
        return RED_SPEED;
    }

    public void setRED_SPEED(String RED_SPEED) {
        this.RED_SPEED = RED_SPEED;
    }

    public String getINVEST_NATURE() {
        return INVEST_NATURE;
    }

    public void setINVEST_NATURE(String INVEST_NATURE) {
        this.INVEST_NATURE = INVEST_NATURE;
    }

    public BigDecimal getPUR_START_LOW() {
        return PUR_START_LOW;
    }

    public void setPUR_START_LOW(BigDecimal PUR_START_LOW) {
        this.PUR_START_LOW = PUR_START_LOW;
    }

    public BigDecimal getRED_START_LOW() {
        return RED_START_LOW;
    }

    public void setRED_START_LOW(BigDecimal RED_START_LOW) {
        this.RED_START_LOW = RED_START_LOW;
    }

    public String getPUR_RED_ID() {
        return PUR_RED_ID;
    }

    public void setPUR_RED_ID(String PUR_RED_ID) {
        this.PUR_RED_ID = PUR_RED_ID;
    }

    public BigDecimal getMIN_PRUNIT() {
        return MIN_PRUNIT;
    }

    public void setMIN_PRUNIT(BigDecimal MIN_PRUNIT) {
        this.MIN_PRUNIT = MIN_PRUNIT;
    }

    public String getPAY_TYPE() {
        return PAY_TYPE;
    }

    public void setPAY_TYPE(String PAY_TYPE) {
        this.PAY_TYPE = PAY_TYPE;
    }

    public String getPAY_CYCLE() {
        return PAY_CYCLE;
    }

    public void setPAY_CYCLE(String PAY_CYCLE) {
        this.PAY_CYCLE = PAY_CYCLE;
    }

    public String getIS_COMPOUND_INT() {
        return IS_COMPOUND_INT;
    }

    public void setIS_COMPOUND_INT(String IS_COMPOUND_INT) {
        this.IS_COMPOUND_INT = IS_COMPOUND_INT;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public List<S001AssetTypeEntity> getASSET_TYPE() {
        return ASSET_TYPE;
    }

    public void setASSET_TYPE(List<S001AssetTypeEntity> ASSET_TYPE) {
        this.ASSET_TYPE = ASSET_TYPE;
    }
}
