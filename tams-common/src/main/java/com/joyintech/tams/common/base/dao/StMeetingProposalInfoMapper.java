package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StMeetingProposalInfo;
import com.joyintech.tams.common.base.entity.StMeetingProposalInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StMeetingProposalInfoMapper {
    int deleteByPrimaryKey(StMeetingProposalInfoKey key);

    int insert(StMeetingProposalInfo record);

    int insertSelective(StMeetingProposalInfo record);

    int insertBasicByBasicInfo(StMeetingProposalInfo record);

    StMeetingProposalInfo selectByPrimaryKey(StMeetingProposalInfoKey key);

    int updateByPrimaryKeySelective(StMeetingProposalInfo record);

    int updateByPrimaryKey(StMeetingProposalInfo record);
}