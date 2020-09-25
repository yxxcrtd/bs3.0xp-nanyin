package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCbondspecialconditions;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCbondspecialconditionsMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCbondspecialconditions record);

    int insertSelective(InterfCbondspecialconditions record);

    int insertBasicByBasicInfo(InterfCbondspecialconditions record);

    InterfCbondspecialconditions selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCbondspecialconditions record);

    int updateByPrimaryKey(InterfCbondspecialconditions record);
}