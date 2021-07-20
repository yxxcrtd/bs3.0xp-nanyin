package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StMeetingProposalInfoKey extends BaseEntity {
    /**
     * 股东大会编号
     */
    private String meetingseq;

    /**
     * 议案编号
     */
    private String proposalid;

    /**
     * 取得 股东大会编号
     * @return 股东大会编号
     */
    public String getMeetingseq() {
        return meetingseq;
    }

    /**
     * 设置 股东大会编号
     * @param meetingseq 股东大会编号
     */
    public void setMeetingseq(String meetingseq) {
        this.meetingseq = meetingseq == null ? null : meetingseq.trim();
    }

    /**
     * 取得 议案编号
     * @return 议案编号
     */
    public String getProposalid() {
        return proposalid;
    }

    /**
     * 设置 议案编号
     * @param proposalid 议案编号
     */
    public void setProposalid(String proposalid) {
        this.proposalid = proposalid == null ? null : proposalid.trim();
    }
}