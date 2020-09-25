package com.joyintech.tams.invest.asset.interfaceDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 债券要素信息 DTO
 */
public class S001Dto {
    private  String FINPROD_ID; //资产代码
    private  String FINPROD_MARKET_ID;//市场代码
    private  String FINPROD_NAME;//资产名称
    private  String FINPROD_ABBR;//资产简称
    private  String TRADE_MARKET;//交易场所
    private  String COUPON_SPECIES;//息票品种
    private  BigDecimal ISSUE_PRICE;//发行价
    private  BigDecimal ISSUE_AMT;//发行规模
    private  String CCY;//币种
    private  String BLN_AREA;//境内外
    private  String ISSUE_TYPE;//发行方式
    private  String ISSUER;//发行人
    private  String IDATE;//发行日
    private  String VDATE;//起息日
    private  String MDATE;//到期日
    private  String IS_SUS;//是否永续
    private  String SUSTAINABLE_REMARK;//永续条款
    private  String IS_RIGHT;//是否含权
    private  String REGIST_ORG;//登记托管机构
    private  String CAPI_INCOME_FEATURE;//保本标识
    private  String O_FINPROD_ID;//原资产代码
    private  String IS_FIN_ORG;//是否金融机构发行
    private  String IS_CITY_BOND;//是否城投债
    private  String SPONSOR;//担保人
    private  String PAY_CYCLE;//付息频率
    private  String INT_TYPE;//利率类型
    private  String BASIS;//计息基础
    private  BigDecimal RATE;//初始票面利率
    private  String BENCHMARK_ID;//挂钩利率代码
    private  BigDecimal COEFFICIENT;//利率系数
    private  BigDecimal SPREAD_RATE;//利差
    private  BigDecimal HIGHEST_RATE;//利率上限
    private  BigDecimal LOWEST_RATE;//利率下限
    private  String RESET_TYPE;//浮动利率调整规则
    private  BigDecimal OBSERVE_BEF_DAY;//固定间隔数值
    private  String OBSERVE_BEF_UNIT;//固定间隔单位
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

    public String getTRADE_MARKET() {
        return TRADE_MARKET;
    }

    public void setTRADE_MARKET(String TRADE_MARKET) {
        this.TRADE_MARKET = TRADE_MARKET;
    }

    public String getCOUPON_SPECIES() {
        return COUPON_SPECIES;
    }

    public void setCOUPON_SPECIES(String COUPON_SPECIES) {
        this.COUPON_SPECIES = COUPON_SPECIES;
    }

    public BigDecimal getISSUE_PRICE() {
        return ISSUE_PRICE;
    }

    public void setISSUE_PRICE(BigDecimal ISSUE_PRICE) {
        this.ISSUE_PRICE = ISSUE_PRICE;
    }

    public BigDecimal getISSUE_AMT() {
        return ISSUE_AMT;
    }

