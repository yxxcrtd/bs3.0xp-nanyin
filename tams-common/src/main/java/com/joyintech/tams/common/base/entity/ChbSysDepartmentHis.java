package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysDepartmentHis extends BaseEntity {
    /**
     * ����
     */
    private String deptCode;

    /**
     * ����
     */
    private Integer hisNo;

    /**
     * ����
     */
    private String deptName;

    /**
     * ������ ������
     */
    private String invOffices;

    /**
     * ���� ������
     */
    private String deptFun;

    /**
     * ����
     */
    private String institutionCode;

    /**
     * ����
     */
    private String directDeptCode;

    /**
     * �����
     */
    private String workflowId;

    /**
     * ��
     */
    private String remark;

    /**
     * 取得 ����
     * @return ����
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 ����
     * @param deptCode ����
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 ����
     * @param hisNo ����
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置 ����
     * @param deptName ����
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * 取得 ������ ������
     * @return ������ ������
     */
    public String getInvOffices() {
        return invOffices;
    }

    /**
     * 设置 ������ ������
     * @param invOffices ������ ������
     */
    public void setInvOffices(String invOffices) {
        this.invOffices = invOffices == null ? null : invOffices.trim();
    }

    /**
     * 取得 ���� ������
     * @return ���� ������
     */
    public String getDeptFun() {
        return deptFun;
    }

    /**
     * 设置 ���� ������
     * @param deptFun ���� ������
     */
    public void setDeptFun(String deptFun) {
        this.deptFun = deptFun == null ? null : deptFun.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 ����
     * @param institutionCode ����
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getDirectDeptCode() {
        return directDeptCode;
    }

    /**
     * 设置 ����
     * @param directDeptCode ����
     */
    public void setDirectDeptCode(String directDeptCode) {
        this.directDeptCode = directDeptCode == null ? null : directDeptCode.trim();
    }

    /**
     * 取得 �����
     * @return �����
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 �����
     * @param workflowId �����
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 ��
     * @return ��
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 ��
     * @param remark ��
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}