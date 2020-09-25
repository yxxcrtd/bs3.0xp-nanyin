package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfPayhsrcbondencashyield;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfPayhsrcbondencashyieldMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfPayhsrcbondencashyield record);

    int insertSelective(InterfPayhsrcbondencashyield record);

    int insertBasicByBasicInfo(InterfPayhsrcbondencashyield record);

    InterfPayhsrcbondencashyield selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfPayhsrcbondencashyield record);

    int updateByPrimaryKey(InterfPayhsrcbondencashyield record);
}