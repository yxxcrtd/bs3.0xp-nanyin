package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskRelatedParty;
import org.springframework.stereotype.Repository;

@Repository
public interface RskRelatedPartyMapper {
    int deleteByPrimaryKey(String instituteCode);

    int insert(RskRelatedParty record);

    int insertSelective(RskRelatedParty record);

    int insertBasicByBasicInfo(RskRelatedParty record);

    RskRelatedParty selectByPrimaryKey(String instituteCode);

    int updateByPrimaryKeySelective(RskRelatedParty record);

    int updateByPrimaryKey(RskRelatedParty record);
}