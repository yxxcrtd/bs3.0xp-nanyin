package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfOpicsTradSecdeal;
import com.joyintech.tams.common.base.entity.InterfOpicsTradSecdealKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfOpicsTradSecdealMapper {
    int deleteByPrimaryKey(InterfOpicsTradSecdealKey key);

    int insert(InterfOpicsTradSecdeal record);

    int insertSelective(InterfOpicsTradSecdeal record);

    int insertBasicByBasicInfo(InterfOpicsTradSecdeal record);

    InterfOpicsTradSecdeal selectByPrimaryKey(InterfOpicsTradSecdealKey key);

    int updateByPrimaryKeySelective(InterfOpicsTradSecdeal record);

    int updateByPrimaryKey(InterfOpicsTradSecdeal record);
}