package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpDetailMsg;

@Repository
public interface RepFtpDetailMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepFtpDetailMsg record);

    int insertSelective(RepFtpDetailMsg record);

    int insertBasicByBasicInfo(RepFtpDetailMsg record);

    RepFtpDetailMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepFtpDetailMsg record);

    int updateByPrimaryKey(RepFtpDetailMsg record);
}