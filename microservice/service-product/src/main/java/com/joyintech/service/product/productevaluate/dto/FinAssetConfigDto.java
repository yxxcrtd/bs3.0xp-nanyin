package com.joyintech.service.product.productevaluate.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;

/**
 * 评价日资产配置情况
 */
public class FinAssetConfigDto extends BaseEntity implements GridRowDto {

    /**
     * 产品代码
     */
    private String finprodId;
    /**
     * 产品名称
     */
    private String finprodName;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 组合份额
     */
    private BigDecimal portfolioShare;
    /**
     * 资产代码
     */
    private String assetCode;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产大类
     */
    private String finprodType;
    /**
     * 二级分类
     */
    private String secondType;
    /**
     * 三级分类
     */
    private String thirdType;
    /**
     * 四级分类
     */
    private String fourthType;
    /**
     * 五级分类
     */
    private String fifthType;
    /**
     * 持仓数量
     */
    private BigDecimal shareNum;
    /**
     * 资产净值
     */
    private BigDecimal assetNetNum;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

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

    public BigDecimal getPortfolioShare() {
        return portfolioShare;
    }

    public void setPortfolioShare(BigDecimal portfolioShare) {
        this.portfolioShare = portfolioShare;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public String getFourthType() {
        return fourthType;
    }

    public void setFourthType(String fourthType) {
        this.fourthType = fourthType;
    }

    public String getFifthType() {
        return fifthType;
    }

    public void setFifthType(String fifthType) {
        this.fifthType = fifthType;
    }

    public BigDecimal getShareNum() {
        return shareNum;
    }

    public void setShareNum(BigDecimal shareNum) {
        this.shareNum = shareNum;
    }

    public BigDecimal getAssetNetNum() {
        return assetNetNum;
    }

    public void setAssetNetNum(BigDecimal assetNetNum) {
        this.assetNetNum = assetNetNum;
    }

    /**
     * 行ID
     */
    private String rowId;


    /**
     * 是否有子节点
     */
    private String hasChildren;

    /**
     * 名称
     */
    private String name;

    /**
     * 父节点
     */
    private String parentInfo;

    public String getParentInfo() {
        return this.parentInfo;
    }

    public void setParentInfo(String parentInfo) {
       this.parentInfo = parentInfo;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public String getRowId() {
        return this.rowId;
    }

    @Override
    public void setRowId(String s) {
        this.rowId = s;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void setOrder(int i) {

    }

    @Override
    public String getId() {
        return getAssetCode();
    }

    @Override
    public void setId(String s) {
        this.setAssetCode(s);
    }

    @Override
    public String getParentId() {
        return this.getParentInfo();
    }

    @Override
    public void setParentId(String s) {
        this.setParentInfo(s);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public boolean isLeaf() {
        return StringUtil.equals(com.joyintech.framework.constant.DictCodeList.是否_是.getValue(), hasChildren);
    }

    @Override
    public void setLeaf(boolean b) {

    }
}
