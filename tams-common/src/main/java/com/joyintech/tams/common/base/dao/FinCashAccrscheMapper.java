package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinCashAccrsche;
import com.joyintech.tams.common.base.entity.FinCashAccrscheKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinCashAccrscheMapper {
    int deleteByPrimaryKey(FinCashAccrscheKey key);

    int insert(FinCashAccrsche record);

    int insertSelective(FinCashAccrsche record);

    int insertBasicByBasicInfo(FinCashAccrsche record);

    FinCashAccrsche selectByPrimaryKey(FinCashAccrscheKey key);

    int updateByPrimaryKeySelective(FinCashAccrsche record);

    int updateByPrimaryKey(FinCashAccrsche record);
}