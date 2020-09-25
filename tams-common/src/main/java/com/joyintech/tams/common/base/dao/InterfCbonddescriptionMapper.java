package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCbonddescription;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCbonddescriptionMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCbonddescription record);

    int insertSelective(InterfCbonddescription record);

    int insertBasicByBasicInfo(InterfCbonddescription record);

    InterfCbonddescription selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCbonddescription record);

    int updateByPrimaryKey(InterfCbonddescription record);
}