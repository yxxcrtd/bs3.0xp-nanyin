package com.joyintech.tams.invest.product.product.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;

import java.math.BigDecimal;

/**
 * 评价日资产配置情况查询 table
 */
public class FinProductEvaluate001Tab2Dto extends BaseEntity {

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

    private String parentInfo;

    private String hasChildren;

    private boolean leaf;

    public boolean getLeaf() {
        return StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), hasChildren);
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getParentInfo() {
        return parentInfo;
    }

    public void setParentInfo(String parentInfo) {
        this.parentInfo = parentInfo;
    }

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

    public String getFinprodTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用,this.finprodType);
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getSecondType() {
        return secondType;
    }

    public String getSecondTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, secondType);
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getThirdType() {
        return thirdType;
    }

    public String getThirdTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, thirdType);
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public String getFourthType() {
        return fourthType;
    }

    public String getFourthTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, fourthType);
    }

    public void setFourthType(String fourthType) {
        this.fourthType = fourthType;
    }

    public String getFifthType() {
        return fifthType;
    }

    public String getFifthTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产五级分类, fifthType);
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
}
