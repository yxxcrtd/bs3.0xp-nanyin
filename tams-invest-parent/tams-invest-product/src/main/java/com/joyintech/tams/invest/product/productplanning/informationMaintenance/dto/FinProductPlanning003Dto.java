package com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.util.BigDecimalSerializer;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产规划信息结果dto
 */
public class FinProductPlanning003Dto extends BaseEntity implements ProcessEntity {
    /**
    * 资产规划编号
    */
    private  String assetPlanCode;
    /**
     * 基准日
     */
    private Date benchDate;
    /**
     * 开始日
     */
    private Date vDate;
    /**
     * 结束日
     */
    private Date eDate;
    /**
     * 目标总规模
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal targetScale;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建部门
     */
    private String createDept;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     *更新时间
     */
    private Date updateDate;

    public String getAssetPlanCode() {
        return assetPlanCode;
    }

    public void setAssetPlanCode(String assetPlanCode) {
        this.assetPlanCode = assetPlanCode;
    }

    public Date getBenchDate() {
        return benchDate;
    }

    public void setBenchDate(Date benchDate) {
        this.benchDate = benchDate;
    }

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public BigDecimal getTargetScale() {
        return targetScale;
    }

    public void setTargetScale(BigDecimal targetScale) {
        this.targetScale = targetScale;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
