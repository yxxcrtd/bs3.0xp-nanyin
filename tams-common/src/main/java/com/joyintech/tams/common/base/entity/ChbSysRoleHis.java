package com.joyintech.tams.common.base.entity;

public class ChbSysRoleHis extends ChbSysRoleHisKey {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色岗位
     */
    private String rolePos;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 自己创建的数据能否查看
     */
    private String dataAuthoritySelfFlg;

    /**
     * 自己部门的数据能否查看
     */
    private String dataAuthorityDeptFlg;

    /**
     * 所有数据能否查看
     */
    private String dataAuthorityAllFlg;

    /**
     * 所有交易数据能否查看
     */
    private String tradeDataAuthorityAllFlg;

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
     * 取得 角色名称
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置 角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 取得 角色岗位
     * @return 角色岗位
     */
    public String getRolePos() {
        return rolePos;
    }

    /**
     * 设置 角色岗位
     * @param rolePos 角色岗位
     */
    public void setRolePos(String rolePos) {
        this.rolePos = rolePos == null ? null : rolePos.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 自己创建的数据能否查看
     * @return 自己创建的数据能否查看
     */
    public String getDataAuthoritySelfFlg() {
        return dataAuthoritySelfFlg;
    }

    /**
     * 设置 自己创建的数据能否查看
     * @param dataAuthoritySelfFlg 自己创建的数据能否查看
     */
    public void setDataAuthoritySelfFlg(String dataAuthoritySelfFlg) {
        this.dataAuthoritySelfFlg = dataAuthoritySelfFlg == null ? null : dataAuthoritySelfFlg.trim();
    }

    /**
     * 取得 自己部门的数据能否查看
     * @return 自己部门的数据能否查看
     */
    public String getDataAuthorityDeptFlg() {
        return dataAuthorityDeptFlg;
    }

    /**
     * 设置 自己部门的数据能否查看
     * @param dataAuthorityDeptFlg 自己部门的数据能否查看
     */
    public void setDataAuthorityDeptFlg(String dataAuthorityDeptFlg) {
        this.dataAuthorityDeptFlg = dataAuthorityDeptFlg == null ? null : dataAuthorityDeptFlg.trim();
    }

    /**
     * 取得 所有数据能否查看
     * @return 所有数据能否查看
     */
    public String getDataAuthorityAllFlg() {
        return dataAuthorityAllFlg;
    }

    /**
     * 设置 所有数据能否查看
     * @param dataAuthorityAllFlg 所有数据能否查看
     */
    public void setDataAuthorityAllFlg(String dataAuthorityAllFlg) {
        this.dataAuthorityAllFlg = dataAuthorityAllFlg == null ? null : dataAuthorityAllFlg.trim();
    }

    /**
     * 取得 所有交易数据能否查看
     * @return 所有交易数据能否查看
     */
    public String getTradeDataAuthorityAllFlg() {
        return tradeDataAuthorityAllFlg;
    }

    /**
     * 设置 所有交易数据能否查看
     * @param tradeDataAuthorityAllFlg 所有交易数据能否查看
     */
    public void setTradeDataAuthorityAllFlg(String tradeDataAuthorityAllFlg) {
        this.tradeDataAuthorityAllFlg = tradeDataAuthorityAllFlg == null ? null : tradeDataAuthorityAllFlg.trim();
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