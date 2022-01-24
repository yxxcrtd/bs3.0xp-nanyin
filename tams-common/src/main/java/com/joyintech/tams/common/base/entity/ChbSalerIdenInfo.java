package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbSalerIdenInfo extends BaseEntity {
    /**
     * 内部编号
     */
    private String salerInsNo;

    /**
     * 销售人员登记编码 
     */
    private String salerRegistNo;

    /**
     * 销售机构代码 
     */
    private String salerInstCode;

    /**
     * 姓名 
     */
    private String salerName;

    /**
     * 性别 
     */
    private String salerSex;

    /**
     * 出生日期 
     */
    private Date salerBirthday;

    /**
     * 证件类别 
     */
    private String salerCredentType;

    /**
     * 其他证件名称 
     */
    private String salerOtherCredentName;

    /**
     * 证件号码 
     */
    private String salerCredentNo;

    /**
     * 所属区域 
     */
    private String salerAreaType;

    /**
     * 所属总部或分支机构 
     */
    private String salerOrgCode;

    /**
     * 所属部门 
     */
    private String salerDept;

    /**
     * 职位 
     */
    private String salerPosition;

    /**
     * 学历 
     */
    private String salerEduLevel;

    /**
     * 学位 
     */
    private String salerDegree;

    /**
     * 首次参加工作时间 
     */
    private String salerWorkStartYmd;

    /**
     * 首次从事金融工作时间 
     */
    private String salerFinanceStartYmd;

    /**
     * 首次从事理财销售时间 
     */
    private String salerStartYmd;

    /**
     * 专业技术职称
     */
    private String salerLevle;

    /**
     * 理财专业证书 
     */
    private String salerCertificateType;

    /**
     * 理财登记培训证书编号 
     */
    private String salerChbTrainNo;

    /**
     * 可销售产品风险等级 
     */
    private String salerRiskLevel;

    /**
     * 销售资质 
     */
    private String salerQualification;

    /**
     * 办公电话 
     */
    private String salerTel;

    /**
     * 移动电话 
     */
    private String salerMobile;

    /**
     * 邮箱 
     */
    private String salerMail;

    /**
     * 当前状态 
     */
    private String salerStatus;

    /**
     * 备注 
     */
    private String remark;

    /**
     * 数据日期 
     */
    private Date dataDate;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 取得 内部编号
     * @return 内部编号
     */
    public String getSalerInsNo() {
        return salerInsNo;
    }

    /**
     * 设置 内部编号
     * @param salerInsNo 内部编号
     */
    public void setSalerInsNo(String salerInsNo) {
        this.salerInsNo = salerInsNo == null ? null : salerInsNo.trim();
    }

    /**
     * 取得 销售人员登记编码 
     * @return 销售人员登记编码 
     */
    public String getSalerRegistNo() {
        return salerRegistNo;
    }

    /**
     * 设置 销售人员登记编码 
     * @param salerRegistNo 销售人员登记编码 
     */
    public void setSalerRegistNo(String salerRegistNo) {
        this.salerRegistNo = salerRegistNo == null ? null : salerRegistNo.trim();
    }

    /**
     * 取得 销售机构代码 
     * @return 销售机构代码 
     */
    public String getSalerInstCode() {
        return salerInstCode;
    }

    /**
     * 设置 销售机构代码 
     * @param salerInstCode 销售机构代码 
     */
    public void setSalerInstCode(String salerInstCode) {
        this.salerInstCode = salerInstCode == null ? null : salerInstCode.trim();
    }

    /**
     * 取得 姓名 
     * @return 姓名 
     */
    public String getSalerName() {
        return salerName;
    }

    /**
     * 设置 姓名 
     * @param salerName 姓名 
     */
    public void setSalerName(String salerName) {
        this.salerName = salerName == null ? null : salerName.trim();
    }

    /**
     * 取得 性别 
     * @return 性别 
     */
    public String getSalerSex() {
        return salerSex;
    }

    /**
     * 设置 性别 
     * @param salerSex 性别 
     */
    public void setSalerSex(String salerSex) {
        this.salerSex = salerSex == null ? null : salerSex.trim();
    }

    /**
     * 取得 出生日期 
     * @return 出生日期 
     */
    public Date getSalerBirthday() {
        return salerBirthday;
    }

    /**
     * 设置 出生日期 
     * @param salerBirthday 出生日期 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSalerBirthday(Date salerBirthday) {
        this.salerBirthday = salerBirthday;
    }

    /**
     * 取得 证件类别 
     * @return 证件类别 
     */
    public String getSalerCredentType() {
        return salerCredentType;
    }

    /**
     * 设置 证件类别 
     * @param salerCredentType 证件类别 
     */
    public void setSalerCredentType(String salerCredentType) {
        this.salerCredentType = salerCredentType == null ? null : salerCredentType.trim();
    }

    /**
     * 取得 其他证件名称 
     * @return 其他证件名称 
     */
    public String getSalerOtherCredentName() {
        return salerOtherCredentName;
    }

    /**
     * 设置 其他证件名称 
     * @param salerOtherCredentName 其他证件名称 
     */
    public void setSalerOtherCredentName(String salerOtherCredentName) {
        this.salerOtherCredentName = salerOtherCredentName == null ? null : salerOtherCredentName.trim();
    }

    /**
     * 取得 证件号码 
     * @return 证件号码 
     */
    public String getSalerCredentNo() {
        return salerCredentNo;
    }

    /**
     * 设置 证件号码 
     * @param salerCredentNo 证件号码 
     */
    public void setSalerCredentNo(String salerCredentNo) {
        this.salerCredentNo = salerCredentNo == null ? null : salerCredentNo.trim();
    }

    /**
     * 取得 所属区域 
     * @return 所属区域 
     */
    public String getSalerAreaType() {
        return salerAreaType;
    }

    /**
     * 设置 所属区域 
     * @param salerAreaType 所属区域 
     */
    public void setSalerAreaType(String salerAreaType) {
        this.salerAreaType = salerAreaType == null ? null : salerAreaType.trim();
    }

    /**
     * 取得 所属总部或分支机构 
     * @return 所属总部或分支机构 
     */
    public String getSalerOrgCode() {
        return salerOrgCode;
    }

    /**
     * 设置 所属总部或分支机构 
     * @param salerOrgCode 所属总部或分支机构 
     */
    public void setSalerOrgCode(String salerOrgCode) {
        this.salerOrgCode = salerOrgCode == null ? null : salerOrgCode.trim();
    }

    /**
     * 取得 所属部门 
     * @return 所属部门 
     */
    public String getSalerDept() {
        return salerDept;
    }

    /**
     * 设置 所属部门 
     * @param salerDept 所属部门 
     */
    public void setSalerDept(String salerDept) {
        this.salerDept = salerDept == null ? null : salerDept.trim();
    }

    /**
     * 取得 职位 
     * @return 职位 
     */
    public String getSalerPosition() {
        return salerPosition;
    }

    /**
     * 设置 职位 
     * @param salerPosition 职位 
     */
    public void setSalerPosition(String salerPosition) {
        this.salerPosition = salerPosition == null ? null : salerPosition.trim();
    }

    /**
     * 取得 学历 
     * @return 学历 
     */
    public String getSalerEduLevel() {
        return salerEduLevel;
    }

    /**
     * 设置 学历 
     * @param salerEduLevel 学历 
     */
    public void setSalerEduLevel(String salerEduLevel) {
        this.salerEduLevel = salerEduLevel == null ? null : salerEduLevel.trim();
    }

    /**
     * 取得 学位 
     * @return 学位 
     */
    public String getSalerDegree() {
        return salerDegree;
    }

    /**
     * 设置 学位 
     * @param salerDegree 学位 
     */
    public void setSalerDegree(String salerDegree) {
        this.salerDegree = salerDegree == null ? null : salerDegree.trim();
    }

    /**
     * 取得 首次参加工作时间 
     * @return 首次参加工作时间 
     */
    public String getSalerWorkStartYmd() {
        return salerWorkStartYmd;
    }

    /**
     * 设置 首次参加工作时间 
     * @param salerWorkStartYmd 首次参加工作时间 
     */
    public void setSalerWorkStartYmd(String salerWorkStartYmd) {
        this.salerWorkStartYmd = salerWorkStartYmd == null ? null : salerWorkStartYmd.trim();
    }

    /**
     * 取得 首次从事金融工作时间 
     * @return 首次从事金融工作时间 
     */
    public String getSalerFinanceStartYmd() {
        return salerFinanceStartYmd;
    }

    /**
     * 设置 首次从事金融工作时间 
     * @param salerFinanceStartYmd 首次从事金融工作时间 
     */
    public void setSalerFinanceStartYmd(String salerFinanceStartYmd) {
        this.salerFinanceStartYmd = salerFinanceStartYmd == null ? null : salerFinanceStartYmd.trim();
    }

    /**
     * 取得 首次从事理财销售时间 
     * @return 首次从事理财销售时间 
     */
    public String getSalerStartYmd() {
        return salerStartYmd;
    }

    /**
     * 设置 首次从事理财销售时间 
     * @param salerStartYmd 首次从事理财销售时间 
     */
    public void setSalerStartYmd(String salerStartYmd) {
        this.salerStartYmd = salerStartYmd == null ? null : salerStartYmd.trim();
    }

    /**
     * 取得 专业技术职称
     * @return 专业技术职称
     */
    public String getSalerLevle() {
        return salerLevle;
    }

    /**
     * 设置 专业技术职称
     * @param salerLevle 专业技术职称
     */
    public void setSalerLevle(String salerLevle) {
        this.salerLevle = salerLevle == null ? null : salerLevle.trim();
    }

    /**
     * 取得 理财专业证书 
     * @return 理财专业证书 
     */
    public String getSalerCertificateType() {
        return salerCertificateType;
    }

    /**
     * 设置 理财专业证书 
     * @param salerCertificateType 理财专业证书 
     */
    public void setSalerCertificateType(String salerCertificateType) {
        this.salerCertificateType = salerCertificateType == null ? null : salerCertificateType.trim();
    }

    /**
     * 取得 理财登记培训证书编号 
     * @return 理财登记培训证书编号 
     */
    public String getSalerChbTrainNo() {
        return salerChbTrainNo;
    }

    /**
     * 设置 理财登记培训证书编号 
     * @param salerChbTrainNo 理财登记培训证书编号 
     */
    public void setSalerChbTrainNo(String salerChbTrainNo) {
        this.salerChbTrainNo = salerChbTrainNo == null ? null : salerChbTrainNo.trim();
    }

    /**
     * 取得 可销售产品风险等级 
     * @return 可销售产品风险等级 
     */
    public String getSalerRiskLevel() {
        return salerRiskLevel;
    }

    /**
     * 设置 可销售产品风险等级 
     * @param salerRiskLevel 可销售产品风险等级 
     */
    public void setSalerRiskLevel(String salerRiskLevel) {
        this.salerRiskLevel = salerRiskLevel == null ? null : salerRiskLevel.trim();
    }

    /**
     * 取得 销售资质 
     * @return 销售资质 
     */
    public String getSalerQualification() {
        return salerQualification;
    }

    /**
     * 设置 销售资质 
     * @param salerQualification 销售资质 
     */
    public void setSalerQualification(String salerQualification) {
        this.salerQualification = salerQualification == null ? null : salerQualification.trim();
    }

    /**
     * 取得 办公电话 
     * @return 办公电话 
     */
    public String getSalerTel() {
        return salerTel;
    }

    /**
     * 设置 办公电话 
     * @param salerTel 办公电话 
     */
    public void setSalerTel(String salerTel) {
        this.salerTel = salerTel == null ? null : salerTel.trim();
    }

    /**
     * 取得 移动电话 
     * @return 移动电话 
     */
    public String getSalerMobile() {
        return salerMobile;
    }

    /**
     * 设置 移动电话 
     * @param salerMobile 移动电话 
     */
    public void setSalerMobile(String salerMobile) {
        this.salerMobile = salerMobile == null ? null : salerMobile.trim();
    }

    /**
     * 取得 邮箱 
     * @return 邮箱 
     */
    public String getSalerMail() {
        return salerMail;
    }

    /**
     * 设置 邮箱 
     * @param salerMail 邮箱 
     */
    public void setSalerMail(String salerMail) {
        this.salerMail = salerMail == null ? null : salerMail.trim();
    }

    /**
     * 取得 当前状态 
     * @return 当前状态 
     */
    public String getSalerStatus() {
        return salerStatus;
    }

    /**
     * 设置 当前状态 
     * @param salerStatus 当前状态 
     */
    public void setSalerStatus(String salerStatus) {
        this.salerStatus = salerStatus == null ? null : salerStatus.trim();
    }

    /**
     * 取得 备注 
     * @return 备注 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注 
     * @param remark 备注 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 数据日期 
     * @return 数据日期 
     */
    public Date getDataDate() {
        return dataDate;
    }

    /**
     * 设置 数据日期 
     * @param dataDate 数据日期 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}