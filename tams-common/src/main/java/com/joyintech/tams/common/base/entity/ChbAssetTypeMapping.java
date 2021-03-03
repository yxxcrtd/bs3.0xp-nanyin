package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbAssetTypeMapping extends BaseEntity {
    /**
     * 主键，自动生成
     */
    private String typeuuid;

    /**
     * 资产一级分类
     */
    private String assetType1;

    /**
     * 资产二级分类
     */
    private String assetType2;

    /**
     * 资产三级分类
     */
    private String assetType3;

    /**
     * 业务分类
     */
    private String assetBusiType;

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
     * 取得 资产一级分类
     * @return 资产一级分类
     */
    public String getAssetType1() {
        return assetType1;
    }

    /**
     * 设置 资产一级分类
     * @param assetType1 资产一级分类
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1 == null ? null : assetType1.trim();
    }

    /**
     * 取得 资产二级分类
     * @return 资产二级分类
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 资产二级分类
     * @param assetType2 资产二级分类
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 资产三级分类
     * @return 资产三级分类
     */
    public String getAssetType3() {
        return assetType3;
    }

    /**
     * 设置 资产三级分类
     * @param assetType3 资产三级分类
     */
    public void setAssetType3(String assetType3) {
        this.assetType3 = assetType3 == null ? null : assetType3.trim();
    }

    /**
     * 取得 业务分类
     * @return 业务分类
     */
    public String getAssetBusiType() {
        return assetBusiType;
    }

    /**
     * 设置 业务分类
     * @param assetBusiType 业务分类
     */
    public void setAssetBusiType(String assetBusiType) {
        this.assetBusiType = assetBusiType == null ? null : assetBusiType.trim();
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