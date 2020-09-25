package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfRiskLog;
import com.joyintech.tams.common.base.entity.InterfRiskLogWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfRiskLogMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(InterfRiskLogWithBLOBs record);

    int insertSelective(InterfRiskLogWithBLOBs record);

    int insertBasicByBasicInfo(InterfRiskLogWithBLOBs record);

    InterfRiskLogWithBLOBs selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(InterfRiskLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InterfRiskLogWithBLOBs record);

    int updateByPrimaryKey(InterfRiskLog record);
}