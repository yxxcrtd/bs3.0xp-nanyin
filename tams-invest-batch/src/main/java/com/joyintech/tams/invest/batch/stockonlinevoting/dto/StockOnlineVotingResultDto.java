package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.StMeetingInfo;
import com.joyintech.tams.common.base.entity.StMeetingProposalInfo;
import com.joyintech.tams.common.base.entity.StMeetingProposalVoteinfo;

import java.util.List;

/**
 * 股票网络投票实体
 */
public class StockOnlineVotingResultDto extends NeedToWriteEntity {
    /**
     * 股东大会信息
     */
    private List<StMeetingInfo> stMeetingInfoList;

    /**
     * 股东大会议案信息
     */
    private List<StMeetingProposalInfo> stMeetingProposalInfoList;

    /**
     * 股东大会议案投票信息
     */
    private List<StMeetingProposalVoteinfo> stMeetingProposalVoteinfoList;

    public List<StMeetingInfo> getStMeetingInfoList() {
        return stMeetingInfoList;
    }

    public void setStMeetingInfoList(List<StMeetingInfo> stMeetingInfoList) {
        this.stMeetingInfoList = stMeetingInfoList;
    }

    public List<StMeetingProposalInfo> getStMeetingProposalInfoList() {
        return stMeetingProposalInfoList;
    }

    public void setStMeetingProposalInfoList(List<StMeetingProposalInfo> stMeetingProposalInfoList) {
        this.stMeetingProposalInfoList = stMeetingProposalInfoList;
    }

    public List<StMeetingProposalVoteinfo> getStMeetingProposalVoteinfoList() {
        return stMeetingProposalVoteinfoList;
    }

    public void setStMeetingProposalVoteinfoList(List<StMeetingProposalVoteinfo> stMeetingProposalVoteinfoList) {
        this.stMeetingProposalVoteinfoList = stMeetingProposalVoteinfoList;
    }
}
