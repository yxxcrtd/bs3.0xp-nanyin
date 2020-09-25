package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinRateInfoLevel;
import com.joyintech.tams.common.base.entity.FinRateInfoLevelKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinRateInfoLevelMapper {
    int deleteByPrimaryKey(FinRateInfoLevelKey key);

    int insert(FinRateInfoLevel record);

    int insertSelective(FinRateInfoLevel record);

    int insertBasicByBasicInfo(FinRateInfoLevel record);

    FinRateInfoLevel selectByPrimaryKey(FinRateInfoLevelKey key);

    int updateByPrimaryKeySelective(FinRateInfoLevel record);

    int updateByPrimaryKey(FinRateInfoLevel record);
}