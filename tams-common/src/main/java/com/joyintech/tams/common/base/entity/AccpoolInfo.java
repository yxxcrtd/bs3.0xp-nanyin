package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class AccpoolInfo extends BaseEntity {
    /**
     * 准入池代码
     */
    private String accpoolId;

    /**
     * 准入池名称
     */
    private String accpoolName;

    /**
     * 准入池类型包括资产池、机构池
     */
    private String accpoolType;

    /**
     * 准入池属性包括黑名单、白名单、灰名单
     */
    private String accpoolProp;

    /**
     * 准入池代码
     */
    private String accpoolId2;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 准入池代码
     * @return 准入池代码
     */
    public String getAccpoolId() {
        return accpoolId;
    }

    /**
     * 设置 准入池代码
     * @param accpoolId 准入池代码
     */
    public void setAccpoolId(String accpoolId) {
        this.accpoolId = accpoolId == null ? null : accpoolId.trim();
    }

    /**
     * 取得 准入池名称
     * @return 准入池名称
     */
    public String getAccpoolName() {
        return accpoolName;
    }

    /**
     * 设置 准入池名称
     * @param accpoolName 准入池名称
     */
    public void setAccpoolName(String accpoolName) {
        this.accpoolName = accpoolName == null ? null : accpoolName.trim();
    }

    /**
     * 取得 准入池类型包括资产池、机构池
     * @return 准入池类型包括资产池、机构池
     */
    public String getAccpoolType() {
        return accpoolType;
    }

    /**
     * 设置 准入池类型包括资产池、机构池
     * @param accpoolType 准入池类型包括资产池、机构池
     */
    public void setAccpoolType(String accpoolType) {
        this.accpoolType = accpoolType == null ? null : accpoolType.trim();
    }

    /**
     * 取得 准入池属性包括黑名单、白名单、灰名单
     * @return 准入池属性包括黑名单、白名单、灰名单
     */
    public String getAccpoolProp() {
        return accpoolProp;
    }

    /**
     * 设置 准入池属性包括黑名单、白名单、灰名单
     * @param accpoolProp 准入池属性包括黑名单、白名单、灰名单
     */
    public void setAccpoolProp(String accpoolProp) {
        this.accpoolProp = accpoolProp == null ? null : accpoolProp.trim();
    }

    /**
     * 取得 准入池代码
     * @return 准入池代码
     */
    public String getAccpoolId2() {
        return accpoolId2;
    }

    /**
     * 设置 准入池代码
     * @param accpoolId2 准入池代码
     */
    public void setAccpoolId2(String accpoolId2) {
        this.accpoolId2 = accpoolId2 == null ? null : accpoolId2.trim();
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
}