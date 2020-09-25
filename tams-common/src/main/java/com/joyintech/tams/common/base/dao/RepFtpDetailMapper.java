package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpDetail;

@Repository
public interface RepFtpDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepFtpDetail record);

    int insertSelective(RepFtpDetail record);

    int insertBasicByBasicInfo(RepFtpDetail record);

    RepFtpDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepFtpDetail record);

    int updateByPrimaryKey(RepFtpDetail record);
}