package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StMeetingInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StMeetingInfoMapper {
    int deleteByPrimaryKey(String meetingseq);

    int insert(StMeetingInfo record);

    int insertSelective(StMeetingInfo record);

    int insertBasicByBasicInfo(StMeetingInfo record);

    StMeetingInfo selectByPrimaryKey(String meetingseq);

    int updateByPrimaryKeySelective(StMeetingInfo record);

    int updateByPrimaryKey(StMeetingInfo record);
}