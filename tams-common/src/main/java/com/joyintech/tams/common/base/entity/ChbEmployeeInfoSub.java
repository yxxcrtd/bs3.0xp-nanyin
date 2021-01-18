package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbEmployeeInfoSub extends BaseEntity {
    /**
     * 从业人员类型
     */
    private String employeeType;

    /**
     * 姓名
     */
    private String employeeName;

    /**
     * 性别
     */
    private String employeeSex;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 身份证号码
     */
    private String identifyNumber;

    /**
     * 所属总行或分支行
     */
    private String belongsBank;

    /**
     * 所属区域
     */
    private String belongsPlace;

    /**
     * 具体单位名称
     */
    private String companyName;

    /**
     * 所属部门
     */
    private String belongsDept;

    /**
     * 职位
     */
    private String position;

    /**
     * 学历
     */
    private String education;

    /**
     * 学位
     */
    private String academicDegree;

    /**
     * 首次参加工作时间
     */
    private String firsttime2work;

    /**
     * 首次从事理财业务时间
     */
    private String firsttime2finwork;

    /**
     * 专业技术职称
     */
    private String professionalTitle;

    /**
     * 理财专业证书
     */
    private String finProfessionalCrtf;

    /**
     * 理财登记培训证书编号
     */
    private String finTrainingNo;

    /**
     * 所获奖励
     */
    private String awards;

    /**
     * 办公电话
     */
    private String officeTel;

    /**
     * 移动电话
     */
    private String mobileTel;

    /**
     * 电子邮箱
     */
    private String mail;

    /**
     * 登记业务分类
     */
    private String regBusiType;

    /**
     * 登记人员类别
     */
    private String regEmployeeType;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 报送流水号
     */
    private String sumitSeq;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 反馈状态 
     */
    private String submitFeedbackStatus;

    /**
     * 报送状态 
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 从业人员类型
     * @return 从业人员类型
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * 设置 从业人员类型
     * @param employeeType 从业人员类型
     */
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType == null ? null : employeeType.trim();
    }

    /**
     * 取得 姓名
     * @return 姓名
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 设置 姓名
     * @param employeeName 姓名
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    /**
     * 取得 性别
     * @return 性别
     */
    public String getEmployeeSex() {
        return employeeSex;
    }

    /**
     * 设置 性别
     * @param employeeSex 性别
     */
    public void setEmployeeSex(String employeeSex) {
        this.employeeSex = employeeSex == null ? null : employeeSex.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 身份证号码
     * @return 身份证号码
     */
    public String getIdentifyNumber() {
        return identifyNumber;
    }

    /**
     * 设置 身份证号码
     * @param identifyNumber 身份证号码
     */
    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber == null ? null : identifyNumber.trim();
    }

    /**
     * 取得 所属总行或分支行
     * @return 所属总行或分支行
     */
    public String getBelongsBank() {
        return belongsBank;
    }

    /**
     * 设置 所属总行或分支行
     * @param belongsBank 所属总行或分支行
     */
    public void setBelongsBank(String belongsBank) {
        this.belongsBank = belongsBank == null ? null : belongsBank.trim();
    }

    /**
     * 取得 所属区域
     * @return 所属区域
     */
    public String getBelongsPlace() {
        return belongsPlace;
    }

    /**
     * 设置 所属区域
     * @param belongsPlace 所属区域
     */
    public void setBelongsPlace(String belongsPlace) {
        this.belongsPlace = belongsPlace == null ? null : belongsPlace.trim();
    }

    /**
     * 取得 具体单位名称
     * @return 具体单位名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置 具体单位名称
     * @param companyName 具体单位名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 取得 所属部门
     * @return 所属部门
     */
    public String getBelongsDept() {
        return belongsDept;
    }

    /**
     * 设置 所属部门
     * @param belongsDept 所属部门
     */
    public void setBelongsDept(String belongsDept) {
        this.belongsDept = belongsDept == null ? null : belongsDept.trim();
    }

    /**
     * 取得 职位
     * @return 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置 职位
     * @param position 职位
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 取得 学历
     * @return 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置 学历
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 取得 学位
     * @return 学位
     */
    public String getAcademicDegree() {
        return academicDegree;
    }

    /**
     * 设置 学位
     * @param academicDegree 学位
     */
    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree == null ? null : academicDegree.trim();
    }

    /**
     * 取得 首次参加工作时间
     * @return 首次参加工作时间
     */
    public String getFirsttime2work() {
        return firsttime2work;
    }

    /**
     * 设置 首次参加工作时间
     * @param firsttime2work 首次参加工作时间
     */
    public void setFirsttime2work(String firsttime2work) {
        this.firsttime2work = firsttime2work == null ? null : firsttime2work.trim();
    }

    /**
     * 取得 首次从事理财业务时间
     * @return 首次从事理财业务时间
     */
    public String getFirsttime2finwork() {
        return firsttime2finwork;
    }

    /**
     * 设置 首次从事理财业务时间
     * @param firsttime2finwork 首次从事理财业务时间
     */
    public void setFirsttime2finwork(String firsttime2finwork) {
        this.firsttime2finwork = firsttime2finwork == null ? null : firsttime2finwork.trim();
    }

    /**
     * 取得 专业技术职称
     * @return 专业技术职称
     */
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    /**
     * 设置 专业技术职称
     * @param professionalTitle 专业技术职称
     */
    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle == null ? null : professionalTitle.trim();
    }

    /**
     * 取得 理财专业证书
     * @return 理财专业证书
     */
    public String getFinProfessionalCrtf() {
        return finProfessionalCrtf;
    }

    /**
     * 设置 理财专业证书
     * @param finProfessionalCrtf 理财专业证书
     */
    public void setFinProfessionalCrtf(String finProfessionalCrtf) {
        this.finProfessionalCrtf = finProfessionalCrtf == null ? null : finProfessionalCrtf.trim();
    }

    /**
     * 取得 理财登记培训证书编号
     * @return 理财登记培训证书编号
     */
    public String getFinTrainingNo() {
        return finTrainingNo;
    }

    /**
     * 设置 理财登记培训证书编号
     * @param finTrainingNo 理财登记培训证书编号
     */
    public void setFinTrainingNo(String finTrainingNo) {
        this.finTrainingNo = finTrainingNo == null ? null : finTrainingNo.trim();
    }

    /**
     * 取得 所获奖励
     * @return 所获奖励
     */
    public String getAwards() {
        return awards;
    }

    /**
     * 设置 所获奖励
     * @param awards 所获奖励
     */
    public void setAwards(String awards) {
        this.awards = awards == null ? null : awards.trim();
    }

    /**
     * 取得 办公电话
     * @return 办公电话
     */
    public String getOfficeTel() {
        return officeTel;
    }

    /**
     * 设置 办公电话
     * @param officeTel 办公电话
     */
    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel == null ? null : officeTel.trim();
    }

    /**
     * 取得 移动电话
     * @return 移动电话
     */
    public String getMobileTel() {
        return mobileTel;
    }

    /**
     * 设置 移动电话
     * @param mobileTel 移动电话
     */
    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel == null ? null : mobileTel.trim();
    }

    /**
     * 取得 电子邮箱
     * @return 电子邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     * 设置 电子邮箱
     * @param mail 电子邮箱
     */
    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    /**
     * 取得 登记业务分类
     * @return 登记业务分类
     */
    public String getRegBusiType() {
        return regBusiType;
    }

    /**
     * 设置 登记业务分类
     * @param regBusiType 登记业务分类
     */
    public void setRegBusiType(String regBusiType) {
        this.regBusiType = regBusiType == null ? null : regBusiType.trim();
    }

    /**
     * 取得 登记人员类别
     * @return 登记人员类别
     */
    public String getRegEmployeeType() {
        return regEmployeeType;
    }

    /**
     * 设置 登记人员类别
     * @param regEmployeeType 登记人员类别
     */
    public void setRegEmployeeType(String regEmployeeType) {
        this.regEmployeeType = regEmployeeType == null ? null : regEmployeeType.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
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

    /**
     * 取得 报送流水号
     * @return 报送流水号
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 报送流水号
     * @param sumitSeq 报送流水号
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
    }

    /**
     * 取得 中债报送截止日
     * @return 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 设置 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 取得 行内报送截止日
     * @return 行内报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 设置 行内报送截止日
     * @param submitDeadline 行内报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 取得 反馈状态 
     * @return 反馈状态 
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 设置 反馈状态 
     * @param sendStatus 反馈状态 
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }
    
    /**
     * 取得 报送状态 
     * @return 报送状态 
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态 
     * @param sendStatus 报送状态 
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 取得 报送日期
     * @return 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 报送日期
     * @param submitTime 报送日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
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
    
    public String getKey(){
    	return this.getEmployeeType() + "_" + this.getIdentifyNumber();
    }
}