package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleBatchPara;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleBatchParaMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SettleBatchPara record);

    int insertSelective(SettleBatchPara record);

    int insertBasicByBasicInfo(SettleBatchPara record);

    SettleBatchPara selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SettleBatchPara record);

    int updateByPrimaryKey(SettleBatchPara record);
}