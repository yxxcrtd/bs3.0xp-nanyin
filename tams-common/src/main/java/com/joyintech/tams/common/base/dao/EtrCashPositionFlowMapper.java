package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrCashPositionFlow;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrCashPositionFlowMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(EtrCashPositionFlow record);

    int insertSelective(EtrCashPositionFlow record);

    int insertBasicByBasicInfo(EtrCashPositionFlow record);

    EtrCashPositionFlow selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(EtrCashPositionFlow record);

    int updateByPrimaryKey(EtrCashPositionFlow record);
}