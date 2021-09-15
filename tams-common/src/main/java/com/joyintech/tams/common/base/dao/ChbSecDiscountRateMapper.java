package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSecDiscountRate;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSecDiscountRateMapper {
    int deleteByPrimaryKey(String datauuid);

    int insert(ChbSecDiscountRate record);

    int insertSelective(ChbSecDiscountRate record);

    int insertBasicByBasicInfo(ChbSecDiscountRate record);

    ChbSecDiscountRate selectByPrimaryKey(String datauuid);

    int updateByPrimaryKeySelective(ChbSecDiscountRate record);

    int updateByPrimaryKey(ChbSecDiscountRate record);
}