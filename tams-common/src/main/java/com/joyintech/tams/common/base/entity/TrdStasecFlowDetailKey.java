package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TrdStasecFlowDetailKey extends BaseEntity {
    /**
     * 入出库编号
     */
    private String seqno;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 取得 入出库编号
     * @return 入出库编号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 入出库编号
     * @param seqno 入出库编号
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

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }
}