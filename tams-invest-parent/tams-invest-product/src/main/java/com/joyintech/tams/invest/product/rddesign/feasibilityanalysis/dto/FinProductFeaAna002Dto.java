package com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.validator.FinProductFeaAna002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

@PageId("FinProductFeaAna002")
@ValidLogic(targetClass=FinProductFeaAna002DtoValidator.class)
public class FinProductFeaAna002Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = -8987646765873962748L;

    /**
     * 附件上传
     */
    private List<FileCommonDto> fileArray;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 确认模式
     */
    private String confirmFlag;

    /**
     * 按钮Id
     */
    private String elementId;

    /**
     * 产品需求编号
     */
    private String requirementId;
    /**
     * 产品形成依据
     */
    private String requirementBasis;
    /**
     * 需求发起时间
     */
    private Date requirementDate;
    /**
     * 目标客户
     */
    private String tarCustomer;
    /**
     * 规模
     */
    private String prdScale;
    /**
     * 期限
     */
    private String prdPeriod;
    /**
     * 收益率
     */
    private String prdRate;
    /**
     * 产品详细需求
     */
    private String prdRequirementDetial;
    /**
     * 需求发起端
     */
    private String requirementSource;
    /**
     * 可行性状态(分析结论)
     */
    private String feasibilityStatus;
    /**
     * 可行需求分类
     */
    private String feasibilityType;
    /**
     * 有效状态
     */
    private String effectStatus;

    /**
     * 可行性分析结论
     */
    private String feasibilityResult;

    public List<FileCommonDto> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<FileCommonDto> fileArray) {
        this.fileArray = fileArray;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getRequirementBasis() {
        return requirementBasis;
    }

    public void setRequirementBasis(String requirementBasis) {
        this.requirementBasis = requirementBasis;
    }

    public Date getRequirementDate() {
        return requirementDate;
    }

    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRequirementDate(Date requirementDate) {
        this.requirementDate = requirementDate;
    }

    public String getTarCustomer() {
        return tarCustomer;
    }

    public void setTarCustomer(String tarCustomer) {
        this.tarCustomer = tarCustomer;
    }

    public String getPrdScale() {
        return prdScale;
    }

    public void setPrdScale(String prdScale) {
        this.prdScale = prdScale;
    }

    public String getPrdPeriod() {
        return prdPeriod;
    }

    public void setPrdPeriod(String prdPeriod) {
        this.prdPeriod = prdPeriod;
    }

    public String getPrdRate() {
        return prdRate;
    }

    public void setPrdRate(String prdRate) {
        this.prdRate = prdRate;
    }

    public String getPrdRequirementDetial() {
        return prdRequirementDetial;
    }

    public void setPrdRequirementDetial(String prdRequirementDetial) {
        this.prdRequirementDetial = prdRequirementDetial;
    }

    public String getRequirementSource() {
        return requirementSource;
    }

    public void setRequirementSource(String requirementSource) {
        this.requirementSource = requirementSource;
    }

    public String getFeasibilityStatus() {
        return feasibilityStatus;
    }

    public void setFeasibilityStatus(String feasibilityStatus) {
        this.feasibilityStatus = feasibilityStatus;
    }

    public String getFeasibilityType() {
        return feasibilityType;
    }

    public void setFeasibilityType(String feasibilityType) {
        this.feasibilityType = feasibilityType;
    }

    public String getEffectStatus() {
        return effectStatus;
    }

    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    public String getFeasibilityResult() {
        return feasibilityResult;
    }

    public void setFeasibilityResult(String feasibilityResult) {
        this.feasibilityResult = feasibilityResult;
    }

    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }
}
