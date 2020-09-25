package com.joyintech.tams.common.base.entity;

public class PbcProdAssetpoolCode extends PbcProdAssetpoolCodeKey {
    /**
     * 人行代码
     */
    private String pbcCode;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 取得 人行代码
     * @return 人行代码
     */
    public String getPbcCode() {
        return pbcCode;
    }

    /**
     * 设置 人行代码
     * @param pbcCode 人行代码
     */
    public void setPbcCode(String pbcCode) {
        this.pbcCode = pbcCode == null ? null : pbcCode.trim();
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