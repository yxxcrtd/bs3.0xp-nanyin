package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbAssetTypeMappingEx extends BaseEntity {
    /**
     * 主键，自动生成
     */
    private String typeuuid;

    /**
     * 资产/负债类别
     */
    private String chbAssetType;

    /**
     * 资产/负债二级类别
     */
    private String chbAssetType2;

    /**
     * 资产/负债类别映射表uuid
     */
    private String mappingUuid;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 取得 主键，自动生成
     * @return 主键，自动生成
     */
    public String getTypeuuid() {
        return typeuuid;
    }

    /**
     * 设置 主键，自动生成
     * @param typeuuid 主键，自动生成
     */
    public void setTypeuuid(String typeuuid) {
        this.typeuuid = typeuuid == null ? null : typeuuid.trim();
    }

    /**
     * 取得 资产/负债类别
     * @return 资产/负债类别
     */
    public String getChbAssetType() {
        return chbAssetType;
    }

    /**
     * 设置 资产/负债类别
     * @param chbAssetType 资产/负债类别
     */
    public void setChbAssetType(String chbAssetType) {
        this.chbAssetType = chbAssetType == null ? null : chbAssetType.trim();
    }

    /**
     * 取得 资产/负债二级类别
     * @return 资产/负债二级类别
     */
    public String getChbAssetType2() {
        return chbAssetType2;
    }

    /**
     * 设置 资产/负债二级类别
     * @param chbAssetType2 资产/负债二级类别
     */
    public void setChbAssetType2(String chbAssetType2) {
        this.chbAssetType2 = chbAssetType2 == null ? null : chbAssetType2.trim();
    }

    /**
     * 取得 资产/负债类别映射表uuid
     * @return 资产/负债类别映射表uuid
     */
    public String getMappingUuid() {
        return mappingUuid;
    }

    /**
     * 设置 资产/负债类别映射表uuid
     * @param mappingUuid 资产/负债类别映射表uuid
     */
    public void setMappingUuid(String mappingUuid) {
        this.mappingUuid = mappingUuid == null ? null : mappingUuid.trim();
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
}