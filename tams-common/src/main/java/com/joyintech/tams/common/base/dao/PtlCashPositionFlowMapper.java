package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlCashPositionFlow;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlCashPositionFlowMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(PtlCashPositionFlow record);

    int insertSelective(PtlCashPositionFlow record);

    int insertBasicByBasicInfo(PtlCashPositionFlow record);

    PtlCashPositionFlow selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(PtlCashPositionFlow record);

    int updateByPrimaryKey(PtlCashPositionFlow record);
}