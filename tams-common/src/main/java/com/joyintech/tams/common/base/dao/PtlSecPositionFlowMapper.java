package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlSecPositionFlowMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(PtlSecPositionFlow record);

    int insertSelective(PtlSecPositionFlow record);

    int insertBasicByBasicInfo(PtlSecPositionFlow record);

    PtlSecPositionFlow selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(PtlSecPositionFlow record);

    int updateByPrimaryKey(PtlSecPositionFlow record);
}