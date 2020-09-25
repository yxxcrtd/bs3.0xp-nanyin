package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StlCapacctInfoMapper {
    int deleteByPrimaryKey(StlCapacctInfoKey key);

    int insert(StlCapacctInfo record);

    int insertSelective(StlCapacctInfo record);

    int insertBasicByBasicInfo(StlCapacctInfo record);

    StlCapacctInfo selectByPrimaryKey(StlCapacctInfoKey key);

    int updateByPrimaryKeySelective(StlCapacctInfo record);

    int updateByPrimaryKey(StlCapacctInfo record);
}