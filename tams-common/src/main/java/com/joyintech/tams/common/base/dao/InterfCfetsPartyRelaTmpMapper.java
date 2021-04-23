package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCfetsPartyRelaTmp;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCfetsPartyRelaTmpMapper {
    int deleteByPrimaryKey(String relaId);

    int insert(InterfCfetsPartyRelaTmp record);

    int insertSelective(InterfCfetsPartyRelaTmp record);

    int insertBasicByBasicInfo(InterfCfetsPartyRelaTmp record);

    InterfCfetsPartyRelaTmp selectByPrimaryKey(String relaId);

    int updateByPrimaryKeySelective(InterfCfetsPartyRelaTmp record);

    int updateByPrimaryKey(InterfCfetsPartyRelaTmp record);
}