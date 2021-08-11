/*
 * 文件名：AssetImpairmentManagement002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-24
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 * @see AssetImpairmentManagement002Dto
 * @since 1.0
 */
public class AssetImpairmentManagement002Dto extends BaseEntity implements ProcessEntity {

    /**
     * 序列号
     */
    private String seqNo;
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 资产代码
     */
    private String assetId;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产类型
     */
    private String assetType;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 业务编号
     */
    private String bussNo;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 减值日
     */
    private Date decDate;
    /**
     * 本金逾期天数
     */
    private BigDecimal prinOverdueDay;
    /**
     * 利息逾期天数
     */
    private BigDecimal intOverdueDay;
    /**
     * 买入时点评级
     */
    private String purchaseTimeGrade;
    /**
     * 当前时点评级
     */
    private String currentTimeGrade;
    /**
     * 前瞻前PD违约概率%
     */
    private BigDecimal preProspectivePd;
    /**
     * 前瞻后PD违约概率%
     */
    private BigDecimal prospectivePd;
    /**
     * 减值阶段
     */
    private String stage;
    /**
     * LGD违约损失率%
     */
    private BigDecimal lgd;
    /**
     * EAD违约风险暴露
     */
    private BigDecimal ead;
    /**
     * 前瞻前ECL预期信用损失
     */
    private BigDecimal preProspectiveEcl;
    /**
     * 前瞻后ECL预期信用损失
     */
    private BigDecimal prospectiveEcl;
    /**
     * 是否允许模型覆盖
     */
    private String allowModelCover;
    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 界面类型
     */
    private String pageModel;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
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

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getBussNo() {
        return bussNo;
    }

    public void setBussNo(String bussNo) {
        this.bussNo = bussNo;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public String getTradeDateFmt(){
        if(StringUtil.isNotEmpty(tradeDate)){
            return DateUtil.format(tradeDate,"yyyy-MM-dd");
        }
        return "";
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getDecDate() {
        return decDate;
    }

    public String getDecDateFmt(){
        if(StringUtil.isNotEmpty(decDate)){
            return DateUtil.format(decDate,"yyyy-MM-dd");
        }
        return "";
    }

    public void setDecDate(Date decDate) {
        this.decDate = decDate;
    }

    public BigDecimal getPrinOverdueDay() {
        return prinOverdueDay;
    }

    public void setPrinOverdueDay(BigDecimal prinOverdueDay) {
        this.prinOverdueDay = prinOverdueDay;
    }

    public BigDecimal getIntOverdueDay() {
        return intOverdueDay;
    }

    public void setIntOverdueDay(BigDecimal intOverdueDay) {
        this.intOverdueDay = intOverdueDay;
    }

    public String getPurchaseTimeGrade() {
        return purchaseTimeGrade;
    }

    public void setPurchaseTimeGrade(String purchaseTimeGrade) {
        this.purchaseTimeGrade = purchaseTimeGrade;
    }

    public String getCurrentTimeGrade() {
        return currentTimeGrade;
    }

    public void setCurrentTimeGrade(String currentTimeGrade) {
        this.currentTimeGrade = currentTimeGrade;
    }

    public BigDecimal getPreProspectivePd() {
        return preProspectivePd;
    }

    public void setPreProspectivePd(BigDecimal preProspectivePd) {
        this.preProspectivePd = preProspectivePd;
    }

    public BigDecimal getProspectivePd() {
        return prospectivePd;
    }

    public void setProspectivePd(BigDecimal prospectivePd) {
        this.prospectivePd = prospectivePd;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public BigDecimal getLgd() {
        return lgd;
    }

    public void setLgd(BigDecimal lgd) {
        this.lgd = lgd;
    }

    public BigDecimal getEad() {
        return ead;
    }

    public void setEad(BigDecimal ead) {
        this.ead = ead;
    }

    public BigDecimal getPreProspectiveEcl() {
        return preProspectiveEcl;
    }

    public void setPreProspectiveEcl(BigDecimal preProspectiveEcl) {
        this.preProspectiveEcl = preProspectiveEcl;
    }

    public BigDecimal getProspectiveEcl() {
        return prospectiveEcl;
    }

    public void setProspectiveEcl(BigDecimal prospectiveEcl) {
        this.prospectiveEcl = prospectiveEcl;
    }

    public String getAllowModelCover() {
        return allowModelCover;
    }

    public void setAllowModelCover(String allowModelCover) {
        this.allowModelCover = allowModelCover;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
}
