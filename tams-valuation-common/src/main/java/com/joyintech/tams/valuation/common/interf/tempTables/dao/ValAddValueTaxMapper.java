package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAddValueTax;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAddValueTaxKey;

@Repository
public interface ValAddValueTaxMapper {
    int deleteByPrimaryKey(ValAddValueTaxKey key);

    int insert(ValAddValueTax record);

    int insertSelective(ValAddValueTax record);

    int insertBasicByBasicInfo(ValAddValueTax record);

    ValAddValueTax selectByPrimaryKey(ValAddValueTaxKey key);

    int updateByPrimaryKeySelective(ValAddValueTax record);

    int updateByPrimaryKey(ValAddValueTax record);
}