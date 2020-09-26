package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFee;
import org.springframework.stereotype.Repository;

@Repository
public interface ValPrdFloatFeeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFloatFee record);

    int insertSelective(ValPrdFloatFee record);

    int insertBasicByBasicInfo(ValPrdFloatFee record);

    ValPrdFloatFee selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFloatFee record);

    int updateByPrimaryKey(ValPrdFloatFee record);
}