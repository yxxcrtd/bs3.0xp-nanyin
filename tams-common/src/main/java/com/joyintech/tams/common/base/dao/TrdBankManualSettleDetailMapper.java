package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdBankManualSettleDetail;

public interface TrdBankManualSettleDetailMapper {
    int insert(TrdBankManualSettleDetail record);

    int insertSelective(TrdBankManualSettleDetail record);
}