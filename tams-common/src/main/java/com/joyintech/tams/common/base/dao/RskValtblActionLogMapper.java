package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblActionLog;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblActionLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(RskValtblActionLog record);

    int insertSelective(RskValtblActionLog record);

    int insertBasicByBasicInfo(RskValtblActionLog record);

    RskValtblActionLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RskValtblActionLog record);

    int updateByPrimaryKeyWithBLOBs(RskValtblActionLog record);

    int updateByPrimaryKey(RskValtblActionLog record);
}