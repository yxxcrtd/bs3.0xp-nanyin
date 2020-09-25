package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlOrdersExt;
import org.springframework.stereotype.Repository;

@Repository
public interface StlOrdersExtMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(StlOrdersExt record);

    int insertSelective(StlOrdersExt record);

    int insertBasicByBasicInfo(StlOrdersExt record);

    StlOrdersExt selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(StlOrdersExt record);

    int updateByPrimaryKey(StlOrdersExt record);
}