package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfHttpLog;
import com.joyintech.tams.common.base.entity.InterfHttpLogWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfHttpLogMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(InterfHttpLogWithBLOBs record);

    int insertSelective(InterfHttpLogWithBLOBs record);

    int insertBasicByBasicInfo(InterfHttpLogWithBLOBs record);

    InterfHttpLogWithBLOBs selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(InterfHttpLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InterfHttpLogWithBLOBs record);

    int updateByPrimaryKey(InterfHttpLog record);
}