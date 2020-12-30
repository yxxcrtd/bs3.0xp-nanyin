package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CustomConditionSchemeDetailKey extends BaseEntity {
    /**
     * 宽表配置方案表主键
     */
    private String pSeqno;

    /**
     * 自定义条件节点id
     */
    private String repId;

    /**
     * 取得 宽表配置方案表主键
     * @return 宽表配置方案表主键
     */
    public String getpSeqno() {
        return pSeqno;
    }

    /**
     * 设置 宽表配置方案表主键
     * @param pSeqno 宽表配置方案表主键
     */
    public void setpSeqno(String pSeqno) {
        this.pSeqno = pSeqno == null ? null : pSeqno.trim();
    }

    /**
     * 取得 自定义条件节点id
     * @return 自定义条件节点id
     */
    public String getRepId() {
        return repId;
    }

    /**
     * 设置 自定义条件节点id
     * @param repId 自定义条件节点id
     */
    public void setRepId(String repId) {
        this.repId = repId == null ? null : repId.trim();
    }
}