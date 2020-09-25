package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class AccpoolRule extends AccpoolRuleKey {
    /**
     * 机构代码
     */
    private String compNo;

    /**
     * 资产类型
     */
    private String finprodType;

    /**
     * 二级资产类型
     */
    private String finprodType2;

    /**
     * 评级结果
     */
    private String exlevel;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 评级结果
     */
    private String inlevel;

    /**
     * 生效日
     */
    private Date vdate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getCompNo() {
        return compNo;
    }

    /**
     * 设置 机构代码
     * @param compNo 机构代码
     */
    public void setCompNo(String compNo) {
        this.compNo = compNo == null ? null : compNo.trim();
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
     * 取得 二级资产类型
     * @return 二级资产类型
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 二级资产类型
     * @param finprodType2 二级资产类型
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
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
     * 取得 资产代码
     * @return 资产代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 资产代码
     * @param finprodId 资产代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
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
     * 取得 生效日
     * @return 生效日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 生效日
     * @param vdate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
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