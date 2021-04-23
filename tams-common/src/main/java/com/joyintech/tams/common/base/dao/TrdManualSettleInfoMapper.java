package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdManualSettleInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdManualSettleInfoMapper {
    int deleteByPrimaryKey(String settleSeqNo);

    int insert(TrdManualSettleInfo record);

    int insertSelective(TrdManualSettleInfo record);

    int insertBasicByBasicInfo(TrdManualSettleInfo record);

    TrdManualSettleInfo selectByPrimaryKey(String settleSeqNo);

    int updateByPrimaryKeySelective(TrdManualSettleInfo record);

    int updateByPrimaryKey(TrdManualSettleInfo record);
}