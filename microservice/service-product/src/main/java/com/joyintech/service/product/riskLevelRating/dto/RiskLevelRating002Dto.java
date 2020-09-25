package com.joyintech.service.product.riskLevelRating.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: RiskLevelRating001Dto
 * @description: 产品风险等级评定新建实体类
 * @author: jyl
 * @create: 2020-01-03 16:44
 **/
public class RiskLevelRating002Dto extends BaseEntity {
    /**
     * 产品代码（市场代码）
     */
    private String finProductCode;
    /**
     * 产品简称
     */
    private String finprodAbbr;
    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 分支序号
     */
    private BigDecimal branch;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 资产代码
     */
    private String finprodMarketId;
    /**
     * 资产名称
     */
    private String finprodName;
    /**
     * 资产一级分类
     */
    private String pinanAssetType;
    /**
     * 资产二级分类
     */
    private String customAssetTypeTwo;
    /**
     * 资产三级分类
     */
    private String customAssetTypeThree;
    /**
     * 资产四级分类
     */
    private String customAssetTypeFour;
    /**
     * 资产五级分类
     */
    private String customAssetTypeFive;
    /**
     * 调整后风险等级
     */
    private String ajustRiskLevel;
    /**
     * 是否存续
     */
    private String isLast;
    /**
     * 资产评定列表
     */
    private List<RiskLevelRating002Dto> riskList;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     *业务类型
     */
    private String busiType;
    /**
     *集中度
     */
    private BigDecimal crn;

    /**
     *资产风险等级
     */
    private String assetRiskType;

    public String getFinProductCode() {
        return finProductCode;
    }

    public void setFinProductCode(String finProductCode) {
        this.finProductCode = finProductCode;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public BigDecimal getBranch() {
        return branch;
    }

    public void setBranch(BigDecimal branch) {
        this.branch = branch;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getPinanAssetType() {
        return pinanAssetType;
    }

    public void setPinanAssetType(String pinanAssetType) {
        this.pinanAssetType = pinanAssetType;
    }

    public String getCustomAssetTypeTwo() {
        return customAssetTypeTwo;
    }

    public void setCustomAssetTypeTwo(String customAssetTypeTwo) {
        this.customAssetTypeTwo = customAssetTypeTwo;
    }

    public String getCustomAssetTypeThree() {
        return customAssetTypeThree;
    }

    public void setCustomAssetTypeThree(String customAssetTypeThree) {
        this.customAssetTypeThree = customAssetTypeThree;
    }

    public String getCustomAssetTypeFour() {
        return customAssetTypeFour;
    }

    public void setCustomAssetTypeFour(String customAssetTypeFour) {
        this.customAssetTypeFour = customAssetTypeFour;
    }

    public String getCustomAssetTypeFive() {
        return customAssetTypeFive;
    }

    public void setCustomAssetTypeFive(String customAssetTypeFive) {
        this.customAssetTypeFive = customAssetTypeFive;
    }

    public String getAjustRiskLevel() {
        return ajustRiskLevel;
    }

    public void setAjustRiskLevel(String ajustRiskLevel) {
        this.ajustRiskLevel = ajustRiskLevel;
    }

    public String getIsLast() {
        return isLast;
    }

    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }

    public List<RiskLevelRating002Dto> getRiskList() {
        return riskList;
    }

    public void setRiskList(List<RiskLevelRating002Dto> riskList) {
        this.riskList = riskList;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public BigDecimal getCrn() {
        return crn;
    }

    public void setCrn(BigDecimal crn) {
        this.crn = crn;
    }

    public String getAssetRiskType() {
        return assetRiskType;
    }

    public void setAssetRiskType(String assetRiskType) {
        this.assetRiskType = assetRiskType;
    }
}
