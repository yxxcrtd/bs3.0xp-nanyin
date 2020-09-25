/*
 * 文件名：PtlPositionAnalysis001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlpositionanalysis.dto;

import java.math.BigDecimal;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.BaseDto;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;

/**
 * 组合持仓分析
 * 
 * @author wanghb
 * @version 1.0 2019年1月25日
 * @see PtlPositionAnalysis001Dto
 * @since 1.0
 */
@PageId("PtlPositionAnalysis001")
public class PtlPositionAnalysis001Dto extends BaseDto implements GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 8496377411327582052L;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 组合名称
     */
    private String otherPortfolioName;

    /**
     * 资产类型
     */
    private String finprodType2;

    /**
     * 资产类型
     */
    private String otherFinprodType2;

    /**
     * 资产二级类型
     */
    private String type5;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产名称
     */
    private String finprodAbbr;

    /**
     * 市值
     */
    private BigDecimal marketValue;

    /**
     * 成本
     */
    private BigDecimal dscCostAmt;

    /**
     * 数量
     */
    private BigDecimal shareAmt;

    /**
     * 应计利息
     */
    private BigDecimal tdyIntincexp;

    /**
     * 当日规模占比
     */
    private BigDecimal tdyAmtRation;

    /**
     * 较上一日变化
     */
    private BigDecimal ystAmtRation;

    /**
     * 较本月初变化
     */
    private BigDecimal monthAmtRation;

    /**
     * 脚本年初变化
     */
    private BigDecimal yearAmtRation;

    /**
     * 规模占比
     */
    private BigDecimal amtRation;

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

    /**
     * 名称
     */
    private String name;

    /**
     * 行
     */
    private String rowId;

    /**
     * 获取 otherPortfolioName
     * 
     * @return otherPortfolioName.
     */
    public String getOtherPortfolioName() {
        return otherPortfolioName;
    }

    /**
     * 设置 otherPortfolioName
     * 
     * @param otherPortfolioName otherPortfolioName
     */
    public void setOtherPortfolioName(String otherPortfolioName) {
        this.otherPortfolioName = otherPortfolioName;
    }

    /**
     * 获取 parentInfo
     * 
     * @return parentInfo.
     */
    public String getParentInfo() {
        return parentInfo;
    }

    /**
     * 设置 parentInfo
     * 
     * @param parentInfo parentInfo
     */
    public void setParentInfo(String parentInfo) {
        this.parentInfo = parentInfo;
    }

    /**
     * 获取 childInfo
     * 
     * @return childInfo.
     */
    public String getChildInfo() {
        return childInfo;
    }

    /**
     * 设置 childInfo
     * 
     * @param childInfo childInfo
     */
    public void setChildInfo(String childInfo) {
        this.childInfo = childInfo;
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
     * 获取 rowId
     * 
     * @return rowId.
     */
    public String getRowId() {
        return rowId;
    }

    /**
     * 设置 rowId
     * 
     * @param rowId rowId
     */
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
        return this.getChildInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.setChildInfo(id);
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
     * 获取 name
     * 
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 name
     * 
     * @param name name
     */
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
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 finprodType2
     * 
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 finprodType2
     * 
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    /**
     * 获取 type5
     * 
     * @return type5.
     */
    public String getType5() {
        return type5;
    }

    /**
     * 设置 type5
     * 
     * @param type5 type5
     */
    public void setType5(String type5) {
        this.type5 = type5;
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
     * 获取 tdyIntincexp
     * 
     * @return tdyIntincexp.
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 设置 tdyIntincexp
     * 
     * @param tdyIntincexp tdyIntincexp
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    /**
     * 获取 tdyAmtRation
     * 
     * @return tdyAmtRation.
     */
    public BigDecimal getTdyAmtRation() {
        return tdyAmtRation;
    }

    /**
     * 设置 tdyAmtRation
     * 
     * @param tdyAmtRation tdyAmtRation
     */
    public void setTdyAmtRation(BigDecimal tdyAmtRation) {
        this.tdyAmtRation = tdyAmtRation;
    }

    /**
     * 获取 ystAmtRation
     * 
     * @return ystAmtRation.
     */
    public BigDecimal getYstAmtRation() {
        return ystAmtRation;
    }

    /**
     * 设置 ystAmtRation
     * 
     * @param ystAmtRation ystAmtRation
     */
    public void setYstAmtRation(BigDecimal ystAmtRation) {
        this.ystAmtRation = ystAmtRation;
    }

    /**
     * 获取 monthAmtRation
     * 
     * @return monthAmtRation.
     */
    public BigDecimal getMonthAmtRation() {
        return monthAmtRation;
    }

    /**
     * 设置 monthAmtRation
     * 
     * @param monthAmtRation monthAmtRation
     */
    public void setMonthAmtRation(BigDecimal monthAmtRation) {
        this.monthAmtRation = monthAmtRation;
    }

    /**
     * 获取 yearAmtRation
     * 
     * @return yearAmtRation.
     */
    public BigDecimal getYearAmtRation() {
        return yearAmtRation;
    }

    /**
     * 设置 yearAmtRation
     * 
     * @param yearAmtRation yearAmtRation
     */
    public void setYearAmtRation(BigDecimal yearAmtRation) {
        this.yearAmtRation = yearAmtRation;
    }

    /**
     * 获取 amtRation
     * 
     * @return amtRation.
     */
    public BigDecimal getAmtRation() {
        return amtRation;
    }

    /**
     * 设置 amtRation
     * 
     * @param amtRation amtRation
     */
    public void setAmtRation(BigDecimal amtRation) {
        this.amtRation = amtRation;
    }

    /**
     * 获取 finprodType2Name
     * 
     * @return finprodType2Name.
     */
    public String getFinprodType2Name() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,
            this.getFinprodType2())) ? SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,
                this.getFinprodType2()) : this.getFinprodType2();
    }
    /**
     * 获取 finprodType2Name
     * 
     * @return finprodType2Name.
     */
    public String getOtherFinprodType2Name() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,
            this.getOtherFinprodType2())) ? SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,
                this.getOtherFinprodType2()) : this.getOtherFinprodType2();
    }

    /**
     * 获取 type5Name
     * 
     * @return type5Name.
     */
    public String getType5Name() {

        if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
            this.getOtherFinprodType2())) {
            return StringUtil.isNotEmpty(
                SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等,
                    this.getType5())) ? SysDataUtil.getNameFromDataDict(
                        DictCode.WIND一级分类_含债券一级分类等, this.getType5()) : this.getType5();
        }

        else if (StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(),
            this.getOtherFinprodType2())) {
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向,
                this.getType5())) ? SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向,
                    this.getType5()) : this.getType5();
        }
        else if (StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(),
                this.getOtherFinprodType2())) {
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向,
                    this.getType5())) ? SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向,
                    this.getType5()) : this.getType5();
        }
        else if (StringUtil.equals(DictCodeList.平安资产类型_基金.getValue(),
            this.getOtherFinprodType2())) {
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型,
                this.getType5())) ? SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型,
                    this.getType5()) : this.getType5();
        }
        else if (StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(),
            this.getOtherFinprodType2())) {
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型,
                this.getType5())) ? SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型,
                    this.getType5()) : this.getType5();
        }
        else if (StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(),
            this.getOtherFinprodType2())) {
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.拆借类型,
                this.getType5())) ? SysDataUtil.getNameFromDataDict(DictCode.拆借类型,
                    this.getType5()) : this.getType5();
        }
        else if (StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(),
            this.getOtherFinprodType2())
                 || StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(),
                     this.getOtherFinprodType2())
                 || StringUtil.equals(DictCodeList.平安资产类型_计划类资产_货币型.getValue(),
                     this.getOtherFinprodType2())
        ) {
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(
                DictCode.计划类资产一级分类, this.getType5())) ? SysDataUtil.getNameFromDataDict(
                    DictCode.计划类资产一级分类, this.getType5()) : this.getType5();
        }
        else if (StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(),
            this.getOtherFinprodType2())) {
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类,
                this.getType5())) ? SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类,
                    this.getType5()) : this.getType5();
        }
        else {
            return null;
        }
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
     * 获取 otherFinprodType2
     * 
     * @return otherFinprodType2.
     */
    public String getOtherFinprodType2() {
        return otherFinprodType2;
    }

    /**
     * 设置 otherFinprodType2
     * 
     * @param otherFinprodType2 otherFinprodType2
     */
    public void setOtherFinprodType2(String otherFinprodType2) {
        this.otherFinprodType2 = otherFinprodType2;
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
