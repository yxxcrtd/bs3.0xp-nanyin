package com.joyintech.tams.common.base.dao;


import com.joyintech.tams.common.base.entity.SysActivereminderInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface SysActivereminderInfoMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SysActivereminderInfo record);

    int insertSelective(SysActivereminderInfo record);

    int insertBasicByBasicInfo(SysActivereminderInfo record);

    SysActivereminderInfo selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SysActivereminderInfo record);

    int updateByPrimaryKey(SysActivereminderInfo record);
}