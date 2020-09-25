package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysOrganization extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构类型
     */
    private String orgType;

    /**
     * 上级机构
     */
    private String fOrg;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String contactNum;

    /**
     * 层级信息
     */
    private String hierarchyInfo;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 县/市
     */
    private String county;

    /**
     * 其他
     */
    private String otherInfo;

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
     * 上级机构代码
     */
    private String supOrgCode;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
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
     * 取得 机构名称
     * @return 机构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置 机构名称
     * @param orgName 机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * 取得 机构类型
     * @return 机构类型
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置 机构类型
     * @param orgType 机构类型
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    /**
     * 取得 上级机构
     * @return 上级机构
     */
    public String getfOrg() {
        return fOrg;
    }

    /**
     * 设置 上级机构
     * @param fOrg 上级机构
     */
    public void setfOrg(String fOrg) {
        this.fOrg = fOrg == null ? null : fOrg.trim();
    }

    /**
     * 取得 联系人
     * @return 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置 联系人
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * 取得 联系电话
     * @return 联系电话
     */
    public String getContactNum() {
        return contactNum;
    }

    /**
     * 设置 联系电话
     * @param contactNum 联系电话
     */
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    }

    /**
     * 取得 层级信息
     * @return 层级信息
     */
    public String getHierarchyInfo() {
        return hierarchyInfo;
    }

    /**
     * 设置 层级信息
     * @param hierarchyInfo 层级信息
     */
    public void setHierarchyInfo(String hierarchyInfo) {
        this.hierarchyInfo = hierarchyInfo == null ? null : hierarchyInfo.trim();
    }

    /**
     * 取得 省份
     * @return 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置 省份
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 取得 城市
     * @return 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置 城市
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 取得 县/市
     * @return 县/市
     */
    public String getCounty() {
        return county;
    }

    /**
     * 设置 县/市
     * @param county 县/市
     */
    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    /**
     * 取得 其他
     * @return 其他
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    /**
     * 设置 其他
     * @param otherInfo 其他
     */
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo == null ? null : otherInfo.trim();
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
     * 取得 上级机构代码
     * @return 上级机构代码
     */
    public String getSupOrgCode() {
        return supOrgCode;
    }

    /**
     * 设置 上级机构代码
     * @param supOrgCode 上级机构代码
     */
    public void setSupOrgCode(String supOrgCode) {
        this.supOrgCode = supOrgCode == null ? null : supOrgCode.trim();
    }

    /**
     * 取得 登记银行代码
     * @return 登记银行代码
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 登记银行代码
     * @param registBankCode 登记银行代码
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }
}