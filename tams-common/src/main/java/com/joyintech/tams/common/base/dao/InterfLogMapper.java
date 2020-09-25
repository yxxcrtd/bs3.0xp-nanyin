package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfLog;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfLogMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(InterfLog record);

    int insertSelective(InterfLog record);

    int insertBasicByBasicInfo(InterfLog record);

    InterfLog selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(InterfLog record);

    int updateByPrimaryKey(InterfLog record);
}