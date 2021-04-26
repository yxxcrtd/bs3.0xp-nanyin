package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleBatchResult;
import com.joyintech.tams.common.base.entity.SettleBatchResultWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleBatchResultMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SettleBatchResultWithBLOBs record);

    int insertSelective(SettleBatchResultWithBLOBs record);

    int insertBasicByBasicInfo(SettleBatchResultWithBLOBs record);

    SettleBatchResultWithBLOBs selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SettleBatchResultWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SettleBatchResultWithBLOBs record);

    int updateByPrimaryKey(SettleBatchResult record);
}