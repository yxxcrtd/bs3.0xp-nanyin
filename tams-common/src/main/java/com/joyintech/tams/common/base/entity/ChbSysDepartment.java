package com.joyintech.tams.common.base.entity;

public class ChbSysDepartment extends ChbSysDepartmentKey {
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 是否投资处室
     */
    private String invOffices;

    /**
     * 部门职能
     */
    private String deptFun;

    /**
     * 市场机构代码
     */
    private String institutionCode;

    /**
     * 上级部门
     */
    private String directDeptCode;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 取得 部门名称
     * @return 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置 部门名称
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * 取得 是否投资处室
     * @return 是否投资处室
     */
    public String getInvOffices() {
        return invOffices;
    }

    /**
     * 设置 是否投资处室
     * @param invOffices 是否投资处室
     */
    public void setInvOffices(String invOffices) {
        this.invOffices = invOffices == null ? null : invOffices.trim();
    }

    /**
     * 取得 部门职能
     * @return 部门职能
     */
    public String getDeptFun() {
        return deptFun;
    }

    /**
     * 设置 部门职能
     * @param deptFun 部门职能
     */
    public void setDeptFun(String deptFun) {
        this.deptFun = deptFun == null ? null : deptFun.trim();
    }

    /**
     * 取得 市场机构代码
     * @return 市场机构代码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 市场机构代码
     * @param institutionCode 市场机构代码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    /**
     * 取得 上级部门
     * @return 上级部门
     */
    public String getDirectDeptCode() {
        return directDeptCode;
    }

    /**
     * 设置 上级部门
     * @param directDeptCode 上级部门
     */
    public void setDirectDeptCode(String directDeptCode) {
        this.directDeptCode = directDeptCode == null ? null : directDeptCode.trim();
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