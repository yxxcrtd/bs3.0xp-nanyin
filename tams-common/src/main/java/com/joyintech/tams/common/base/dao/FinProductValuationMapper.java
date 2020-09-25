package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.FinProductValuationKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductValuationMapper {
    int deleteByPrimaryKey(FinProductValuationKey key);

    int insert(FinProductValuation record);

    int insertSelective(FinProductValuation record);

    int insertBasicByBasicInfo(FinProductValuation record);

    FinProductValuation selectByPrimaryKey(FinProductValuationKey key);

    int updateByPrimaryKeySelective(FinProductValuation record);

    int updateByPrimaryKey(FinProductValuation record);
}