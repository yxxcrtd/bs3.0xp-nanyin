package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMonthPrdPositiondetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMonthPrdPositiondetailMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(ChbMonthPrdPositiondetail record);

    int insertSelective(ChbMonthPrdPositiondetail record);

    int insertBasicByBasicInfo(ChbMonthPrdPositiondetail record);

    ChbMonthPrdPositiondetail selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(ChbMonthPrdPositiondetail record);

    int updateByPrimaryKey(ChbMonthPrdPositiondetail record);
}