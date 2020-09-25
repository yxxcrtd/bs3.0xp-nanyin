package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlActOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface StlActOrderMapper {
    int deleteByPrimaryKey(String actOrderId);

    int insert(StlActOrder record);

    int insertSelective(StlActOrder record);

    int insertBasicByBasicInfo(StlActOrder record);

    StlActOrder selectByPrimaryKey(String actOrderId);

    int updateByPrimaryKeySelective(StlActOrder record);

    int updateByPrimaryKey(StlActOrder record);
}