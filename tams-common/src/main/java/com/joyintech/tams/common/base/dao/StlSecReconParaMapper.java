package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlSecReconPara;
import org.springframework.stereotype.Repository;

@Repository
public interface StlSecReconParaMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(StlSecReconPara record);

    int insertSelective(StlSecReconPara record);

    int insertBasicByBasicInfo(StlSecReconPara record);

    StlSecReconPara selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(StlSecReconPara record);

    int updateByPrimaryKey(StlSecReconPara record);
}