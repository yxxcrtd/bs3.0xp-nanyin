/*
 * 文件名：AssetImpairmentManagement002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-24
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.validator.AssetImpairmentKpmgManagement002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 * @see AssetImpairmentKpmgManagement002Dto
 * @since 1.0
 */
@PageId("AssetImpairmentKpmgManagement002")
@ValidLogic(targetClass = AssetImpairmentKpmgManagement002Validator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class AssetImpairmentKpmgManagement002Dto extends BaseEntity implements ProcessEntity {

    /**
     * 序列号
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    private String seqNo;
    /**
     * 产品代码
     */
    @NotBlank(message = "产品代码不能为空!", groups = {ValidateGroupEdit.class})
    private String prodId;
    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空!", groups = {ValidateGroupEdit.class})
    private String prodName;
    /**
     * 资产代码
     */
    @NotBlank(message = "资产代码不能为空!", groups = {ValidateGroupEdit.class})
    private String assetId;
    /**
     * 资产名称
     */
    @NotBlank(message = "资产名称不能为空!", groups = {ValidateGroupEdit.class})
    private String assetName;
    /**
     * 资产类型
     */
    @NotBlank(message = "资产类型不能为空!", groups = {ValidateGroupEdit.class})
    private String assetType;
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 减值日
     */
    @NotNull(message = "减值日不能为空!", groups = {ValidateGroupEdit.class})
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
    @NotNull(message = "前瞻前PD违约概率%不能为空!", groups = {ValidateGroupEdit.class})
    private BigDecimal preProspectivePd;
    /**
     * 前瞻后PD违约概率%
     */
    @NotNull(message = "前瞻后PD违约概率%不能为空!", groups = {ValidateGroupEdit.class})
    private BigDecimal prospectivePd;
    /**
     * 减值阶段
     */
    @NotBlank(message = "减值阶段不能为空!", groups = {ValidateGroupEdit.class})
    private String stage;
    /**
     * LGD违约损失率%
     */
    @NotNull(message = "LGD违约损失率%不能为空!", groups = {ValidateGroupEdit.class})
    private BigDecimal lgd;
    /**
     * EAD违约风险暴露
     */
    @NotNull(message = "EAD违约风险暴露不能为空!", groups = {ValidateGroupEdit.class})
    private BigDecimal ead;
    /**
     * 前瞻前ECL预期信用损失
     */
    @NotNull(message = "前瞻前ECL预期信用损失不能为空!", groups = {ValidateGroupEdit.class})
    private BigDecimal preProspectiveEcl;
    /**
     * 前瞻后ECL预期信用损失
     */
    @NotNull(message = "前瞻后ECL预期信用损失不能为空!", groups = {ValidateGroupEdit.class})
    private BigDecimal prospectiveEcl;
    /**
     * 是否允许模型覆盖
     */
    @NotBlank(message = "是否允许模型覆盖不能为空!", groups = {ValidateGroupEdit.class})
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


    public Date getDecDate() {
        return decDate;
    }

    @PrimaryKey(value= PrimaryKey.Field.PK_3)
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

    public String getStageFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.减值阶段, stage))?
                SysDataUtil.getNameFromDataDict(DictCode.减值阶段, stage):stage;
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

    public String getDataSourceFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.模型数据来源, dataSource))?
                SysDataUtil.getNameFromDataDict(DictCode.模型数据来源, dataSource):dataSource;
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
