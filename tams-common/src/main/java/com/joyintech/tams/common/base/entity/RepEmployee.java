package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepEmployee extends BaseEntity {
    /**
     * 从业人员登记编码
     */
    private String repEmployeeSystemCode;

    /**
     * 姓名
     */
    private String repName;

    /**
     * 性别
     */
    private String repSex;

    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 身份证号
     */
    private String repCreditId;

    /**
     * 所属总行或分支行
     */
    private String repEmployeeBank;

    /**
     * 所属区域
     */
    private String repRegion;

    /**
     * 具体单位名称
     */
    private String repCompanyName;

    /**
     * 所属部门
     */
    private String repDepartmentName;

    /**
     * 职位
     */
    private String repPost;

    /**
     * 学历
     */
    private String repEducation;

    /**
     * 学位
     */
    private String repDegree;

    /**
     * 首次参加工作时间

     */
    private Integer repFirstWorkTime;

    /**
     * 首次从事理财业务时间

     */
    private Integer repFirstFinancingTime;

    /**
     * 专业技术职称
     */
    private String repProTechTitle;

    /**
     * 理财专业证书
     */
    private String repFinancingCertificate;

    /**
     * 所获奖励
     */
    private String repReword;

    /**
     * 办公电话
     */
    private String repTelephone;

    /**
     * 移动电话
     */
    private String repMobilephone;

    /**
     * 电子邮箱
     */
    private String repEmail;

    /**
     * 登记日期
     */
    private Date repRegDate;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 取得 从业人员登记编码
     * @return 从业人员登记编码
     */
    public String getRepEmployeeSystemCode() {
        return repEmployeeSystemCode;
    }

    /**
     * 设置 从业人员登记编码
     * @param repEmployeeSystemCode 从业人员登记编码
     */
    public void setRepEmployeeSystemCode(String repEmployeeSystemCode) {
        this.repEmployeeSystemCode = repEmployeeSystemCode == null ? null : repEmployeeSystemCode.trim();
    }

    /**
     * 取得 姓名
     * @return 姓名
     */
    public String getRepName() {
        return repName;
    }

    /**
     * 设置 姓名
     * @param repName 姓名
     */
    public void setRepName(String repName) {
        this.repName = repName == null ? null : repName.trim();
    }

    /**
     * 取得 性别
     * @return 性别
     */
    public String getRepSex() {
        return repSex;
    }

    /**
     * 设置 性别
     * @param repSex 性别
     */
    public void setRepSex(String repSex) {
        this.repSex = repSex == null ? null : repSex.trim();
    }

    /**
     * 取得 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @return 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    /**
     * 设置 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @param repIssuePartyId 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId == null ? null : repIssuePartyId.trim();
    }

    /**
     * 取得 身份证号
     * @return 身份证号
     */
    public String getRepCreditId() {
        return repCreditId;
    }

    /**
     * 设置 身份证号
     * @param repCreditId 身份证号
     */
    public void setRepCreditId(String repCreditId) {
        this.repCreditId = repCreditId == null ? null : repCreditId.trim();
    }

    /**
     * 取得 所属总行或分支行
     * @return 所属总行或分支行
     */
    public String getRepEmployeeBank() {
        return repEmployeeBank;
    }

    /**
     * 设置 所属总行或分支行
     * @param repEmployeeBank 所属总行或分支行
     */
    public void setRepEmployeeBank(String repEmployeeBank) {
        this.repEmployeeBank = repEmployeeBank == null ? null : repEmployeeBank.trim();
    }

    /**
     * 取得 所属区域
     * @return 所属区域
     */
    public String getRepRegion() {
        return repRegion;
    }

    /**
     * 设置 所属区域
     * @param repRegion 所属区域
     */
    public void setRepRegion(String repRegion) {
        this.repRegion = repRegion == null ? null : repRegion.trim();
    }

    /**
     * 取得 具体单位名称
     * @return 具体单位名称
     */
    public String getRepCompanyName() {
        return repCompanyName;
    }

    /**
     * 设置 具体单位名称
     * @param repCompanyName 具体单位名称
     */
    public void setRepCompanyName(String repCompanyName) {
        this.repCompanyName = repCompanyName == null ? null : repCompanyName.trim();
    }

    /**
     * 取得 所属部门
     * @return 所属部门
     */
    public String getRepDepartmentName() {
        return repDepartmentName;
    }

    /**
     * 设置 所属部门
     * @param repDepartmentName 所属部门
     */
    public void setRepDepartmentName(String repDepartmentName) {
        this.repDepartmentName = repDepartmentName == null ? null : repDepartmentName.trim();
    }

    /**
     * 取得 职位
     * @return 职位
     */
    public String getRepPost() {
        return repPost;
    }

    /**
     * 设置 职位
     * @param repPost 职位
     */
    public void setRepPost(String repPost) {
        this.repPost = repPost == null ? null : repPost.trim();
    }

    /**
     * 取得 学历
     * @return 学历
     */
    public String getRepEducation() {
        return repEducation;
    }

    /**
     * 设置 学历
     * @param repEducation 学历
     */
    public void setRepEducation(String repEducation) {
        this.repEducation = repEducation == null ? null : repEducation.trim();
    }

    /**
     * 取得 学位
     * @return 学位
     */
    public String getRepDegree() {
        return repDegree;
    }

    /**
     * 设置 学位
     * @param repDegree 学位
     */
    public void setRepDegree(String repDegree) {
        this.repDegree = repDegree == null ? null : repDegree.trim();
    }

    /**
     * 取得 首次参加工作时间

     * @return 首次参加工作时间

     */
    public Integer getRepFirstWorkTime() {
        return repFirstWorkTime;
    }

    /**
     * 设置 首次参加工作时间

     * @param repFirstWorkTime 首次参加工作时间

     */
    public void setRepFirstWorkTime(Integer repFirstWorkTime) {
        this.repFirstWorkTime = repFirstWorkTime;
    }

    /**
     * 取得 首次从事理财业务时间

     * @return 首次从事理财业务时间

     */
    public Integer getRepFirstFinancingTime() {
        return repFirstFinancingTime;
    }

    /**
     * 设置 首次从事理财业务时间

     * @param repFirstFinancingTime 首次从事理财业务时间

     */
    public void setRepFirstFinancingTime(Integer repFirstFinancingTime) {
        this.repFirstFinancingTime = repFirstFinancingTime;
    }

    /**
     * 取得 专业技术职称
     * @return 专业技术职称
     */
    public String getRepProTechTitle() {
        return repProTechTitle;
    }

    /**
     * 设置 专业技术职称
     * @param repProTechTitle 专业技术职称
     */
    public void setRepProTechTitle(String repProTechTitle) {
        this.repProTechTitle = repProTechTitle == null ? null : repProTechTitle.trim();
    }

    /**
     * 取得 理财专业证书
     * @return 理财专业证书
     */
    public String getRepFinancingCertificate() {
        return repFinancingCertificate;
    }

    /**
     * 设置 理财专业证书
     * @param repFinancingCertificate 理财专业证书
     */
    public void setRepFinancingCertificate(String repFinancingCertificate) {
        this.repFinancingCertificate = repFinancingCertificate == null ? null : repFinancingCertificate.trim();
    }

    /**
     * 取得 所获奖励
     * @return 所获奖励
     */
    public String getRepReword() {
        return repReword;
    }

    /**
     * 设置 所获奖励
     * @param repReword 所获奖励
     */
    public void setRepReword(String repReword) {
        this.repReword = repReword == null ? null : repReword.trim();
    }

    /**
     * 取得 办公电话
     * @return 办公电话
     */
    public String getRepTelephone() {
        return repTelephone;
    }

    /**
     * 设置 办公电话
     * @param repTelephone 办公电话
     */
    public void setRepTelephone(String repTelephone) {
        this.repTelephone = repTelephone == null ? null : repTelephone.trim();
    }

    /**
     * 取得 移动电话
     * @return 移动电话
     */
    public String getRepMobilephone() {
        return repMobilephone;
    }

    /**
     * 设置 移动电话
     * @param repMobilephone 移动电话
     */
    public void setRepMobilephone(String repMobilephone) {
        this.repMobilephone = repMobilephone == null ? null : repMobilephone.trim();
    }

    /**
     * 取得 电子邮箱
     * @return 电子邮箱
     */
    public String getRepEmail() {
        return repEmail;
    }

    /**
     * 设置 电子邮箱
     * @param repEmail 电子邮箱
     */
    public void setRepEmail(String repEmail) {
        this.repEmail = repEmail == null ? null : repEmail.trim();
    }

    /**
     * 取得 登记日期
     * @return 登记日期
     */
    public Date getRepRegDate() {
        return repRegDate;
    }

    /**
     * 设置 登记日期
     * @param repRegDate 登记日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepRegDate(Date repRegDate) {
        this.repRegDate = repRegDate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getRepSendStatus() {
        return repSendStatus;
    }

    /**
     * 设置 报送状态
     * @param repSendStatus 报送状态
     */
    public void setRepSendStatus(String repSendStatus) {
        this.repSendStatus = repSendStatus == null ? null : repSendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRepStatusDate() {
        return repStatusDate;
    }

    /**
     * 设置 数据日期
     * @param repStatusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepStatusDate(Date repStatusDate) {
        this.repStatusDate = repStatusDate;
    }
}