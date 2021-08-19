package com.joyintech.tams.valuation.common.dto.modelDto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class AssetCategories extends BaseEntity {
    /**
     * 流水号
     */
    public String seqNo;

    /**
     * 资产类型（证券品种_模型）
     */
    public String assetType;

    /**
     * 资产细分类（数据字典证券细分类_模型）
     */
    public String assetDetailType;

    /**
     * 评级机构（数据字典 评级机构）
     */
    public String gradeOrgId;

    /**
     * 评级类型（数据字典 评级类型_模型）
     */
    public String oType;

    /**
     * 评级机构类型
     */
    private String gradeOrgType;

    /**
     * 创建人
     */
    public String createUser;

    /**
     * 创建部门
     */
    public String createDept;

    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 更新人
     */
    public String updateUser;

    /**
     * 更新时间
     */
    public Date updateTime;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetDetailType() {
        return assetDetailType;
    }

    public void setAssetDetailType(String assetDetailType) {
        this.assetDetailType = assetDetailType;
    }

    public String getGradeOrgId() {
        return gradeOrgId;
    }

    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGradeOrgType() {
        return gradeOrgType;
    }

    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType;
    }
}