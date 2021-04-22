package com.joyintech.service.basicdata.systrdconfig.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.security.LoginUserUtil;

import java.math.BigDecimal;
import java.util.Date;

public class StockInfoDto extends BaseEntity {
    public StockInfoDto(){
        super.setCreateUser(null);
        super.setUpdateUser(null);
        super.setCreateTime(null);
        super.setUpdateTime(null);
        super.setStatus(null);
    }
    // 内部合约代码,市场代码，六位数+市场后缀
    private String 	innerCode;
    // 市场 , 市场后缀SZ或者SH
    private String 	market;
    // 合约代码,交易所标准代码，不带市场后缀
    private String 	stockCode;
    // 合约名称-简称
    private String 	stockName;
    // 资产类型-资产大类
    private String 	assetType;
    // 资产类型-资产二级分类
    private String 	assetType1;
    // 交易平台
    private String 	tradPlat;
    // 面值
    private BigDecimal parValue;
    // 转让类型
    private String 	stbtransType;
    // 转让状态
    private String 	stbtransStatus;
    // 证券子类 三级分类（股票区分科创板、创业板、中小板）
    private String 	subStockType;
    // 除权除息标志
    private String 	exdividendFlag;
    // 债券投资标志
    private String 	bondinvFlag;
    // 证券状态
    private String 	stkcodeStatus;
    // 转让标志
    private String 	stbtransFlag;
    // 净价交易标志
    private String 	netPricFlag;
    // 允许交易的平台
    private String 	tradePlat;

    //期货合约乘数
    private BigDecimal contractMultiplier;

    //交易所基金类型（ETF)
    private String trmFundType;

    //ETF类型
    private String etfType;

    //申购赎回代码
    private String purRedId;

    //申购赎回简称
    private String purRedAbbr;

    public String getPurRedId() {
        return purRedId;
    }

    public void setPurRedId(String purRedId) {
        this.purRedId = purRedId;
    }

    public String getPurRedAbbr() {
        return purRedAbbr;
    }

    public void setPurRedAbbr(String purRedAbbr) {
        this.purRedAbbr = purRedAbbr;
    }

    public BigDecimal getContractMultiplier() {
        return contractMultiplier;
    }

    public void setContractMultiplier(BigDecimal contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
    }

    public String getTrmFundType() {
        return trmFundType;
    }

    public void setTrmFundType(String trmFundType) {
        this.trmFundType = trmFundType;
    }

    public String getEtfType() {
        return etfType;
    }

    public void setEtfType(String etfType) {
        this.etfType = etfType;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getStockCode() {
        if (stockCode != null && stockCode.indexOf(".") > 0){
            return stockCode.substring(0,stockCode.indexOf("."));
        }
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
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

    public String getTradPlat() {
        return tradPlat;
    }

    public void setTradPlat(String tradPlat) {
        this.tradPlat = tradPlat;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    public String getStbtransType() {
        return stbtransType;
    }

    public void setStbtransType(String stbtransType) {
        this.stbtransType = stbtransType;
    }

    public String getStbtransStatus() {
        return stbtransStatus;
    }

    public void setStbtransStatus(String stbtransStatus) {
        this.stbtransStatus = stbtransStatus;
    }

    public String getSubStockType() {
        return subStockType;
    }

    public void setSubStockType(String subStockType) {
        this.subStockType = subStockType;
    }

    public String getExdividendFlag() {
        return exdividendFlag;
    }

    public void setExdividendFlag(String exdividendFlag) {
        this.exdividendFlag = exdividendFlag;
    }

    public String getBondinvFlag() {
        return bondinvFlag;
    }

    public void setBondinvFlag(String bondinvFlag) {
        this.bondinvFlag = bondinvFlag;
    }

    public String getStkcodeStatus() {
        return stkcodeStatus;
    }

    public void setStkcodeStatus(String stkcodeStatus) {
        this.stkcodeStatus = stkcodeStatus;
    }

    public String getStbtransFlag() {
        return stbtransFlag;
    }

    public void setStbtransFlag(String stbtransFlag) {
        this.stbtransFlag = stbtransFlag;
    }

    public String getNetPricFlag() {
        return netPricFlag;
    }

    public void setNetPricFlag(String netPricFlag) {
        this.netPricFlag = netPricFlag;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }
}
