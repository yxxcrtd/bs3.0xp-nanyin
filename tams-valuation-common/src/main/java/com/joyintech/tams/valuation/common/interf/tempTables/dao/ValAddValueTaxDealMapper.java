package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAddValueTaxDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ValAddValueTaxDealMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValAddValueTaxDeal record);

    int insertSelective(ValAddValueTaxDeal record);

    int insertBasicByBasicInfo(ValAddValueTaxDeal record);

    ValAddValueTaxDeal selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValAddValueTaxDeal record);

    int updateByPrimaryKey(ValAddValueTaxDeal record);
}