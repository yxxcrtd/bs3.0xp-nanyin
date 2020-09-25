package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepValuation;
import com.joyintech.tams.common.base.entity.RepValuationKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepValuationMapper {
    int deleteByPrimaryKey(RepValuationKey key);

    int insert(RepValuation record);

    int insertSelective(RepValuation record);

    int insertBasicByBasicInfo(RepValuation record);

    RepValuation selectByPrimaryKey(RepValuationKey key);

    int updateByPrimaryKeySelective(RepValuation record);

    int updateByPrimaryKey(RepValuation record);
}