package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundShareConvert;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFundShareConvertMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFundShareConvert record);

    int insertSelective(ValFundShareConvert record);

    int insertBasicByBasicInfo(ValFundShareConvert record);

    ValFundShareConvert selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFundShareConvert record);

    int updateByPrimaryKey(ValFundShareConvert record);
}