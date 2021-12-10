package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RegTradeFeeTotal;
import com.joyintech.tams.common.base.entity.RegTradeFeeTotalKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RegTradeFeeTotalMapper {
    int deleteByPrimaryKey(RegTradeFeeTotalKey key);

    int insert(RegTradeFeeTotal record);

    int insertSelective(RegTradeFeeTotal record);

    int insertBasicByBasicInfo(RegTradeFeeTotal record);

    RegTradeFeeTotal selectByPrimaryKey(RegTradeFeeTotalKey key);

    int updateByPrimaryKeySelective(RegTradeFeeTotal record);

    int updateByPrimaryKey(RegTradeFeeTotal record);
}