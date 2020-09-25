package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbEquHoldHis extends ChbEquHoldHisKey {
    /**
     * 原始识别标识
     */
    private String orgIndentifyMark;

    /**
     * 该权益人是否属于本行
     */
    private String isBelongBank;

    /**
     * 权益人所属银行名称
     */
    private String theBankName;

    /**
     * 权益人所属银行代码
     */
    private String theBankCode;

    /**
     * 权益人境内外标识
     */
    private String holderMark;

    /**
     * 权益人所属国家或地区
     */
    private String holderRegion;

    /**
     * 权益人类别
     */
    private String holderType;

    /**
     * 个人证件类别
     */
    private String personalIdentifyType;

    /**
     * 机构证件类别
     */
    private String institutionIdentifyType;

    /**
     * 其他证件名称
     */
    private String otherIdentifyName;

    /**
     * 证件号码
     */
    private String identifyNumber;

    /**
     * SPV资金托管账户开户行
     */
    private String spvCapAccBank;

    /**
     * 其他资金托管账户开户行
     */
    private String otherCapAccBank;

    /**
     * SPV登记编码
     */
    private String spvRegistCode;

    /**
     * 权益人登记日期
     */
    private Date holderDate;

    /**
     * 权益人名称
     */
    private String holderName;

    /**
     * 性别
     */
    private String holderSex;

    /**
     * 风险偏好
     */
    private String holderRiskPrefer;

    /**
     * 手机号码
     */
    private String holderCellphone;

    /**
     * 固定电话
     */
    private String holderTelephone;

    /**
     * 是否报送
     */
    private String isSubmit;

    /**
     * 电子邮箱
     */
    private String holderEmail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 经办人
     */
    private String agent;

    /**
     * 取得 原始识别标识
     * @return 原始识别标识
     */
    public String getOrgIndentifyMark() {
        return orgIndentifyMark;
    }

    /**
     * 设置 原始识别标识
     * @param orgIndentifyMark 原始识别标识
     */
    public void setOrgIndentifyMark(String orgIndentifyMark) {
        this.orgIndentifyMark = orgIndentifyMark == null ? null : orgIndentifyMark.trim();
    }

    /**
     * 取得 该权益人是否属于本行
     * @return 该权益人是否属于本行
     */
    public String getIsBelongBank() {
        return isBelongBank;
    }

    /**
     * 设置 该权益人是否属于本行
     * @param isBelongBank 该权益人是否属于本行
     */
    public void setIsBelongBank(String isBelongBank) {
        this.isBelongBank = isBelongBank == null ? null : isBelongBank.trim();
    }

    /**
     * 取得 权益人所属银行名称
     * @return 权益人所属银行名称
     */
    public String getTheBankName() {
        return theBankName;
    }

    /**
     * 设置 权益人所属银行名称
     * @param theBankName 权益人所属银行名称
     */
    public void setTheBankName(String theBankName) {
        this.theBankName = theBankName == null ? null : theBankName.trim();
    }

    /**
     * 取得 权益人所属银行代码
     * @return 权益人所属银行代码
     */
    public String getTheBankCode() {
        return theBankCode;
    }

    /**
     * 设置 权益人所属银行代码
     * @param theBankCode 权益人所属银行代码
     */
    public void setTheBankCode(String theBankCode) {
        this.theBankCode = theBankCode == null ? null : theBankCode.trim();
    }

    /**
     * 取得 权益人境内外标识
     * @return 权益人境内外标识
     */
    public String getHolderMark() {
        return holderMark;
    }

    /**
     * 设置 权益人境内外标识
     * @param holderMark 权益人境内外标识
     */
    public void setHolderMark(String holderMark) {
        this.holderMark = holderMark == null ? null : holderMark.trim();
    }

    /**
     * 取得 权益人所属国家或地区
     * @return 权益人所属国家或地区
     */
    public String getHolderRegion() {
        return holderRegion;
    }

    /**
     * 设置 权益人所属国家或地区
     * @param holderRegion 权益人所属国家或地区
     */
    public void setHolderRegion(String holderRegion) {
        this.holderRegion = holderRegion == null ? null : holderRegion.trim();
    }

    /**
     * 取得 权益人类别
     * @return 权益人类别
     */
    public String getHolderType() {
        return holderType;
    }

    /**
     * 设置 权益人类别
     * @param holderType 权益人类别
     */
    public void setHolderType(String holderType) {
        this.holderType = holderType == null ? null : holderType.trim();
    }

    /**
     * 取得 个人证件类别
     * @return 个人证件类别
     */
    public String getPersonalIdentifyType() {
        return personalIdentifyType;
    }

    /**
     * 设置 个人证件类别
     * @param personalIdentifyType 个人证件类别
     */
    public void setPersonalIdentifyType(String personalIdentifyType) {
        this.personalIdentifyType = personalIdentifyType == null ? null : personalIdentifyType.trim();
    }

    /**
     * 取得 机构证件类别
     * @return 机构证件类别
     */
    public String getInstitutionIdentifyType() {
        return institutionIdentifyType;
    }

    /**
     * 设置 机构证件类别
     * @param institutionIdentifyType 机构证件类别
     */
    public void setInstitutionIdentifyType(String institutionIdentifyType) {
        this.institutionIdentifyType = institutionIdentifyType == null ? null : institutionIdentifyType.trim();
    }

    /**
     * 取得 其他证件名称
     * @return 其他证件名称
     */
    public String getOtherIdentifyName() {
        return otherIdentifyName;
    }

    /**
     * 设置 其他证件名称
     * @param otherIdentifyName 其他证件名称
     */
    public void setOtherIdentifyName(String otherIdentifyName) {
        this.otherIdentifyName = otherIdentifyName == null ? null : otherIdentifyName.trim();
    }

    /**
     * 取得 证件号码
     * @return 证件号码
     */
    public String getIdentifyNumber() {
        return identifyNumber;
    }

    /**
     * 设置 证件号码
     * @param identifyNumber 证件号码
     */
    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber == null ? null : identifyNumber.trim();
    }

    /**
     * 取得 SPV资金托管账户开户行
     * @return SPV资金托管账户开户行
     */
    public String getSpvCapAccBank() {
        return spvCapAccBank;
    }

    /**
     * 设置 SPV资金托管账户开户行
     * @param spvCapAccBank SPV资金托管账户开户行
     */
    public void setSpvCapAccBank(String spvCapAccBank) {
        this.spvCapAccBank = spvCapAccBank == null ? null : spvCapAccBank.trim();
    }

    /**
     * 取得 其他资金托管账户开户行
     * @return 其他资金托管账户开户行
     */
    public String getOtherCapAccBank() {
        return otherCapAccBank;
    }

    /**
     * 设置 其他资金托管账户开户行
     * @param otherCapAccBank 其他资金托管账户开户行
     */
    public void setOtherCapAccBank(String otherCapAccBank) {
        this.otherCapAccBank = otherCapAccBank == null ? null : otherCapAccBank.trim();
    }

    /**
     * 取得 SPV登记编码
     * @return SPV登记编码
     */
    public String getSpvRegistCode() {
        return spvRegistCode;
    }

    /**
     * 设置 SPV登记编码
     * @param spvRegistCode SPV登记编码
     */
    public void setSpvRegistCode(String spvRegistCode) {
        this.spvRegistCode = spvRegistCode == null ? null : spvRegistCode.trim();
    }

    /**
     * 取得 权益人登记日期
     * @return 权益人登记日期
     */
    public Date getHolderDate() {
        return holderDate;
    }

    /**
     * 设置 权益人登记日期
     * @param holderDate 权益人登记日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHolderDate(Date holderDate) {
        this.holderDate = holderDate;
    }

    /**
     * 取得 权益人名称
     * @return 权益人名称
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * 设置 权益人名称
     * @param holderName 权益人名称
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName == null ? null : holderName.trim();
    }

    /**
     * 取得 性别
     * @return 性别
     */
    public String getHolderSex() {
        return holderSex;
    }

    /**
     * 设置 性别
     * @param holderSex 性别
     */
    public void setHolderSex(String holderSex) {
        this.holderSex = holderSex == null ? null : holderSex.trim();
    }

    /**
     * 取得 风险偏好
     * @return 风险偏好
     */
    public String getHolderRiskPrefer() {
        return holderRiskPrefer;
    }

    /**
     * 设置 风险偏好
     * @param holderRiskPrefer 风险偏好
     */
    public void setHolderRiskPrefer(String holderRiskPrefer) {
        this.holderRiskPrefer = holderRiskPrefer == null ? null : holderRiskPrefer.trim();
    }

    /**
     * 取得 手机号码
     * @return 手机号码
     */
    public String getHolderCellphone() {
        return holderCellphone;
    }

    /**
     * 设置 手机号码
     * @param holderCellphone 手机号码
     */
    public void setHolderCellphone(String holderCellphone) {
        this.holderCellphone = holderCellphone == null ? null : holderCellphone.trim();
    }

    /**
     * 取得 固定电话
     * @return 固定电话
     */
    public String getHolderTelephone() {
        return holderTelephone;
    }

    /**
     * 设置 固定电话
     * @param holderTelephone 固定电话
     */
    public void setHolderTelephone(String holderTelephone) {
        this.holderTelephone = holderTelephone == null ? null : holderTelephone.trim();
    }

    /**
     * 取得 是否报送
     * @return 是否报送
     */
    public String getIsSubmit() {
        return isSubmit;
    }

    /**
     * 设置 是否报送
     * @param isSubmit 是否报送
     */
    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit == null ? null : isSubmit.trim();
    }

    /**
     * 取得 电子邮箱
     * @return 电子邮箱
     */
    public String getHolderEmail() {
        return holderEmail;
    }

    /**
     * 设置 电子邮箱
     * @param holderEmail 电子邮箱
     */
    public void setHolderEmail(String holderEmail) {
        this.holderEmail = holderEmail == null ? null : holderEmail.trim();
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
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
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
     * 取得 经办人
     * @return 经办人
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 经办人
     * @param agent 经办人
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }
}