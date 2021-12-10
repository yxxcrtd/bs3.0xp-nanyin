package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RegFinProductDealAdd;
import org.springframework.stereotype.Repository;

@Repository
public interface RegFinProductDealAddMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(RegFinProductDealAdd record);

    int insertSelective(RegFinProductDealAdd record);

    int insertBasicByBasicInfo(RegFinProductDealAdd record);

    RegFinProductDealAdd selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(RegFinProductDealAdd record);

    int updateByPrimaryKey(RegFinProductDealAdd record);
}