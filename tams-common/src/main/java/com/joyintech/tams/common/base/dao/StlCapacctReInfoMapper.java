package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlCapacctReInfo;
import com.joyintech.tams.common.base.entity.StlCapacctReInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StlCapacctReInfoMapper {
    int deleteByPrimaryKey(StlCapacctReInfoKey key);

    int insert(StlCapacctReInfo record);

    int insertSelective(StlCapacctReInfo record);

    int insertBasicByBasicInfo(StlCapacctReInfo record);

    StlCapacctReInfo selectByPrimaryKey(StlCapacctReInfoKey key);

    int updateByPrimaryKeySelective(StlCapacctReInfo record);

    int updateByPrimaryKey(StlCapacctReInfo record);
}