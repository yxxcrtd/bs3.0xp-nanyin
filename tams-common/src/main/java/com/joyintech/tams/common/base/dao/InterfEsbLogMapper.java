package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfEsbLog;
import com.joyintech.tams.common.base.entity.InterfEsbLogWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfEsbLogMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(InterfEsbLogWithBLOBs record);

    int insertSelective(InterfEsbLogWithBLOBs record);

    int insertBasicByBasicInfo(InterfEsbLogWithBLOBs record);

    InterfEsbLogWithBLOBs selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(InterfEsbLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InterfEsbLogWithBLOBs record);

    int updateByPrimaryKey(InterfEsbLog record);
}