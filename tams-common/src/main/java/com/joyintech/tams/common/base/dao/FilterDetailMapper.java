package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FilterDetail;
import com.joyintech.tams.common.base.entity.FilterDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterDetailMapper {
    int deleteByPrimaryKey(FilterDetailKey key);
    int deleteByFilterCode(FilterDetailKey key);
    int insert(FilterDetail record);

    int insertSelective(FilterDetail record);

    int insertBasicByBasicInfo(FilterDetail record);

    FilterDetail selectByPrimaryKey(FilterDetailKey key);

    int updateByPrimaryKeySelective(FilterDetail record);

    int updateByPrimaryKey(FilterDetail record);
}