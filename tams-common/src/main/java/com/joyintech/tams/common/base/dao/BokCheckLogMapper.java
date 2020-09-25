package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokCheckLog;
import org.springframework.stereotype.Repository;

@Repository
public interface BokCheckLogMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(BokCheckLog record);

    int insertSelective(BokCheckLog record);

    int insertBasicByBasicInfo(BokCheckLog record);

    BokCheckLog selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(BokCheckLog record);

    int updateByPrimaryKeyWithBLOBs(BokCheckLog record);

    int updateByPrimaryKey(BokCheckLog record);
}