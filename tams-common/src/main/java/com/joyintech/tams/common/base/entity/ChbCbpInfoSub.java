package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbCbpInfoSub extends ChbCbpInfoSubKey {
    /**
     * 从业人员类型
     */
    private String empoyeeType;

    /**
     * 从业人员登记编码
     */
    private String empoyeeRegistNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 参照数据字典
     */
    private String sex;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 身份证号码
     */
    private String personId;

    /**
     * 所属总行/分支行 参照数据字典
     */
    private String bank;

    /**
     * 所属区域
     */
    private String belongArea;

    /**
     * 具体单位名称
     */
    private String companyName;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 职位
     */
    private String position;

    /**
     * 学历 参照数据字典
     */
    private String education;

    /**
     * 学位 参照数据字典
     */
    private String degree;

    /**
     * 首次参加工作时间
     */
    private Date fWorkDate;

    /**
     * 首次从事理财业务时间
     */
    private Date firstFinaDate;

    /**
     * 专业技术职称 参照数据字典
     */
    private String title;

    /**
     * 理财专业证书 参照数据字典
     */
    private String finanCert;

    /**
     * 所获奖励
     */
    private String reward;

    /**
     * 办公电话
     */
    private String phone;

    /**
     * 移动电话
     */
    private String mPhone;

    /**
     * 电子邮箱
     */
    private String mail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 从业人员类型
     * @return 从业人员类型
     */
    public String getEmpoyeeType() {
        return empoyeeType;
    }

    /**
     * 设置 从业人员类型
     * @param empoyeeType 从业人员类型
     */
    public void setEmpoyeeType(String empoyeeType) {
        this.empoyeeType = empoyeeType == null ? null : empoyeeType.trim();
    }

    /**
     * 取得 从业人员登记编码
     * @return 从业人员登记编码
     */
    public String getEmpoyeeRegistNo() {
        return empoyeeRegistNo;
    }

    /**
     * 设置 从业人员登记编码
     * @param empoyeeRegistNo 从业人员登记编码
     */
    public void setEmpoyeeRegistNo(String empoyeeRegistNo) {
        this.empoyeeRegistNo = empoyeeRegistNo == null ? null : empoyeeRegistNo.trim();
    }

    /**
     * 取得 姓名
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 取得 性别 参照数据字典
     * @return 性别 参照数据字典
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置 性别 参照数据字典
     * @param sex 性别 参照数据字典
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
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
    public String getPersonId() {
        return personId;
    }

    /**
     * 设置 身份证号码
     * @param personId 身份证号码
     */
    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    /**
     * 取得 所属总行/分支行 参照数据字典
     * @return 所属总行/分支行 参照数据字典
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置 所属总行/分支行 参照数据字典
     * @param bank 所属总行/分支行 参照数据字典
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     * 取得 所属区域
     * @return 所属区域
     */
    public String getBelongArea() {
        return belongArea;
    }

    /**
     * 设置 所属区域
     * @param belongArea 所属区域
     */
    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea == null ? null : belongArea.trim();
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
    public String getDepartment() {
        return department;
    }

    /**
     * 设置 所属部门
     * @param department 所属部门
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
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
     * 取得 学历 参照数据字典
     * @return 学历 参照数据字典
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置 学历 参照数据字典
     * @param education 学历 参照数据字典
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 取得 学位 参照数据字典
     * @return 学位 参照数据字典
     */
    public String getDegree() {
        return degree;
    }

    /**
     * 设置 学位 参照数据字典
     * @param degree 学位 参照数据字典
     */
    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    /**
     * 取得 首次参加工作时间
     * @return 首次参加工作时间
     */
    public Date getfWorkDate() {
        return fWorkDate;
    }

    /**
     * 设置 首次参加工作时间
     * @param fWorkDate 首次参加工作时间
     */
    public void setfWorkDate(Date fWorkDate) {
        this.fWorkDate = fWorkDate;
    }

    /**
     * 取得 首次从事理财业务时间
     * @return 首次从事理财业务时间
     */
    public Date getFirstFinaDate() {
        return firstFinaDate;
    }

    /**
     * 设置 首次从事理财业务时间
     * @param firstFinaDate 首次从事理财业务时间
     */
    public void setFirstFinaDate(Date firstFinaDate) {
        this.firstFinaDate = firstFinaDate;
    }

    /**
     * 取得 专业技术职称 参照数据字典
     * @return 专业技术职称 参照数据字典
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 专业技术职称 参照数据字典
     * @param title 专业技术职称 参照数据字典
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 取得 理财专业证书 参照数据字典
     * @return 理财专业证书 参照数据字典
     */
    public String getFinanCert() {
        return finanCert;
    }

    /**
     * 设置 理财专业证书 参照数据字典
     * @param finanCert 理财专业证书 参照数据字典
     */
    public void setFinanCert(String finanCert) {
        this.finanCert = finanCert == null ? null : finanCert.trim();
    }

    /**
     * 取得 所获奖励
     * @return 所获奖励
     */
    public String getReward() {
        return reward;
    }

    /**
     * 设置 所获奖励
     * @param reward 所获奖励
     */
    public void setReward(String reward) {
        this.reward = reward == null ? null : reward.trim();
    }

    /**
     * 取得 办公电话
     * @return 办公电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置 办公电话
     * @param phone 办公电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 取得 移动电话
     * @return 移动电话
     */
    public String getmPhone() {
        return mPhone;
    }

    /**
     * 设置 移动电话
     * @param mPhone 移动电话
     */
    public void setmPhone(String mPhone) {
        this.mPhone = mPhone == null ? null : mPhone.trim();
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
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
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
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}