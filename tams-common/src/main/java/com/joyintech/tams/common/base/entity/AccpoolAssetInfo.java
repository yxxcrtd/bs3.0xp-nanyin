package com.joyintech.tams.common.base.entity;

public class AccpoolAssetInfo extends AccpoolAssetInfoKey {
    /**
     * 机构名称
     */
    private String compName;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 资产类型
     */
    private String finprodType;

    /**
     * 评级结果
     */
    private String exlevel;

    /**
     * 评级结果
     */
    private String inlevel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 机构名称
     * @return 机构名称
     */
    public String getCompName() {
        return compName;
    }

    /**
     * 设置 机构名称
     * @param compName 机构名称
     */
    public void setCompName(String compName) {
        this.compName = compName == null ? null : compName.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 资产名称
     * @param finprodName 资产名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 资产类型
     * @param finprodType 资产类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getExlevel() {
        return exlevel;
    }

    /**
     * 设置 评级结果
     * @param exlevel 评级结果
     */
    public void setExlevel(String exlevel) {
        this.exlevel = exlevel == null ? null : exlevel.trim();
    }

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getInlevel() {
        return inlevel;
    }

    /**
     * 设置 评级结果
     * @param inlevel 评级结果
     */
    public void setInlevel(String inlevel) {
        this.inlevel = inlevel == null ? null : inlevel.trim();
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
}