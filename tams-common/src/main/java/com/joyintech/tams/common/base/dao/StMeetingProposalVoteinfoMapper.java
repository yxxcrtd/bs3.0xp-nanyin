package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StMeetingProposalVoteinfo;
import com.joyintech.tams.common.base.entity.StMeetingProposalVoteinfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StMeetingProposalVoteinfoMapper {
    int deleteByPrimaryKey(StMeetingProposalVoteinfoKey key);

    int insert(StMeetingProposalVoteinfo record);

    int insertSelective(StMeetingProposalVoteinfo record);

    int insertBasicByBasicInfo(StMeetingProposalVoteinfo record);

    StMeetingProposalVoteinfo selectByPrimaryKey(StMeetingProposalVoteinfoKey key);

    int updateByPrimaryKeySelective(StMeetingProposalVoteinfo record);

    int updateByPrimaryKey(StMeetingProposalVoteinfo record);
}