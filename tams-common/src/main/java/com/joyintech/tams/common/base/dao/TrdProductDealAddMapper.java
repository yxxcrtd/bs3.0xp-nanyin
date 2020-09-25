package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdProductDealAddMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(TrdProductDealAdd record);

    int insertSelective(TrdProductDealAdd record);

    int insertBasicByBasicInfo(TrdProductDealAdd record);

    TrdProductDealAdd selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(TrdProductDealAdd record);

    int updateByPrimaryKey(TrdProductDealAdd record);
}