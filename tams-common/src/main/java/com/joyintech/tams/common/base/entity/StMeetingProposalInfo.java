package com.joyintech.tams.common.base.entity;

public class StMeetingProposalInfo extends StMeetingProposalInfoKey {
    /**
     * 议案组编号
     */
    private String proposalGroupId;

    /**
     * 议案名称
     */
    private String proposalname;

    /**
     * 议案类型，普通投票，累积投票
     */
    private String vtype;

    /**
     * 累计投票应选数
     */
    private Long electionnum;

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
     * 取得 议案名称
     * @return 议案名称
     */
    public String getProposalname() {
        return proposalname;
    }

    /**
     * 设置 议案名称
     * @param proposalname 议案名称
     */
    public void setProposalname(String proposalname) {
        this.proposalname = proposalname == null ? null : proposalname.trim();
    }

    /**
     * 取得 议案类型，普通投票，累积投票
     * @return 议案类型，普通投票，累积投票
     */
    public String getVtype() {
        return vtype;
    }

    /**
     * 设置 议案类型，普通投票，累积投票
     * @param vtype 议案类型，普通投票，累积投票
     */
    public void setVtype(String vtype) {
        this.vtype = vtype == null ? null : vtype.trim();
    }

    /**
     * 取得 累计投票应选数
     * @return 累计投票应选数
     */
    public Long getElectionnum() {
        return electionnum;
    }

    /**
     * 设置 累计投票应选数
     * @param electionnum 累计投票应选数
     */
    public void setElectionnum(Long electionnum) {
        this.electionnum = electionnum;
    }
}