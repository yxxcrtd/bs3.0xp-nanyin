package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpPath;

@Repository
public interface RepFtpPathMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepFtpPath record);

    int insertSelective(RepFtpPath record);

    int insertBasicByBasicInfo(RepFtpPath record);

    RepFtpPath selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepFtpPath record);

    int updateByPrimaryKey(RepFtpPath record);
}