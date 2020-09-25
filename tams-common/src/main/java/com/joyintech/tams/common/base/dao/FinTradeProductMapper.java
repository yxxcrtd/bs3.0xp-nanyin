package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinTradeProductMapper {
    int deleteByPrimaryKey(FinTradeProductKey key);

    int insert(FinTradeProduct record);

    int insertSelective(FinTradeProduct record);

    int insertBasicByBasicInfo(FinTradeProduct record);

    FinTradeProduct selectByPrimaryKey(FinTradeProductKey key);

    int updateByPrimaryKeySelective(FinTradeProduct record);

    int updateByPrimaryKey(FinTradeProduct record);
}