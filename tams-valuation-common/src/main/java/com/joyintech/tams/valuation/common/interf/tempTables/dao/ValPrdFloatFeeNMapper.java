package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFeeN;
import org.springframework.stereotype.Repository;

@Repository
public interface ValPrdFloatFeeNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFloatFeeN record);

    int insertSelective(ValPrdFloatFeeN record);

    int insertBasicByBasicInfo(ValPrdFloatFeeN record);

    ValPrdFloatFeeN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFloatFeeN record);

    int updateByPrimaryKey(ValPrdFloatFeeN record);
}