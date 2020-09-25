package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdIssuStrategyDetail;
import com.joyintech.tams.common.base.entity.PrdIssuStrategyDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdIssuStrategyDetailMapper {
    int deleteByPrimaryKey(PrdIssuStrategyDetailKey key);

    int insert(PrdIssuStrategyDetail record);

    int insertSelective(PrdIssuStrategyDetail record);

    int insertBasicByBasicInfo(PrdIssuStrategyDetail record);

    PrdIssuStrategyDetail selectByPrimaryKey(PrdIssuStrategyDetailKey key);

    int updateByPrimaryKeySelective(PrdIssuStrategyDetail record);

    int updateByPrimaryKey(PrdIssuStrategyDetail record);
}