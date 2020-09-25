package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PlTargetDb extends PlTargetDbKey {
    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 总资产
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetValue;

    /**
     * 净资产
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetNetvalue;

    /**
     * 单位净值/万分收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValue;

    /**
     * 累计单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totUniitValue;

    /**
     * 当日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyYield;

    /**
     * 七日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield7;

    /**
     * 总资产占产品比重
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetRate;

    /**
     * 总资产增长率（较上一日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetGrD;

    /**
     * 总资产增长率（较本周初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetGrW;

    /**
     * 总资产增长率（较本月初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetGrM;

    /**
     * 总资产增长率（较本季初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetGrS;

    /**
     * 总资产增长率（较本年初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetGrY;

    /**
     * 净资产增长率（较上一日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netassetGrD;

    /**
     * 净资产增长率（较本周初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netassetGrW;

    /**
     * 净资产增长率（较本月初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netassetGrM;

    /**
     * 净资产增长率（较本季初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netassetGrS;

    /**
     * 净资产增长率（较本年初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netassetGrY;

    /**
     * 单位净值增长率（较上一日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrD;

    /**
     * 单位净值增长率（较本周初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrW;

    /**
     * 单位净值增长率（较本月初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrM;

    /**
     * 单位净值增长率（较本季初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrS;

    /**
     * 单位净值增长率（较本年初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrY;

    /**
     * 累计单位净值增长率（较上一日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrD2;

    /**
     * 累计单位净值增长率（较本周初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrW2;

    /**
     * 累计单位净值增长率（较本月初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrM2;

    /**
     * 累计单位净值增长率（较本季初）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrS2;

    /**
     * 累计单位净值增长率（较本年初）
     */
    private BigDecimal netunitGrY2;

    /**
     * 组合起息日
     */
    private Date plVdate;

    /**
     * 组合到期日
     */
    private Date plMdate;

    /**
     * 投资经理
     */
    private String invManager;

    /**
     * 波动率（本周）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixW;

    /**
     * 波动率（本月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixM;

    /**
     * 波动率（本季）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixS;

    /**
     * 波动率（本年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixY;

    /**
     * 波动率（成立至今）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixR;

    /**
     * 波动率（最近1个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixLoneM;

    /**
     * 波动率（最近3个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixLthreeM;

    /**
     * 波动率（最近半年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixLHy;

    /**
     * 波动率（最近1年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vixLY;

    /**
     * Sharpe（本周）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeW;

    /**
     * Sharpe（本月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeM;

    /**
     * Sharpe（本季）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeS;

    /**
     * Sharpe（本年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeY;

    /**
     * Sharpe（成立至今）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeR;

    /**
     * Sharpe（最近1个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeLoneM;

    /**
     * Sharpe（最近3个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeLthreeM;

    /**
     * Sharpe（最近半年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeLHy;

    /**
     * Sharpe（最近1年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sharpeLY;

    /**
     * Sortino（本周）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoW;

    /**
     * Sortino（本月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoM;

    /**
     * Sortino（本季）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoS;

    /**
     * Sortino（本年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoY;

    /**
     * Sortino（成立至今）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoR;

    /**
     * Sortino（最近1个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoLoneM;

    /**
     * Sortino（最近3个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoLthreeM;

    /**
     * Sortino（最近半年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoLHy;

    /**
     * Sortino（最近1年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sortinoLY;

    /**
     * 最大回撤（本周）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrW;

    /**
     * 最大回撤（本月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrM;

    /**
     * 最大回撤（本季）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrS;

    /**
     * 最大回撤（本年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrY;

    /**
     * 最大回撤（成立至今）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrR;

    /**
     * 最大回撤（最近1个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrLoneM;

    /**
     * 最大回撤（最近3个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrLthreeM;

    /**
     * 最大回撤（最近半年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrLHy;

    /**
     * 最大回撤（最近1年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxDrLY;

    /**
     * 买卖部分增值（价差收入）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dsclossExp;

    /**
     * 利息收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intincexp;

    /**
     * 持仓部分估值增值（公允价值变动）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valueAdd;

    /**
     * 整体估值增值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalValueAdd;

    /**
     * 整体盈亏
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalFloatIngpl;

    /**
     * 取得 组合名称
     * @return 组合名称
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 组合名称
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName == null ? null : portfolioName.trim();
    }

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 总资产
     * @return 总资产
     */
    public BigDecimal getAssetValue() {
        return assetValue;
    }

    /**
     * 设置 总资产
     * @param assetValue 总资产
     */
    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    /**
     * 取得 净资产
     * @return 净资产
     */
    public BigDecimal getAssetNetvalue() {
        return assetNetvalue;
    }

    /**
     * 设置 净资产
     * @param assetNetvalue 净资产
     */
    public void setAssetNetvalue(BigDecimal assetNetvalue) {
        this.assetNetvalue = assetNetvalue;
    }

    /**
     * 取得 单位净值/万分收益
     * @return 单位净值/万分收益
     */
    public BigDecimal getUnitValue() {
        return unitValue;
    }

    /**
     * 设置 单位净值/万分收益
     * @param unitValue 单位净值/万分收益
     */
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * 取得 累计单位净值
     * @return 累计单位净值
     */
    public BigDecimal getTotUniitValue() {
        return totUniitValue;
    }

    /**
     * 设置 累计单位净值
     * @param totUniitValue 累计单位净值
     */
    public void setTotUniitValue(BigDecimal totUniitValue) {
        this.totUniitValue = totUniitValue;
    }

    /**
     * 取得 当日年化收益率
     * @return 当日年化收益率
     */
    public BigDecimal getTdyYield() {
        return tdyYield;
    }

    /**
     * 设置 当日年化收益率
     * @param tdyYield 当日年化收益率
     */
    public void setTdyYield(BigDecimal tdyYield) {
        this.tdyYield = tdyYield;
    }

    /**
     * 取得 七日年化收益率
     * @return 七日年化收益率
     */
    public BigDecimal getYield7() {
        return yield7;
    }

    /**
     * 设置 七日年化收益率
     * @param yield7 七日年化收益率
     */
    public void setYield7(BigDecimal yield7) {
        this.yield7 = yield7;
    }

    /**
     * 取得 总资产占产品比重
     * @return 总资产占产品比重
     */
    public BigDecimal getAssetRate() {
        return assetRate;
    }

    /**
     * 设置 总资产占产品比重
     * @param assetRate 总资产占产品比重
     */
    public void setAssetRate(BigDecimal assetRate) {
        this.assetRate = assetRate;
    }

    /**
     * 取得 总资产增长率（较上一日）
     * @return 总资产增长率（较上一日）
     */
    public BigDecimal getAssetGrD() {
        return assetGrD;
    }

    /**
     * 设置 总资产增长率（较上一日）
     * @param assetGrD 总资产增长率（较上一日）
     */
    public void setAssetGrD(BigDecimal assetGrD) {
        this.assetGrD = assetGrD;
    }

    /**
     * 取得 总资产增长率（较本周初）
     * @return 总资产增长率（较本周初）
     */
    public BigDecimal getAssetGrW() {
        return assetGrW;
    }

    /**
     * 设置 总资产增长率（较本周初）
     * @param assetGrW 总资产增长率（较本周初）
     */
    public void setAssetGrW(BigDecimal assetGrW) {
        this.assetGrW = assetGrW;
    }

    /**
     * 取得 总资产增长率（较本月初）
     * @return 总资产增长率（较本月初）
     */
    public BigDecimal getAssetGrM() {
        return assetGrM;
    }

    /**
     * 设置 总资产增长率（较本月初）
     * @param assetGrM 总资产增长率（较本月初）
     */
    public void setAssetGrM(BigDecimal assetGrM) {
        this.assetGrM = assetGrM;
    }

    /**
     * 取得 总资产增长率（较本季初）
     * @return 总资产增长率（较本季初）
     */
    public BigDecimal getAssetGrS() {
        return assetGrS;
    }

    /**
     * 设置 总资产增长率（较本季初）
     * @param assetGrS 总资产增长率（较本季初）
     */
    public void setAssetGrS(BigDecimal assetGrS) {
        this.assetGrS = assetGrS;
    }

    /**
     * 取得 总资产增长率（较本年初）
     * @return 总资产增长率（较本年初）
     */
    public BigDecimal getAssetGrY() {
        return assetGrY;
    }

    /**
     * 设置 总资产增长率（较本年初）
     * @param assetGrY 总资产增长率（较本年初）
     */
    public void setAssetGrY(BigDecimal assetGrY) {
        this.assetGrY = assetGrY;
    }

    /**
     * 取得 净资产增长率（较上一日）
     * @return 净资产增长率（较上一日）
     */
    public BigDecimal getNetassetGrD() {
        return netassetGrD;
    }

    /**
     * 设置 净资产增长率（较上一日）
     * @param netassetGrD 净资产增长率（较上一日）
     */
    public void setNetassetGrD(BigDecimal netassetGrD) {
        this.netassetGrD = netassetGrD;
    }

    /**
     * 取得 净资产增长率（较本周初）
     * @return 净资产增长率（较本周初）
     */
    public BigDecimal getNetassetGrW() {
        return netassetGrW;
    }

    /**
     * 设置 净资产增长率（较本周初）
     * @param netassetGrW 净资产增长率（较本周初）
     */
    public void setNetassetGrW(BigDecimal netassetGrW) {
        this.netassetGrW = netassetGrW;
    }

    /**
     * 取得 净资产增长率（较本月初）
     * @return 净资产增长率（较本月初）
     */
    public BigDecimal getNetassetGrM() {
        return netassetGrM;
    }

    /**
     * 设置 净资产增长率（较本月初）
     * @param netassetGrM 净资产增长率（较本月初）
     */
    public void setNetassetGrM(BigDecimal netassetGrM) {
        this.netassetGrM = netassetGrM;
    }

    /**
     * 取得 净资产增长率（较本季初）
     * @return 净资产增长率（较本季初）
     */
    public BigDecimal getNetassetGrS() {
        return netassetGrS;
    }

    /**
     * 设置 净资产增长率（较本季初）
     * @param netassetGrS 净资产增长率（较本季初）
     */
    public void setNetassetGrS(BigDecimal netassetGrS) {
        this.netassetGrS = netassetGrS;
    }

    /**
     * 取得 净资产增长率（较本年初）
     * @return 净资产增长率（较本年初）
     */
    public BigDecimal getNetassetGrY() {
        return netassetGrY;
    }

    /**
     * 设置 净资产增长率（较本年初）
     * @param netassetGrY 净资产增长率（较本年初）
     */
    public void setNetassetGrY(BigDecimal netassetGrY) {
        this.netassetGrY = netassetGrY;
    }

    /**
     * 取得 单位净值增长率（较上一日）
     * @return 单位净值增长率（较上一日）
     */
    public BigDecimal getNetunitGrD() {
        return netunitGrD;
    }

    /**
     * 设置 单位净值增长率（较上一日）
     * @param netunitGrD 单位净值增长率（较上一日）
     */
    public void setNetunitGrD(BigDecimal netunitGrD) {
        this.netunitGrD = netunitGrD;
    }

    /**
     * 取得 单位净值增长率（较本周初）
     * @return 单位净值增长率（较本周初）
     */
    public BigDecimal getNetunitGrW() {
        return netunitGrW;
    }

    /**
     * 设置 单位净值增长率（较本周初）
     * @param netunitGrW 单位净值增长率（较本周初）
     */
    public void setNetunitGrW(BigDecimal netunitGrW) {
        this.netunitGrW = netunitGrW;
    }

    /**
     * 取得 单位净值增长率（较本月初）
     * @return 单位净值增长率（较本月初）
     */
    public BigDecimal getNetunitGrM() {
        return netunitGrM;
    }

    /**
     * 设置 单位净值增长率（较本月初）
     * @param netunitGrM 单位净值增长率（较本月初）
     */
    public void setNetunitGrM(BigDecimal netunitGrM) {
        this.netunitGrM = netunitGrM;
    }

    /**
     * 取得 单位净值增长率（较本季初）
     * @return 单位净值增长率（较本季初）
     */
    public BigDecimal getNetunitGrS() {
        return netunitGrS;
    }

    /**
     * 设置 单位净值增长率（较本季初）
     * @param netunitGrS 单位净值增长率（较本季初）
     */
    public void setNetunitGrS(BigDecimal netunitGrS) {
        this.netunitGrS = netunitGrS;
    }

    /**
     * 取得 单位净值增长率（较本年初）
     * @return 单位净值增长率（较本年初）
     */
    public BigDecimal getNetunitGrY() {
        return netunitGrY;
    }

    /**
     * 设置 单位净值增长率（较本年初）
     * @param netunitGrY 单位净值增长率（较本年初）
     */
    public void setNetunitGrY(BigDecimal netunitGrY) {
        this.netunitGrY = netunitGrY;
    }

    /**
     * 取得 累计单位净值增长率（较上一日）
     * @return 累计单位净值增长率（较上一日）
     */
    public BigDecimal getNetunitGrD2() {
        return netunitGrD2;
    }

    /**
     * 设置 累计单位净值增长率（较上一日）
     * @param netunitGrD2 累计单位净值增长率（较上一日）
     */
    public void setNetunitGrD2(BigDecimal netunitGrD2) {
        this.netunitGrD2 = netunitGrD2;
    }

    /**
     * 取得 累计单位净值增长率（较本周初）
     * @return 累计单位净值增长率（较本周初）
     */
    public BigDecimal getNetunitGrW2() {
        return netunitGrW2;
    }

    /**
     * 设置 累计单位净值增长率（较本周初）
     * @param netunitGrW2 累计单位净值增长率（较本周初）
     */
    public void setNetunitGrW2(BigDecimal netunitGrW2) {
        this.netunitGrW2 = netunitGrW2;
    }

    /**
     * 取得 累计单位净值增长率（较本月初）
     * @return 累计单位净值增长率（较本月初）
     */
    public BigDecimal getNetunitGrM2() {
        return netunitGrM2;
    }

    /**
     * 设置 累计单位净值增长率（较本月初）
     * @param netunitGrM2 累计单位净值增长率（较本月初）
     */
    public void setNetunitGrM2(BigDecimal netunitGrM2) {
        this.netunitGrM2 = netunitGrM2;
    }

    /**
     * 取得 累计单位净值增长率（较本季初）
     * @return 累计单位净值增长率（较本季初）
     */
    public BigDecimal getNetunitGrS2() {
        return netunitGrS2;
    }

    /**
     * 设置 累计单位净值增长率（较本季初）
     * @param netunitGrS2 累计单位净值增长率（较本季初）
     */
    public void setNetunitGrS2(BigDecimal netunitGrS2) {
        this.netunitGrS2 = netunitGrS2;
    }

    /**
     * 取得 累计单位净值增长率（较本年初）
     * @return 累计单位净值增长率（较本年初）
     */
    public BigDecimal getNetunitGrY2() {
        return netunitGrY2;
    }

    /**
     * 设置 累计单位净值增长率（较本年初）
     * @param netunitGrY2 累计单位净值增长率（较本年初）
     */
    public void setNetunitGrY2(BigDecimal netunitGrY2) {
        this.netunitGrY2 = netunitGrY2;
    }

    /**
     * 取得 组合起息日
     * @return 组合起息日
     */
    public Date getPlVdate() {
        return plVdate;
    }

    /**
     * 设置 组合起息日
     * @param plVdate 组合起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPlVdate(Date plVdate) {
        this.plVdate = plVdate;
    }

    /**
     * 取得 组合到期日
     * @return 组合到期日
     */
    public Date getPlMdate() {
        return plMdate;
    }

    /**
     * 设置 组合到期日
     * @param plMdate 组合到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPlMdate(Date plMdate) {
        this.plMdate = plMdate;
    }

    /**
     * 取得 投资经理
     * @return 投资经理
     */
    public String getInvManager() {
        return invManager;
    }

    /**
     * 设置 投资经理
     * @param invManager 投资经理
     */
    public void setInvManager(String invManager) {
        this.invManager = invManager == null ? null : invManager.trim();
    }

    /**
     * 取得 波动率（本周）
     * @return 波动率（本周）
     */
    public BigDecimal getVixW() {
        return vixW;
    }

    /**
     * 设置 波动率（本周）
     * @param vixW 波动率（本周）
     */
    public void setVixW(BigDecimal vixW) {
        this.vixW = vixW;
    }

    /**
     * 取得 波动率（本月）
     * @return 波动率（本月）
     */
    public BigDecimal getVixM() {
        return vixM;
    }

    /**
     * 设置 波动率（本月）
     * @param vixM 波动率（本月）
     */
    public void setVixM(BigDecimal vixM) {
        this.vixM = vixM;
    }

    /**
     * 取得 波动率（本季）
     * @return 波动率（本季）
     */
    public BigDecimal getVixS() {
        return vixS;
    }

    /**
     * 设置 波动率（本季）
     * @param vixS 波动率（本季）
     */
    public void setVixS(BigDecimal vixS) {
        this.vixS = vixS;
    }

    /**
     * 取得 波动率（本年）
     * @return 波动率（本年）
     */
    public BigDecimal getVixY() {
        return vixY;
    }

    /**
     * 设置 波动率（本年）
     * @param vixY 波动率（本年）
     */
    public void setVixY(BigDecimal vixY) {
        this.vixY = vixY;
    }

    /**
     * 取得 波动率（成立至今）
     * @return 波动率（成立至今）
     */
    public BigDecimal getVixR() {
        return vixR;
    }

    /**
     * 设置 波动率（成立至今）
     * @param vixR 波动率（成立至今）
     */
    public void setVixR(BigDecimal vixR) {
        this.vixR = vixR;
    }

    /**
     * 取得 波动率（最近1个月）
     * @return 波动率（最近1个月）
     */
    public BigDecimal getVixLoneM() {
        return vixLoneM;
    }

    /**
     * 设置 波动率（最近1个月）
     * @param vixLoneM 波动率（最近1个月）
     */
    public void setVixLoneM(BigDecimal vixLoneM) {
        this.vixLoneM = vixLoneM;
    }

    /**
     * 取得 波动率（最近3个月）
     * @return 波动率（最近3个月）
     */
    public BigDecimal getVixLthreeM() {
        return vixLthreeM;
    }

    /**
     * 设置 波动率（最近3个月）
     * @param vixLthreeM 波动率（最近3个月）
     */
    public void setVixLthreeM(BigDecimal vixLthreeM) {
        this.vixLthreeM = vixLthreeM;
    }

    /**
     * 取得 波动率（最近半年）
     * @return 波动率（最近半年）
     */
    public BigDecimal getVixLHy() {
        return vixLHy;
    }

    /**
     * 设置 波动率（最近半年）
     * @param vixLHy 波动率（最近半年）
     */
    public void setVixLHy(BigDecimal vixLHy) {
        this.vixLHy = vixLHy;
    }

    /**
     * 取得 波动率（最近1年）
     * @return 波动率（最近1年）
     */
    public BigDecimal getVixLY() {
        return vixLY;
    }

    /**
     * 设置 波动率（最近1年）
     * @param vixLY 波动率（最近1年）
     */
    public void setVixLY(BigDecimal vixLY) {
        this.vixLY = vixLY;
    }

    /**
     * 取得 Sharpe（本周）
     * @return Sharpe（本周）
     */
    public BigDecimal getSharpeW() {
        return sharpeW;
    }

    /**
     * 设置 Sharpe（本周）
     * @param sharpeW Sharpe（本周）
     */
    public void setSharpeW(BigDecimal sharpeW) {
        this.sharpeW = sharpeW;
    }

    /**
     * 取得 Sharpe（本月）
     * @return Sharpe（本月）
     */
    public BigDecimal getSharpeM() {
        return sharpeM;
    }

    /**
     * 设置 Sharpe（本月）
     * @param sharpeM Sharpe（本月）
     */
    public void setSharpeM(BigDecimal sharpeM) {
        this.sharpeM = sharpeM;
    }

    /**
     * 取得 Sharpe（本季）
     * @return Sharpe（本季）
     */
    public BigDecimal getSharpeS() {
        return sharpeS;
    }

    /**
     * 设置 Sharpe（本季）
     * @param sharpeS Sharpe（本季）
     */
    public void setSharpeS(BigDecimal sharpeS) {
        this.sharpeS = sharpeS;
    }

    /**
     * 取得 Sharpe（本年）
     * @return Sharpe（本年）
     */
    public BigDecimal getSharpeY() {
        return sharpeY;
    }

    /**
     * 设置 Sharpe（本年）
     * @param sharpeY Sharpe（本年）
     */
    public void setSharpeY(BigDecimal sharpeY) {
        this.sharpeY = sharpeY;
    }

    /**
     * 取得 Sharpe（成立至今）
     * @return Sharpe（成立至今）
     */
    public BigDecimal getSharpeR() {
        return sharpeR;
    }

    /**
     * 设置 Sharpe（成立至今）
     * @param sharpeR Sharpe（成立至今）
     */
    public void setSharpeR(BigDecimal sharpeR) {
        this.sharpeR = sharpeR;
    }

    /**
     * 取得 Sharpe（最近1个月）
     * @return Sharpe（最近1个月）
     */
    public BigDecimal getSharpeLoneM() {
        return sharpeLoneM;
    }

    /**
     * 设置 Sharpe（最近1个月）
     * @param sharpeLoneM Sharpe（最近1个月）
     */
    public void setSharpeLoneM(BigDecimal sharpeLoneM) {
        this.sharpeLoneM = sharpeLoneM;
    }

    /**
     * 取得 Sharpe（最近3个月）
     * @return Sharpe（最近3个月）
     */
    public BigDecimal getSharpeLthreeM() {
        return sharpeLthreeM;
    }

    /**
     * 设置 Sharpe（最近3个月）
     * @param sharpeLthreeM Sharpe（最近3个月）
     */
    public void setSharpeLthreeM(BigDecimal sharpeLthreeM) {
        this.sharpeLthreeM = sharpeLthreeM;
    }

    /**
     * 取得 Sharpe（最近半年）
     * @return Sharpe（最近半年）
     */
    public BigDecimal getSharpeLHy() {
        return sharpeLHy;
    }

    /**
     * 设置 Sharpe（最近半年）
     * @param sharpeLHy Sharpe（最近半年）
     */
    public void setSharpeLHy(BigDecimal sharpeLHy) {
        this.sharpeLHy = sharpeLHy;
    }

    /**
     * 取得 Sharpe（最近1年）
     * @return Sharpe（最近1年）
     */
    public BigDecimal getSharpeLY() {
        return sharpeLY;
    }

    /**
     * 设置 Sharpe（最近1年）
     * @param sharpeLY Sharpe（最近1年）
     */
    public void setSharpeLY(BigDecimal sharpeLY) {
        this.sharpeLY = sharpeLY;
    }

    /**
     * 取得 Sortino（本周）
     * @return Sortino（本周）
     */
    public BigDecimal getSortinoW() {
        return sortinoW;
    }

    /**
     * 设置 Sortino（本周）
     * @param sortinoW Sortino（本周）
     */
    public void setSortinoW(BigDecimal sortinoW) {
        this.sortinoW = sortinoW;
    }

    /**
     * 取得 Sortino（本月）
     * @return Sortino（本月）
     */
    public BigDecimal getSortinoM() {
        return sortinoM;
    }

    /**
     * 设置 Sortino（本月）
     * @param sortinoM Sortino（本月）
     */
    public void setSortinoM(BigDecimal sortinoM) {
        this.sortinoM = sortinoM;
    }

    /**
     * 取得 Sortino（本季）
     * @return Sortino（本季）
     */
    public BigDecimal getSortinoS() {
        return sortinoS;
    }

    /**
     * 设置 Sortino（本季）
     * @param sortinoS Sortino（本季）
     */
    public void setSortinoS(BigDecimal sortinoS) {
        this.sortinoS = sortinoS;
    }

    /**
     * 取得 Sortino（本年）
     * @return Sortino（本年）
     */
    public BigDecimal getSortinoY() {
        return sortinoY;
    }

    /**
     * 设置 Sortino（本年）
     * @param sortinoY Sortino（本年）
     */
    public void setSortinoY(BigDecimal sortinoY) {
        this.sortinoY = sortinoY;
    }

    /**
     * 取得 Sortino（成立至今）
     * @return Sortino（成立至今）
     */
    public BigDecimal getSortinoR() {
        return sortinoR;
    }

    /**
     * 设置 Sortino（成立至今）
     * @param sortinoR Sortino（成立至今）
     */
    public void setSortinoR(BigDecimal sortinoR) {
        this.sortinoR = sortinoR;
    }

    /**
     * 取得 Sortino（最近1个月）
     * @return Sortino（最近1个月）
     */
    public BigDecimal getSortinoLoneM() {
        return sortinoLoneM;
    }

    /**
     * 设置 Sortino（最近1个月）
     * @param sortinoLoneM Sortino（最近1个月）
     */
    public void setSortinoLoneM(BigDecimal sortinoLoneM) {
        this.sortinoLoneM = sortinoLoneM;
    }

    /**
     * 取得 Sortino（最近3个月）
     * @return Sortino（最近3个月）
     */
    public BigDecimal getSortinoLthreeM() {
        return sortinoLthreeM;
    }

    /**
     * 设置 Sortino（最近3个月）
     * @param sortinoLthreeM Sortino（最近3个月）
     */
    public void setSortinoLthreeM(BigDecimal sortinoLthreeM) {
        this.sortinoLthreeM = sortinoLthreeM;
    }

    /**
     * 取得 Sortino（最近半年）
     * @return Sortino（最近半年）
     */
    public BigDecimal getSortinoLHy() {
        return sortinoLHy;
    }

    /**
     * 设置 Sortino（最近半年）
     * @param sortinoLHy Sortino（最近半年）
     */
    public void setSortinoLHy(BigDecimal sortinoLHy) {
        this.sortinoLHy = sortinoLHy;
    }

    /**
     * 取得 Sortino（最近1年）
     * @return Sortino（最近1年）
     */
    public BigDecimal getSortinoLY() {
        return sortinoLY;
    }

    /**
     * 设置 Sortino（最近1年）
     * @param sortinoLY Sortino（最近1年）
     */
    public void setSortinoLY(BigDecimal sortinoLY) {
        this.sortinoLY = sortinoLY;
    }

    /**
     * 取得 最大回撤（本周）
     * @return 最大回撤（本周）
     */
    public BigDecimal getMaxDrW() {
        return maxDrW;
    }

    /**
     * 设置 最大回撤（本周）
     * @param maxDrW 最大回撤（本周）
     */
    public void setMaxDrW(BigDecimal maxDrW) {
        this.maxDrW = maxDrW;
    }

    /**
     * 取得 最大回撤（本月）
     * @return 最大回撤（本月）
     */
    public BigDecimal getMaxDrM() {
        return maxDrM;
    }

    /**
     * 设置 最大回撤（本月）
     * @param maxDrM 最大回撤（本月）
     */
    public void setMaxDrM(BigDecimal maxDrM) {
        this.maxDrM = maxDrM;
    }

    /**
     * 取得 最大回撤（本季）
     * @return 最大回撤（本季）
     */
    public BigDecimal getMaxDrS() {
        return maxDrS;
    }

    /**
     * 设置 最大回撤（本季）
     * @param maxDrS 最大回撤（本季）
     */
    public void setMaxDrS(BigDecimal maxDrS) {
        this.maxDrS = maxDrS;
    }

    /**
     * 取得 最大回撤（本年）
     * @return 最大回撤（本年）
     */
    public BigDecimal getMaxDrY() {
        return maxDrY;
    }

    /**
     * 设置 最大回撤（本年）
     * @param maxDrY 最大回撤（本年）
     */
    public void setMaxDrY(BigDecimal maxDrY) {
        this.maxDrY = maxDrY;
    }

    /**
     * 取得 最大回撤（成立至今）
     * @return 最大回撤（成立至今）
     */
    public BigDecimal getMaxDrR() {
        return maxDrR;
    }

    /**
     * 设置 最大回撤（成立至今）
     * @param maxDrR 最大回撤（成立至今）
     */
    public void setMaxDrR(BigDecimal maxDrR) {
        this.maxDrR = maxDrR;
    }

    /**
     * 取得 最大回撤（最近1个月）
     * @return 最大回撤（最近1个月）
     */
    public BigDecimal getMaxDrLoneM() {
        return maxDrLoneM;
    }

    /**
     * 设置 最大回撤（最近1个月）
     * @param maxDrLoneM 最大回撤（最近1个月）
     */
    public void setMaxDrLoneM(BigDecimal maxDrLoneM) {
        this.maxDrLoneM = maxDrLoneM;
    }

    /**
     * 取得 最大回撤（最近3个月）
     * @return 最大回撤（最近3个月）
     */
    public BigDecimal getMaxDrLthreeM() {
        return maxDrLthreeM;
    }

    /**
     * 设置 最大回撤（最近3个月）
     * @param maxDrLthreeM 最大回撤（最近3个月）
     */
    public void setMaxDrLthreeM(BigDecimal maxDrLthreeM) {
        this.maxDrLthreeM = maxDrLthreeM;
    }

    /**
     * 取得 最大回撤（最近半年）
     * @return 最大回撤（最近半年）
     */
    public BigDecimal getMaxDrLHy() {
        return maxDrLHy;
    }

    /**
     * 设置 最大回撤（最近半年）
     * @param maxDrLHy 最大回撤（最近半年）
     */
    public void setMaxDrLHy(BigDecimal maxDrLHy) {
        this.maxDrLHy = maxDrLHy;
    }

    /**
     * 取得 最大回撤（最近1年）
     * @return 最大回撤（最近1年）
     */
    public BigDecimal getMaxDrLY() {
        return maxDrLY;
    }

    /**
     * 设置 最大回撤（最近1年）
     * @param maxDrLY 最大回撤（最近1年）
     */
    public void setMaxDrLY(BigDecimal maxDrLY) {
        this.maxDrLY = maxDrLY;
    }

    /**
     * 取得 买卖部分增值（价差收入）
     * @return 买卖部分增值（价差收入）
     */
    public BigDecimal getDsclossExp() {
        return dsclossExp;
    }

    /**
     * 设置 买卖部分增值（价差收入）
     * @param dsclossExp 买卖部分增值（价差收入）
     */
    public void setDsclossExp(BigDecimal dsclossExp) {
        this.dsclossExp = dsclossExp;
    }

    /**
     * 取得 利息收入
     * @return 利息收入
     */
    public BigDecimal getIntincexp() {
        return intincexp;
    }

    /**
     * 设置 利息收入
     * @param intincexp 利息收入
     */
    public void setIntincexp(BigDecimal intincexp) {
        this.intincexp = intincexp;
    }

    /**
     * 取得 持仓部分估值增值（公允价值变动）
     * @return 持仓部分估值增值（公允价值变动）
     */
    public BigDecimal getValueAdd() {
        return valueAdd;
    }

    /**
     * 设置 持仓部分估值增值（公允价值变动）
     * @param valueAdd 持仓部分估值增值（公允价值变动）
     */
    public void setValueAdd(BigDecimal valueAdd) {
        this.valueAdd = valueAdd;
    }

    /**
     * 取得 整体估值增值
     * @return 整体估值增值
     */
    public BigDecimal getTotalValueAdd() {
        return totalValueAdd;
    }

    /**
     * 设置 整体估值增值
     * @param totalValueAdd 整体估值增值
     */
    public void setTotalValueAdd(BigDecimal totalValueAdd) {
        this.totalValueAdd = totalValueAdd;
    }

    /**
     * 取得 整体盈亏
     * @return 整体盈亏
     */
    public BigDecimal getTotalFloatIngpl() {
        return totalFloatIngpl;
    }

    /**
     * 设置 整体盈亏
     * @param totalFloatIngpl 整体盈亏
     */
    public void setTotalFloatIngpl(BigDecimal totalFloatIngpl) {
        this.totalFloatIngpl = totalFloatIngpl;
    }
}