package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundChangeDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFundChangeDealMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFundChangeDeal record);

    int insertSelective(ValFundChangeDeal record);

    int insertBasicByBasicInfo(ValFundChangeDeal record);

    ValFundChangeDeal selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFundChangeDeal record);

    int updateByPrimaryKey(ValFundChangeDeal record);
}