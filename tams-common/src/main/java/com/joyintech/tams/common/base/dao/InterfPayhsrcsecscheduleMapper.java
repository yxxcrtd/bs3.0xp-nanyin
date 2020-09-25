package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfPayhsrcsecschedule;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecscheduleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfPayhsrcsecscheduleMapper {
    int deleteByPrimaryKey(InterfPayhsrcsecscheduleKey key);

    int insert(InterfPayhsrcsecschedule record);

    int insertSelective(InterfPayhsrcsecschedule record);

    int insertBasicByBasicInfo(InterfPayhsrcsecschedule record);

    InterfPayhsrcsecschedule selectByPrimaryKey(InterfPayhsrcsecscheduleKey key);

    int updateByPrimaryKeySelective(InterfPayhsrcsecschedule record);

    int updateByPrimaryKey(InterfPayhsrcsecschedule record);
}