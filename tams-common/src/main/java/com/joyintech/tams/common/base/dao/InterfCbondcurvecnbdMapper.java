package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCbondcurvecnbd;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCbondcurvecnbdMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCbondcurvecnbd record);

    int insertSelective(InterfCbondcurvecnbd record);

    int insertBasicByBasicInfo(InterfCbondcurvecnbd record);

    InterfCbondcurvecnbd selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCbondcurvecnbd record);

    int updateByPrimaryKey(InterfCbondcurvecnbd record);
}