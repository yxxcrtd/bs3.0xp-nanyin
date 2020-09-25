package com.joyintech.tams.invest.product.investmentevaluate.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * JYL 投资评价管理
 */
public class FinInvestmentEvaluate001Dto extends BaseEntity {
    /**
    * 组合代码
    */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产代码
     */
    private String assetId;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 起始日
     */
    private Date vdate;
    /**
     * 评价日
     */
    private Date mdate;
    /**
     * 策略代码
     */
    private String strategyId;
    /**
     * 策略名称
     */
    private String strategyName;
//    /**
//     * 评价指标
//     */
//    private String evalIndex;
//    /**
//     * 评价指标值
//     */
//    private String indexId;
    /**
     * 组合份额
     */
    private String portfolioShare;
    /**
     *资产大类
     */
    private String finprodType2;
    /**
     * 资产二级分类
     */
    private String type9;
    /**
     * 资产三级分类
     */
    private String type10;
    /**
     * 资产四级分类
     */
    private String type11;
    /**
     * 资产五级分类
     */
    private String type12;
    /**
     * 持仓
     */
    private BigDecimal shareAmt;
    /**
     * 资产净值
     */
    private BigDecimal netAssetValue;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getPortfolioShare() {
        return portfolioShare;
    }

    public void setPortfolioShare(String portfolioShare) {
        this.portfolioShare = portfolioShare;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public String getFinprodType2Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用,getFinprodType2());
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getType9() {
        return type9;
    }

    public String getType9Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类,getType9());
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public String getType10() {
        return type10;
    }

    public String getType10Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类,getType10());
    }

    public void setType10(String type10) {
        this.type10 = type10;
    }

    public String getType11() {
        return type11;
    }

    public String getType11Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类,getType11());
    }

    public void setType11(String type11) {
        this.type11 = type11;
    }

    public String getType12() {
        return type12;
    }

    public String getType12Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产五级分类,getType12());
    }

    public void setType12(String type12) {
        this.type12 = type12;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getNetAssetValue() {
        return netAssetValue;
    }

    public void setNetAssetValue(BigDecimal netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
}
