package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlSecRecon;
import org.springframework.stereotype.Repository;

@Repository
public interface StlSecReconMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(StlSecRecon record);

    int insertSelective(StlSecRecon record);

    int insertBasicByBasicInfo(StlSecRecon record);

    StlSecRecon selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(StlSecRecon record);

    int updateByPrimaryKeyWithBLOBs(StlSecRecon record);

    int updateByPrimaryKey(StlSecRecon record);
}