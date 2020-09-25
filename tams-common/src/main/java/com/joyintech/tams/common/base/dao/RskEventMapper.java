package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface RskEventMapper {
    int deleteByPrimaryKey(String eventCode);

    int insert(RskEvent record);

    int insertSelective(RskEvent record);

    int insertBasicByBasicInfo(RskEvent record);

    RskEvent selectByPrimaryKey(String eventCode);

    int updateByPrimaryKeySelective(RskEvent record);

    int updateByPrimaryKey(RskEvent record);
}