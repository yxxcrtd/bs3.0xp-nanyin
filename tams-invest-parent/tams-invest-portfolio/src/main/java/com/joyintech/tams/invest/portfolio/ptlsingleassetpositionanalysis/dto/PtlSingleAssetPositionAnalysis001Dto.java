/*
 * 文件名：PtlSingleAssetPositionAnalysis001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsingleassetpositionanalysis.dto;

import java.math.BigDecimal;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.BaseDto;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;

/**
 * 单一资产持仓分析Dto
 * 
 * @author wanghb
 * @version 1.0 2019年1月23日
 * @see PtlSingleAssetPositionAnalysis001Dto
 * @since 1.0
 */
@PageId("PtlSingleAssetPositionAnalysis001")
public class PtlSingleAssetPositionAnalysis001Dto extends BaseDto implements GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 8496377411327582051L;

    /**
     * 资产名称
     */
    private String finprodAbbr;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 组合名称
     */
    private String portFolioName;

    /**
     * 市值(元)
     */
    private BigDecimal marketValue;

    /**
     * 成本(元)
     */
    private BigDecimal dscCostAmt;

    /**
     * 持仓数量
     */
    private BigDecimal shareAmt;

    /**
     * 市值占比
     */
    private BigDecimal marketValueRatio;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 是否有子节点
     */
    private String hasChildren;

    /**
     * 行ID
     */
    private String rowId;

    /**
     * 名称
     */
    private String name;
    /**
     * 父节点
     */
    private String parentInfo;
    /**
     * 子节点
     */
    private String childInfo;
    /**
     * 获取 finprodAbbr
     * 
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * 
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 portFolioName
     * 
     * @return portFolioName.
     */
    public String getPortFolioName() {
        return portFolioName;
    }

    /**
     * 设置 portFolioName
     * 
     * @param portFolioName portFolioName
     */
    public void setPortFolioName(String portFolioName) {
        this.portFolioName = portFolioName;
    }

    /**
     * 获取 marketValue
     * 
     * @return marketValue.
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 marketValue
     * 
     * @param marketValue marketValue
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 获取 dscCostAmt
     * 
     * @return dscCostAmt.
     */
    public BigDecimal getDscCostAmt() {
        return dscCostAmt;
    }

    /**
     * 设置 dscCostAmt
     * 
     * @param dscCostAmt dscCostAmt
     */
    public void setDscCostAmt(BigDecimal dscCostAmt) {
        this.dscCostAmt = dscCostAmt;
    }

    /**
     * 获取 shareAmt
     * 
     * @return shareAmt.
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 shareAmt
     * 
     * @param shareAmt shareAmt
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 获取 marketValueRatio
     * 
     * @return marketValueRatio.
     */
    public BigDecimal getMarketValueRatio() {
        return marketValueRatio;
    }

    /**
     * 设置 marketValueRatio
     * 
     * @param marketValueRatio marketValueRatio
     */
    public void setMarketValueRatio(BigDecimal marketValueRatio) {
        this.marketValueRatio = marketValueRatio;
    }

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * 
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 hasChildren
     * 
     * @return hasChildren.
     */
    public String getHasChildren() {
        return hasChildren;
    }

    /**
     * 设置 hasChildren
     * 
     * @param hasChildren hasChildren
     */
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreateUser() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCreateUser(String createUser) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreateDept() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCreateDept(String createDept) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRowId() {
        // TODO Auto-generated method stub
        return rowId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOrder(int order) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return getPortFolioName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.setPortFolioName(id);
    }

    /**
     * 获取 parentId
     * 
     * @return parentId.
     */
    public String getParentId() {
        return this.getParentInfo();
    }

    /**
     * 设置 parentId
     * 
     * @param parentId parentId
     */
    public void setParentId(String parentId) {
        this.setParentInfo(parentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {
        return StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), hasChildren);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLeaf(boolean leaf) {
    }

    
    /**
     * 获取 parentInfo
     * @return parentInfo.
     */
    public String getParentInfo() {
        return parentInfo;
    }

    
    /**
     * 设置 parentInfo
     * @param parentInfo parentInfo
     */
    public void setParentInfo(String parentInfo) {
        this.parentInfo = parentInfo;
    }

    
    /**
     * 获取 childInfo
     * @return childInfo.
     */
    public String getChildInfo() {
        return childInfo;
    }

    
    /**
     * 设置 childInfo
     * @param childInfo childInfo
     */
    public void setChildInfo(String childInfo) {
        this.childInfo = childInfo;
    }
    private String belongInstitutionCodeNew;

    public String getBelongInstitutionCodeNew() {
        return belongInstitutionCodeNew;
    }

    public void setBelongInstitutionCodeNew(String belongInstitutionCodeNew) {
        this.belongInstitutionCodeNew = belongInstitutionCodeNew;
    }

    public String getBelongInstitutionNameNew() {
        return StringUtils.isNotEmpty(this.belongInstitutionCodeNew) ? String.valueOf(CacheUtil.getAppCacheInstitutionCodeName(this.belongInstitutionCodeNew)) : this.belongInstitutionCodeNew;
    }
}
