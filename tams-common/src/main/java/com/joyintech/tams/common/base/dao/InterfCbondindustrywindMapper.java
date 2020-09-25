package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCbondindustrywind;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCbondindustrywindMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCbondindustrywind record);

    int insertSelective(InterfCbondindustrywind record);

    int insertBasicByBasicInfo(InterfCbondindustrywind record);

    InterfCbondindustrywind selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCbondindustrywind record);

    int updateByPrimaryKey(InterfCbondindustrywind record);
}