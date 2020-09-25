package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfFileLog;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfFileLogMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(InterfFileLog record);

    int insertSelective(InterfFileLog record);

    int insertBasicByBasicInfo(InterfFileLog record);

    InterfFileLog selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(InterfFileLog record);

    int updateByPrimaryKey(InterfFileLog record);
}