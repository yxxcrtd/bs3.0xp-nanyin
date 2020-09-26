package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValDlDeal;

@Repository
public interface ValDlDealMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValDlDeal record);

    int insertSelective(ValDlDeal record);

    int insertBasicByBasicInfo(ValDlDeal record);

    ValDlDeal selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValDlDeal record);

    int updateByPrimaryKey(ValDlDeal record);
}