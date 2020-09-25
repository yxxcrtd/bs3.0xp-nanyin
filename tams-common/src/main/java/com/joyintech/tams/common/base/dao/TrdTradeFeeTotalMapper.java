package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdTradeFeeTotal;
import com.joyintech.tams.common.base.entity.TrdTradeFeeTotalKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdTradeFeeTotalMapper {
    int deleteByPrimaryKey(TrdTradeFeeTotalKey key);

    int insert(TrdTradeFeeTotal record);

    int insertSelective(TrdTradeFeeTotal record);

    int insertBasicByBasicInfo(TrdTradeFeeTotal record);

    TrdTradeFeeTotal selectByPrimaryKey(TrdTradeFeeTotalKey key);

    int updateByPrimaryKeySelective(TrdTradeFeeTotal record);

    int updateByPrimaryKey(TrdTradeFeeTotal record);
}