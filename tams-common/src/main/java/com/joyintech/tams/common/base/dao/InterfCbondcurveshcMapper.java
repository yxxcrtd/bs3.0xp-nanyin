package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCbondcurveshc;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCbondcurveshcMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCbondcurveshc record);

    int insertSelective(InterfCbondcurveshc record);

    int insertBasicByBasicInfo(InterfCbondcurveshc record);

    InterfCbondcurveshc selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCbondcurveshc record);

    int updateByPrimaryKey(InterfCbondcurveshc record);
}