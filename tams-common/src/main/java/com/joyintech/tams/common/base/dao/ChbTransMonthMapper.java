package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbTransMonth;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbTransMonthMapper {
    int deleteByPrimaryKey(String reportuuid);

    int insert(ChbTransMonth record);

    int insertSelective(ChbTransMonth record);

    int insertBasicByBasicInfo(ChbTransMonth record);

    ChbTransMonth selectByPrimaryKey(String reportuuid);

    int updateByPrimaryKeySelective(ChbTransMonth record);

    int updateByPrimaryKey(ChbTransMonth record);
}