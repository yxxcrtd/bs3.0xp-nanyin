package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdManualSettleFlow;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdManualSettleFlowMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdManualSettleFlow record);

    int insertSelective(TrdManualSettleFlow record);

    int insertBasicByBasicInfo(TrdManualSettleFlow record);

    TrdManualSettleFlow selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdManualSettleFlow record);

    int updateByPrimaryKey(TrdManualSettleFlow record);
}