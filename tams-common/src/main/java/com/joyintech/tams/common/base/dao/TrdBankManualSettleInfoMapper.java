package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdBankManualSettleInfo;
import org.springframework.stereotype.Repository;

public interface TrdBankManualSettleInfoMapper {
    int deleteByPrimaryKey(String settleSeqNo);

    int insert(TrdBankManualSettleInfo record);

    int insertSelective(TrdBankManualSettleInfo record);

    TrdBankManualSettleInfo selectByPrimaryKey(String settleSeqNo);

    int updateByPrimaryKeySelective(TrdBankManualSettleInfo record);

    int updateByPrimaryKey(TrdBankManualSettleInfo record);
}