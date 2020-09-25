package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.common.base.entity.FinTradeProductAddKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinTradeProductAddMapper {
    int deleteByPrimaryKey(FinTradeProductAddKey key);

    int insert(FinTradeProductAdd record);

    int insertSelective(FinTradeProductAdd record);

    int insertBasicByBasicInfo(FinTradeProductAdd record);

    FinTradeProductAdd selectByPrimaryKey(FinTradeProductAddKey key);

    int updateByPrimaryKeySelective(FinTradeProductAdd record);

    int updateByPrimaryKey(FinTradeProductAdd record);
}