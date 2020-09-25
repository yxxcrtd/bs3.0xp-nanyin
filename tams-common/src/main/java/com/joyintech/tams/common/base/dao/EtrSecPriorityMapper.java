package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrSecPriority;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrSecPriorityMapper {
    int deleteByPrimaryKey(String configId);

    int insert(EtrSecPriority record);

    int insertSelective(EtrSecPriority record);

    int insertBasicByBasicInfo(EtrSecPriority record);

    EtrSecPriority selectByPrimaryKey(String configId);

    int updateByPrimaryKeySelective(EtrSecPriority record);

    int updateByPrimaryKey(EtrSecPriority record);
}