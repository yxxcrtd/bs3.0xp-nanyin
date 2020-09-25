package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBooksetCapacctInfoMapper {
    int deleteByPrimaryKey(BokBooksetCapacctInfoKey key);

    int insert(BokBooksetCapacctInfo record);

    int insertSelective(BokBooksetCapacctInfo record);

    int insertBasicByBasicInfo(BokBooksetCapacctInfo record);

    BokBooksetCapacctInfo selectByPrimaryKey(BokBooksetCapacctInfoKey key);

    int updateByPrimaryKeySelective(BokBooksetCapacctInfo record);

    int updateByPrimaryKey(BokBooksetCapacctInfo record);
}