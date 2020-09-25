
package com.joyintech.tams.invest.portfolio.ptlpositionanalysis.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.tams.common.base.entity.AssetTargetDb;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 组合持仓分析报表
 * 
 * @author nixiukuan
 * @version 1.0 2019年12月05日
 * @see PtlPositionAnalysis003Dto
 * @since 1.0
 */
@PageId("PtlPositionAnalysis002")
public class PtlPositionAnalysis003Dto  extends AssetTargetDb implements GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 8496377411327582052L;

    /**
     * 修正久期扩展
     */
    private  BigDecimal mdurationAmend;

    public BigDecimal getMdurationAmend() {
        return mdurationAmend;
    }

    public void setMdurationAmend(BigDecimal mdurationAmend) {
        this.mdurationAmend = mdurationAmend;
    }

    /**
     * 分类名称
     */
    private  String typeName;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    /**
     * 父节点
     */
    private String parentInfo;

    /**
     * 子节点
     */
    private String childInfo;

    /**
     * 是否有子节点
     */
    private String hasChildren;

    public String getParentInfo() {
        return parentInfo;
    }

    public void setParentInfo(String parentInfo) {
        this.parentInfo = parentInfo;
    }

    public String getChildInfo() {
        return childInfo;
    }

    public void setChildInfo(String childInfo) {
        this.childInfo = childInfo;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public String getRowId() {
        return null;
    }

    @Override
    public void setRowId(String s) {

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
        return null;
    }

    @Override
    public void setId(String s) {

    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public void setParentId(String s) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void setLeaf(boolean b) {

    }

    @Override
    public String getCreateUser() {
        return null;
    }

    @Override
    public void setCreateUser(String s) {

    }

    @Override
    public String getCreateDept() {
        return null;
    }

    @Override
    public void setCreateDept(String s) {

    }


    /**
     * 价格涨跌
     */

    private BigDecimal priceFluctuation;

    /**
     * 价格涨跌幅%
     */

    private BigDecimal priceFluctuationPercentage;

    /**
     * 持仓占发行总额比%
     */
    private BigDecimal positionTotalIssuance;

    /**
     * 持仓占债券资产规模比%
     */

    private BigDecimal positionBondIssuance;

    /**
     * 持仓占流通股本比%
     */

    private BigDecimal cSharesPercentage;

    /**
     * 持仓占总股本比%
     */
    private BigDecimal sSharesPercentage;


    public BigDecimal getPriceFluctuation() {
        return priceFluctuation;
    }


    public void setPriceFluctuation(BigDecimal priceFluctuation) {
        this.priceFluctuation = priceFluctuation;
    }


    public BigDecimal getPriceFluctuationPercentage() {
        return priceFluctuationPercentage;
    }


    public void setPriceFluctuationPercentage(BigDecimal priceFluctuationPercentage) {
        this.priceFluctuationPercentage = priceFluctuationPercentage;
    }


    public BigDecimal getPositionTotalIssuance() {
        return positionTotalIssuance;
    }


    public void setPositionTotalIssuance(BigDecimal positionTotalIssuance) {
        this.positionTotalIssuance = positionTotalIssuance;
    }


    public BigDecimal getPositionBondIssuance() {
        return positionBondIssuance;
    }


    public void setPositionBondIssuance(BigDecimal positionBondIssuance) {
        this.positionBondIssuance = positionBondIssuance;
    }


    public BigDecimal getcSharesPercentage() {
        return cSharesPercentage;
    }


    public void setcSharesPercentage(BigDecimal cSharesPercentage) {
        this.cSharesPercentage = cSharesPercentage;
    }


    public BigDecimal getsSharesPercentage() {
        return sSharesPercentage;
    }


    public void setsSharesPercentage(BigDecimal sSharesPercentage) {
        this.sSharesPercentage = sSharesPercentage;
    }
    private String belongInstitutionNameNew;

    public String getBelongInstitutionNameNew() {
        return belongInstitutionNameNew;
    }

    public void setBelongInstitutionNameNew(String belongInstitutionNameNew) {
        this.belongInstitutionNameNew = belongInstitutionNameNew;
    }
}
