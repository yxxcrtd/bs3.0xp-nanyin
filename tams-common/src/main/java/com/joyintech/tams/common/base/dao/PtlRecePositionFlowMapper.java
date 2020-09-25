package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlRecePositionFlow;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlRecePositionFlowMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(PtlRecePositionFlow record);

    int insertSelective(PtlRecePositionFlow record);

    int insertBasicByBasicInfo(PtlRecePositionFlow record);

    PtlRecePositionFlow selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(PtlRecePositionFlow record);

    int updateByPrimaryKey(PtlRecePositionFlow record);
}