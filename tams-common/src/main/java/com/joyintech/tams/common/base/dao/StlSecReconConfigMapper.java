package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlSecReconConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface StlSecReconConfigMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(StlSecReconConfig record);

    int insertSelective(StlSecReconConfig record);

    int insertBasicByBasicInfo(StlSecReconConfig record);

    StlSecReconConfig selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(StlSecReconConfig record);

    int updateByPrimaryKey(StlSecReconConfig record);
}