    public void setISSUE_AMT(BigDecimal ISSUE_AMT) {
        this.ISSUE_AMT = ISSUE_AMT;
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

    public String getIS_SUS() {
        return IS_SUS;
    }

    public void setIS_SUS(String IS_SUS) {
        this.IS_SUS = IS_SUS;
    }

    public String getSUSTAINABLE_REMARK() {
        return SUSTAINABLE_REMARK;
    }

    public void setSUSTAINABLE_REMARK(String SUSTAINABLE_REMARK) {
        this.SUSTAINABLE_REMARK = SUSTAINABLE_REMARK;
    }

    public String getIS_RIGHT() {
        return IS_RIGHT;
    }

    public void setIS_RIGHT(String IS_RIGHT) {
        this.IS_RIGHT = IS_RIGHT;
    }

    public String getREGIST_ORG() {
        return REGIST_ORG;
    }

    public void setREGIST_ORG(String REGIST_ORG) {
        this.REGIST_ORG = REGIST_ORG;
    }

    public String getCAPI_INCOME_FEATURE() {
        return CAPI_INCOME_FEATURE;
    }

    public void setCAPI_INCOME_FEATURE(String CAPI_INCOME_FEATURE) {
        this.CAPI_INCOME_FEATURE = CAPI_INCOME_FEATURE;
    }

    public String getO_FINPROD_ID() {
        return O_FINPROD_ID;
    }

    public void setO_FINPROD_ID(String o_FINPROD_ID) {
        O_FINPROD_ID = o_FINPROD_ID;
    }

    public String getIS_FIN_ORG() {
        return IS_FIN_ORG;
    }

    public void setIS_FIN_ORG(String IS_FIN_ORG) {
        this.IS_FIN_ORG = IS_FIN_ORG;
    }

    public String getIS_CITY_BOND() {
        return IS_CITY_BOND;
    }

    public void setIS_CITY_BOND(String IS_CITY_BOND) {
        this.IS_CITY_BOND = IS_CITY_BOND;
    }

    public String getSPONSOR() {
        return SPONSOR;
    }

    public void setSPONSOR(String SPONSOR) {
        this.SPONSOR = SPONSOR;
    }

    public String getPAY_CYCLE() {
        return PAY_CYCLE;
    }

    public void setPAY_CYCLE(String PAY_CYCLE) {
        this.PAY_CYCLE = PAY_CYCLE;
    }

    public String getINT_TYPE() {
        return INT_TYPE;
    }

    public void setINT_TYPE(String INT_TYPE) {
        this.INT_TYPE = INT_TYPE;
    }

    public String getBASIS() {
        return BASIS;
    }

    public void setBASIS(String BASIS) {
        this.BASIS = BASIS;
    }

    public BigDecimal getRATE() {
        return RATE;
    }

    public void setRATE(BigDecimal RATE) {
        this.RATE = RATE;
    }

    public String getBENCHMARK_ID() {
        return BENCHMARK_ID;
    }

    public void setBENCHMARK_ID(String BENCHMARK_ID) {
        this.BENCHMARK_ID = BENCHMARK_ID;
    }

    public BigDecimal getCOEFFICIENT() {
        return COEFFICIENT;
    }

    public void setCOEFFICIENT(BigDecimal COEFFICIENT) {
        this.COEFFICIENT = COEFFICIENT;
    }

    public BigDecimal getSPREAD_RATE() {
        return SPREAD_RATE;
    }

    public void setSPREAD_RATE(BigDecimal SPREAD_RATE) {
        this.SPREAD_RATE = SPREAD_RATE;
    }

    public BigDecimal getHIGHEST_RATE() {
        return HIGHEST_RATE;
    }

    public void setHIGHEST_RATE(BigDecimal HIGHEST_RATE) {
        this.HIGHEST_RATE = HIGHEST_RATE;
    }

    public BigDecimal getLOWEST_RATE() {
        return LOWEST_RATE;
    }

    public void setLOWEST_RATE(BigDecimal LOWEST_RATE) {
        this.LOWEST_RATE = LOWEST_RATE;
    }

    public String getRESET_TYPE() {
        return RESET_TYPE;
    }

    public void setRESET_TYPE(String RESET_TYPE) {
        this.RESET_TYPE = RESET_TYPE;
    }

    public BigDecimal getOBSERVE_BEF_DAY() {
        return OBSERVE_BEF_DAY;
    }

    public void setOBSERVE_BEF_DAY(BigDecimal OBSERVE_BEF_DAY) {
        this.OBSERVE_BEF_DAY = OBSERVE_BEF_DAY;
    }

    public String getOBSERVE_BEF_UNIT() {
        return OBSERVE_BEF_UNIT;
    }

    public void setOBSERVE_BEF_UNIT(String OBSERVE_BEF_UNIT) {
        this.OBSERVE_BEF_UNIT = OBSERVE_BEF_UNIT;
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
