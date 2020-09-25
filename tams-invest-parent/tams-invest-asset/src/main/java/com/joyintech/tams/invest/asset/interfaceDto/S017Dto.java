package com.joyintech.tams.invest.asset.interfaceDto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 计划类资产要素信息 DTO
 */
public class S017Dto  {
    private  String FINPROD_ID; //资产代码
    private  Long BRANCH; //期次号
    private  String FINPROD_MARKET_ID;//市场代码
    private  String CONTRACT_NO;//合同编号
    private  String FINPROD_NAME;//资产名称
    private  BigDecimal ISSUE_PRICE;//发行价
    private  BigDecimal ISSUE_AMT;//发行规模
    private  String PROFIT_TYPE;//收益类型
    private  String CCY;//币种
    private  String BLN_AREA;//境内外
    private  String MANAGER;//管理人
    private  String TRUSTEE_ID;//托管人
    private  String INVEST_ADVISER;//投资顾问
    private  String VDATE;//成立日
    private  String MDATE;//到期日
    private  String SETTLE_SPEED;//清算速度
    private  String PUR_SPEED;//申购确认速度
    private  String RED_SPEED;//赎回确认速度
    private  String PAY_CYCLE;//付息频率
    private  String INT_TYPE;//利率类型
    private  String RATING_TYPE;//计息利率方式
    private  String BASIS;//计息基础
    private  BigDecimal INVEST_RATE;//初始净投资收益率
    private  BigDecimal LOAD_RATE;//初始借款利率
    private  BigDecimal BUYOUT_RATE;//初始买断利率
    private  String PAY_TYPE;//分红方式
    private  String ACTUAL_MANAGER;//实际管理人
    private  String INVEST_NATURE;//资产投资性质
    private List<S017RecommenderInfoEntity> RECOMMENDER_INFO;//资产推荐信息
    private S017InvestInfoEntity INVEST_INFO;//资产投后信息
    private String CAPI_INCOME_FEATURE;//保本标识
    private String REMARK;//备注
    private List<S001AssetTypeEntity> ASSET_TYPE;//资产分类

    public String getFINPROD_ID() {
        return FINPROD_ID;
    }

    public void setFINPROD_ID(String FINPROD_ID) {
        this.FINPROD_ID = FINPROD_ID;
    }

    public Long getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(Long BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getFINPROD_MARKET_ID() {
        return FINPROD_MARKET_ID;
    }

    public void setFINPROD_MARKET_ID(String FINPROD_MARKET_ID) {
        this.FINPROD_MARKET_ID = FINPROD_MARKET_ID;
    }

    public String getCONTRACT_NO() {
        return CONTRACT_NO;
    }

    public void setCONTRACT_NO(String CONTRACT_NO) {
        this.CONTRACT_NO = CONTRACT_NO;
    }

    public String getFINPROD_NAME() {
        return FINPROD_NAME;
    }

    public void setFINPROD_NAME(String FINPROD_NAME) {
        this.FINPROD_NAME = FINPROD_NAME;
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

    public String getPROFIT_TYPE() {
        return PROFIT_TYPE;
    }

    public void setPROFIT_TYPE(String PROFIT_TYPE) {
        this.PROFIT_TYPE = PROFIT_TYPE;
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

    public String getMANAGER() {
        return MANAGER;
    }

    public void setMANAGER(String MANAGER) {
        this.MANAGER = MANAGER;
    }

    public String getTRUSTEE_ID() {
        return TRUSTEE_ID;
    }

    public void setTRUSTEE_ID(String TRUSTEE_ID) {
        this.TRUSTEE_ID = TRUSTEE_ID;
    }

    public String getINVEST_ADVISER() {
        return INVEST_ADVISER;
    }

    public void setINVEST_ADVISER(String INVEST_ADVISER) {
        this.INVEST_ADVISER = INVEST_ADVISER;
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

    public String getSETTLE_SPEED() {
        return SETTLE_SPEED;
    }

    public void setSETTLE_SPEED(String SETTLE_SPEED) {
        this.SETTLE_SPEED = SETTLE_SPEED;
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

    public String getRATING_TYPE() {
        return RATING_TYPE;
    }

    public void setRATING_TYPE(String RATING_TYPE) {
        this.RATING_TYPE = RATING_TYPE;
    }

    public String getBASIS() {
        return BASIS;
    }

    public void setBASIS(String BASIS) {
        this.BASIS = BASIS;
    }

    public BigDecimal getINVEST_RATE() {
        return INVEST_RATE;
    }

    public void setINVEST_RATE(BigDecimal INVEST_RATE) {
        this.INVEST_RATE = INVEST_RATE;
    }

    public BigDecimal getLOAD_RATE() {
        return LOAD_RATE;
    }

    public void setLOAD_RATE(BigDecimal LOAD_RATE) {
        this.LOAD_RATE = LOAD_RATE;
    }

    public BigDecimal getBUYOUT_RATE() {
        return BUYOUT_RATE;
    }

    public void setBUYOUT_RATE(BigDecimal BUYOUT_RATE) {
        this.BUYOUT_RATE = BUYOUT_RATE;
    }

    public String getPAY_TYPE() {
        return PAY_TYPE;
    }

    public void setPAY_TYPE(String PAY_TYPE) {
        this.PAY_TYPE = PAY_TYPE;
    }

    public String getACTUAL_MANAGER() {
        return ACTUAL_MANAGER;
    }

    public void setACTUAL_MANAGER(String ACTUAL_MANAGER) {
        this.ACTUAL_MANAGER = ACTUAL_MANAGER;
    }

    public String getINVEST_NATURE() {
        return INVEST_NATURE;
    }

    public void setINVEST_NATURE(String INVEST_NATURE) {
        this.INVEST_NATURE = INVEST_NATURE;
    }

    public List<S017RecommenderInfoEntity> getRECOMMENDER_INFO() {
        return RECOMMENDER_INFO;
    }

    public void setRECOMMENDER_INFO(List<S017RecommenderInfoEntity> RECOMMENDER_INFO) {
        this.RECOMMENDER_INFO = RECOMMENDER_INFO;
    }

    public S017InvestInfoEntity getINVEST_INFO() {
        return INVEST_INFO;
    }

    public void setINVEST_INFO(S017InvestInfoEntity INVEST_INFO) {
        this.INVEST_INFO = INVEST_INFO;
    }

    public String getCAPI_INCOME_FEATURE() {
        return CAPI_INCOME_FEATURE;
    }

    public void setCAPI_INCOME_FEATURE(String CAPI_INCOME_FEATURE) {
        this.CAPI_INCOME_FEATURE = CAPI_INCOME_FEATURE;
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
