package com.joyintech.tams.invest.asset.interfaceDto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 存款类资产要素信息 DTO
 */
public class S026Dto  {
    private  String FINPROD_ID; //资产代码
    private  String FINPROD_MARKET_ID;//市场代码
    private  String FINPROD_NAME;//资产名称
    private  BigDecimal ISSUE_AMT;//存款本金规模
    private  String CCY;//币种
    private  String BLN_AREA;//境内外
    private  String ISSUER;//存/借款机构
    private String IDATE;//发行日
    private  String VDATE;//起息日
    private  String MDATE;//到期日
    private  String CAPI_INCOME_FEATURE;//保本标识
    private  String O_FINPROD_ID;//原资产代码
    private  String SPONSOR;//担保人
    private  String INT_TYPE;//利率类型
    private  String BASIS;//计息基础
    private  BigDecimal RATE;//初始利率
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

    public String getO_FINPROD_ID() {
        return O_FINPROD_ID;
    }

    public void setO_FINPROD_ID(String o_FINPROD_ID) {
        O_FINPROD_ID = o_FINPROD_ID;
    }

    public String getSPONSOR() {
        return SPONSOR;
    }

    public void setSPONSOR(String SPONSOR) {
        this.SPONSOR = SPONSOR;
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
