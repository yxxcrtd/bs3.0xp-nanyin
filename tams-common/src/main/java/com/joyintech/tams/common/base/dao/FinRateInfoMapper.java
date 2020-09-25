package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.FinRateInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinRateInfoMapper {
    int deleteByPrimaryKey(FinRateInfoKey key);

    int insert(FinRateInfo record);

    int insertSelective(FinRateInfo record);

    int insertBasicByBasicInfo(FinRateInfo record);

    FinRateInfo selectByPrimaryKey(FinRateInfoKey key);

    int updateByPrimaryKeySelective(FinRateInfo record);

    int updateByPrimaryKey(FinRateInfo record);
}