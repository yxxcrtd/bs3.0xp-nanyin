package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CostPositionDb;
import com.joyintech.tams.common.base.entity.CostPositionDbKey;
import org.springframework.stereotype.Repository;

@Repository
public interface CostPositionDbMapper {
    int deleteByPrimaryKey(CostPositionDbKey key);

    int insert(CostPositionDb record);

    int insertSelective(CostPositionDb record);

    int insertBasicByBasicInfo(CostPositionDb record);

    CostPositionDb selectByPrimaryKey(CostPositionDbKey key);

    int updateByPrimaryKeySelective(CostPositionDb record);

    int updateByPrimaryKey(CostPositionDb record);
}