package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblStructMap;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblStructMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(RskValtblStructMap record);

    int insertSelective(RskValtblStructMap record);

    int insertBasicByBasicInfo(RskValtblStructMap record);

    RskValtblStructMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RskValtblStructMap record);

    int updateByPrimaryKey(RskValtblStructMap record);
}