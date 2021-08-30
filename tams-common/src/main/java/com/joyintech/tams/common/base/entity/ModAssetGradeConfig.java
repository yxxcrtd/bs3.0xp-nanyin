package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ModAssetGradeConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 资产类型(证券品种_模型)
     */
    private String assetType;

    /**
     * 资产细分类(数据字典 证券细分类_模型)
     */
    private String assetDetailType;

    /**
     * 评级机构（数据字典 评级机构）
     */
    private String gradeOrgId;

    /**
     * 评级类型（数据字典 评级类型_模型）
     */
    private String oType;

    /**
     * 评级机构类型
     */
    private String gradeOrgType;

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
     * 取得 资产类型(证券品种_模型)
     * @return 资产类型(证券品种_模型)
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型(证券品种_模型)
     * @param assetType 资产类型(证券品种_模型)
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 资产细分类(数据字典 证券细分类_模型)
     * @return 资产细分类(数据字典 证券细分类_模型)
     */
    public String getAssetDetailType() {
        return assetDetailType;
    }

    /**
     * 设置 资产细分类(数据字典 证券细分类_模型)
     * @param assetDetailType 资产细分类(数据字典 证券细分类_模型)
     */
    public void setAssetDetailType(String assetDetailType) {
        this.assetDetailType = assetDetailType == null ? null : assetDetailType.trim();
    }

    /**
     * 取得 评级机构（数据字典 评级机构）
     * @return 评级机构（数据字典 评级机构）
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    /**
     * 设置 评级机构（数据字典 评级机构）
     * @param gradeOrgId 评级机构（数据字典 评级机构）
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId == null ? null : gradeOrgId.trim();
    }

    /**
     * 取得 评级类型（数据字典 评级类型_模型）
     * @return 评级类型（数据字典 评级类型_模型）
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 评级类型（数据字典 评级类型_模型）
     * @param oType 评级类型（数据字典 评级类型_模型）
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
    }

    /**
     * 取得 评级机构类型
     * @return 评级机构类型
     */
    public String getGradeOrgType() {
        return gradeOrgType;
    }

    /**
     * 设置 评级机构类型
     * @param gradeOrgType 评级机构类型
     */
    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType == null ? null : gradeOrgType.trim();
    }
}