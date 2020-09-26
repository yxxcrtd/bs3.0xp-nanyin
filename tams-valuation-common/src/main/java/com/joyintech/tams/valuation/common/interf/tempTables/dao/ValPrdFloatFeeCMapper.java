package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFeeC;
import org.springframework.stereotype.Repository;

@Repository
public interface ValPrdFloatFeeCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFloatFeeC record);

    int insertSelective(ValPrdFloatFeeC record);

    int insertBasicByBasicInfo(ValPrdFloatFeeC record);

    ValPrdFloatFeeC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFloatFeeC record);

    int updateByPrimaryKey(ValPrdFloatFeeC record);
}