package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CreditAssetpool extends BaseEntity {
    /**
     * 资产系列代码
     */
    private String seqno;

    /**
     * 资产系列名称
     */
    private String assetpoolName;

    /**
     * 机构代码
     */
    private String institutionCode;

    /**
     * 取得 资产系列代码
     * @return 资产系列代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 资产系列代码
     * @param seqno 资产系列代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 资产系列名称
     * @return 资产系列名称
     */
    public String getAssetpoolName() {
        return assetpoolName;
    }

    /**
     * 设置 资产系列名称
     * @param assetpoolName 资产系列名称
     */
    public void setAssetpoolName(String assetpoolName) {
        this.assetpoolName = assetpoolName == null ? null : assetpoolName.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 机构代码
     * @param institutionCode 机构代码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }
}