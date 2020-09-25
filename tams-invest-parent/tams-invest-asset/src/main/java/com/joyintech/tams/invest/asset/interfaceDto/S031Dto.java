package com.joyintech.tams.invest.asset.interfaceDto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 股票要素信息 DTO
 */
public class S031Dto  {
    private  String FINPROD_ID; //资产代码
    private  String FINPROD_MARKET_ID;//市场代码
    private  String FINPROD_NAME;//资产名称
    private  String CCY;//币种
    private  String ISSUER;//发行人
    private  String VDATE;//上市日期
    private  String MDATE;//退市日期
    private  String BOARD;//上市板
    private  String SH_OR_ZH;//是否深港通或者沪港通
    private  String IS_MARGIN_FINPROD;//是否融资融券标的
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

    public String getCCY() {
        return CCY;
    }

    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    public String getISSUER() {
        return ISSUER;
    }

    public void setISSUER(String ISSUER) {
        this.ISSUER = ISSUER;
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

    public String getBOARD() {
        return BOARD;
    }

    public void setBOARD(String BOARD) {
        this.BOARD = BOARD;
    }

    public String getSH_OR_ZH() {
        return SH_OR_ZH;
    }

    public void setSH_OR_ZH(String SH_OR_ZH) {
        this.SH_OR_ZH = SH_OR_ZH;
    }

    public String getIS_MARGIN_FINPROD() {
        return IS_MARGIN_FINPROD;
    }

    public void setIS_MARGIN_FINPROD(String IS_MARGIN_FINPROD) {
        this.IS_MARGIN_FINPROD = IS_MARGIN_FINPROD;
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
