package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import org.springframework.stereotype.Repository;

@Repository
public interface PaTrdFinProductDealTotalMapper {
    int deleteByPrimaryKey(String totleTradeId);

    int insert(PaTrdFinProductDealTotal record);

    int insertSelective(PaTrdFinProductDealTotal record);

    int insertBasicByBasicInfo(PaTrdFinProductDealTotal record);

    PaTrdFinProductDealTotal selectByPrimaryKey(String totleTradeId);

    int updateByPrimaryKeySelective(PaTrdFinProductDealTotal record);

    int updateByPrimaryKey(PaTrdFinProductDealTotal record);
}