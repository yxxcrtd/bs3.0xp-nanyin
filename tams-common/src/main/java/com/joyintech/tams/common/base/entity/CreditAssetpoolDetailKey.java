package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CreditAssetpoolDetailKey extends BaseEntity {
    /**
     * 资产系列代码
     */
    private String seqno;

    /**
     * 金融产品代码
     */
    private String finprodId;

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
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }
}