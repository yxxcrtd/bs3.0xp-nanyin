package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcMonthPrdPositiondetail;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcMonthPrdPositiondetailMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(PbcMonthPrdPositiondetail record);

    int insertSelective(PbcMonthPrdPositiondetail record);

    int insertBasicByBasicInfo(PbcMonthPrdPositiondetail record);

    PbcMonthPrdPositiondetail selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(PbcMonthPrdPositiondetail record);

    int updateByPrimaryKey(PbcMonthPrdPositiondetail record);
}