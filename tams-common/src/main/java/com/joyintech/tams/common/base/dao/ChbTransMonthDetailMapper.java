package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbTransMonthDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbTransMonthDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(ChbTransMonthDetail record);

    int insertSelective(ChbTransMonthDetail record);

    int insertBasicByBasicInfo(ChbTransMonthDetail record);

    ChbTransMonthDetail selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(ChbTransMonthDetail record);

    int updateByPrimaryKey(ChbTransMonthDetail record);
}