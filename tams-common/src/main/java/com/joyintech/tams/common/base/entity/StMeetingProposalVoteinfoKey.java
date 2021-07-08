package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StMeetingProposalVoteinfoKey extends BaseEntity {
    /**
     * 股东大会编号
     */
    private String meetingseq;

    /**
     * 议案组编号
     */
    private String proposalGroupId;

    /**
     * 投票代码
     */
    private String vcode;

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
     * 取得 议案组编号
     * @return 议案组编号
     */
    public String getProposalGroupId() {
        return proposalGroupId;
    }

    /**
     * 设置 议案组编号
     * @param proposalGroupId 议案组编号
     */
    public void setProposalGroupId(String proposalGroupId) {
        this.proposalGroupId = proposalGroupId == null ? null : proposalGroupId.trim();
    }

    /**
     * 取得 投票代码
     * @return 投票代码
     */
    public String getVcode() {
        return vcode;
    }

    /**
     * 设置 投票代码
     * @param vcode 投票代码
     */
    public void setVcode(String vcode) {
        this.vcode = vcode == null ? null : vcode.trim();
    }
}