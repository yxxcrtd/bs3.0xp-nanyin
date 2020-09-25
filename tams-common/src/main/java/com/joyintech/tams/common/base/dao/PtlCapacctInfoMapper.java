package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlCapacctInfo;
import com.joyintech.tams.common.base.entity.PtlCapacctInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlCapacctInfoMapper {
    int deleteByPrimaryKey(PtlCapacctInfoKey key);

    int insert(PtlCapacctInfo record);

    int insertSelective(PtlCapacctInfo record);

    int insertBasicByBasicInfo(PtlCapacctInfo record);

    PtlCapacctInfo selectByPrimaryKey(PtlCapacctInfoKey key);

    int updateByPrimaryKeySelective(PtlCapacctInfo record);

    int updateByPrimaryKey(PtlCapacctInfo record);
}