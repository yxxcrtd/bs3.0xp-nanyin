/*
 * 文件名：PtlFutureCashPosiDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlfuturecashposi.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlfuturecashposi.validator.PtlFutureCashPosiDtoValidator;

/**
 * 组合未来头寸分析头寸Dto
 *
 * @author renping
 * @version 1.0 2019年11月09日
 * @see PtlFutureCashPosiDto
 * @since 1.0
 */
@ValidLogic(targetClass = PtlFutureCashPosiDtoValidator.class)
public class PtlFutureCashPosiDto extends PtlPortfolio implements GridRowDto {

    /**
     * 组合未来头寸分析头寸
     */
    private static final long serialVersionUID = 1213425647546634432L;

    /**
     * 产品经理
     */
    private String prodManager;

    /**
     * 展示维度
     */
    @NotEmpty(fieldName = "PtlFutureCashPosi001.showDimension")
    private String showDimension;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 组合可用现金
     */
    private BigDecimal usableAmt;

    /**
     * 产品到期
     */
    private BigDecimal prdEndAmt;

    /**
     * 资产存续期（资金流入）
     */
    private BigDecimal assetPeriodInAmt;

    /**
     * 资产存续期（资金流出）
     */
    private BigDecimal assetPeriodOutAmt;

    /**
     * 现金缺口
     */
    private BigDecimal cashGapAmt;

    /**
     * 父节点
     */
    private String upperPortfolioCode;

    /**
     * 子节点
     */
    private String hasChildren;

    public String getUpperPortfolioCode() {
        return upperPortfolioCode;
    }

    public void setUpperPortfolioCode(String upperPortfolioCode) {
        this.upperPortfolioCode = upperPortfolioCode;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public String getRowId() {
        return this.getPortfolioId();
    }

    @Override
    public void setRowId(String rowId) {

    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void setOrder(int order) {

    }

    @Override
    public String getId() {
        return this.getPortfolioId();
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getParentId() {
        return upperPortfolioCode;
    }

    @Override
    public void setParentId(String parentId) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public boolean isLeaf() {
        return StringUtil.equals(hasChildren, DictCodeListExt1.是或否_否.getValue());
    }

    @Override
    public void setLeaf(boolean leaf) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * 获取 prodManager
     * 
     * @return prodManager.
     */
    public String getProdManager() {
        return prodManager;
    }

    /**
     * 设置 prodManager
     * 
     * @param prodManager prodManager
     */
    public void setProdManager(String prodManager) {
        this.prodManager = prodManager;
    }

    /**
     * 获取 showDimension
     * 
     * @return showDimension.
     */
    public String getShowDimension() {
        return showDimension;
    }

    /**
     * 设置 showDimension
     * 
     * @param showDimension showDimension
     */
    public void setShowDimension(String showDimension) {
        this.showDimension = showDimension;
    }

    /**
     * 获取 cdate
     * 
     * @return cdate.
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 cdate
     * 
     * @param cdate cdate
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 usableAmt
     * 
     * @return usableAmt.
     */
    public BigDecimal getUsableAmt() {
        return usableAmt;
    }

    /**
     * 设置 usableAmt
     * 
     * @param usableAmt usableAmt
     */
    public void setUsableAmt(BigDecimal usableAmt) {
        this.usableAmt = usableAmt;
    }

    /**
     * 获取 prdEndAmt
     * 
     * @return prdEndAmt.
     */
    public BigDecimal getPrdEndAmt() {
        return prdEndAmt;
    }

    /**
     * 设置 prdEndAmt
     * 
     * @param prdEndAmt prdEndAmt
     */
    public void setPrdEndAmt(BigDecimal prdEndAmt) {
        this.prdEndAmt = prdEndAmt;
    }

    /**
     * 获取 assetPeriodInAmt
     * 
     * @return assetPeriodInAmt.
     */
    public BigDecimal getAssetPeriodInAmt() {
        return assetPeriodInAmt;
    }

    /**
     * 设置 assetPeriodInAmt
     * 
     * @param assetPeriodInAmt assetPeriodInAmt
     */
    public void setAssetPeriodInAmt(BigDecimal assetPeriodInAmt) {
        this.assetPeriodInAmt = assetPeriodInAmt;
    }

    /**
     * 获取 assetPeriodOutAmt
     * 
     * @return assetPeriodOutAmt.
     */
    public BigDecimal getAssetPeriodOutAmt() {
        return assetPeriodOutAmt;
    }

    /**
     * 设置 assetPeriodOutAmt
     * 
     * @param assetPeriodOutAmt assetPeriodOutAmt
     */
    public void setAssetPeriodOutAmt(BigDecimal assetPeriodOutAmt) {
        this.assetPeriodOutAmt = assetPeriodOutAmt;
    }

    /**
     * 获取 cashGapAmt
     * 
     * @return cashGapAmt.
     */
    public BigDecimal getCashGapAmt() {
        return cashGapAmt;
    }

    /**
     * 设置 cashGapAmt
     * 
     * @param cashGapAmt cashGapAmt
     */
    public void setCashGapAmt(BigDecimal cashGapAmt) {
        this.cashGapAmt = cashGapAmt;
    }

    /**
     * 获取 mDateStr
     * 
     * @return mDateStr.
     */
    public String getmDateStr() {
        return DateUtil.format(super.getMdate());
    }

}
