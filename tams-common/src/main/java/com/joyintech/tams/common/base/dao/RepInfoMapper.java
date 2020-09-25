package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInfo;

@Repository
public interface RepInfoMapper {
    int deleteByPrimaryKey(String repId);

    int insert(RepInfo record);

    int insertSelective(RepInfo record);

    int insertBasicByBasicInfo(RepInfo record);

    RepInfo selectByPrimaryKey(String repId);

    int updateByPrimaryKeySelective(RepInfo record);

    int updateByPrimaryKey(RepInfo record);
}