package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMonthPrdPosition;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMonthPrdPositionMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(ChbMonthPrdPosition record);

    int insertSelective(ChbMonthPrdPosition record);

    int insertBasicByBasicInfo(ChbMonthPrdPosition record);

    ChbMonthPrdPosition selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(ChbMonthPrdPosition record);

    int updateByPrimaryKey(ChbMonthPrdPosition record);
}