package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

/**
 * 人行资金信托代码管理
 *
 * @author kx
 */
public class PbcCapAssetInfo extends BaseEntity {
    /**
     * 主键，自动生成
     */
    private String assetUuid;

    /**
     * 资产代码
     */
    private String assetid;

    /**
     * 资产名称
     */
    private String assetname;

    /**
     * 人行资金信托代码
     */
    private String pobcassetid;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 取得 主键，自动生成
     *
     * @return 主键，自动生成
     */
    public String getAssetUuid() {
        return assetUuid;
    }

    /**
     * 设置 主键，自动生成
     *
     * @param assetUuid 主键，自动生成
     */
    public void setAssetUuid(String assetUuid) {
        this.assetUuid = assetUuid == null ? null : assetUuid.trim();
    }

    /**
     * 取得 资产代码
     *
     * @return 资产代码
     */
    public String getAssetid() {
        return assetid;
    }

    /**
     * 设置 资产代码
     *
     * @param assetid 资产代码
     */
    public void setAssetid(String assetid) {
        this.assetid = assetid == null ? null : assetid.trim();
    }

    /**
     * 取得 资产名称
     *
     * @return 资产名称
     */
    public String getAssetname() {
        return assetname;
    }

    /**
     * 设置 资产名称
     *
     * @param assetname 资产名称
     */
    public void setAssetname(String assetname) {
        this.assetname = assetname == null ? null : assetname.trim();
    }

    /**
     * 取得 人行资金信托代码
     *
     * @return 人行资金信托代码
     */
    public String getPobcassetid() {
        return pobcassetid;
    }

    /**
     * 设置 人行资金信托代码
     *
     * @param pobcassetid 人行资金信托代码
     */
    public void setPobcassetid(String pobcassetid) {
        this.pobcassetid = pobcassetid == null ? null : pobcassetid.trim();
    }

    /**
     * 取得 机构代码
     *
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     *
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}