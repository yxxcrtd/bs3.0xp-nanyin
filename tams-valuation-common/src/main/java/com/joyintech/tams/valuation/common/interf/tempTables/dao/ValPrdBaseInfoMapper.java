package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdBaseInfo;

@Repository
public interface ValPrdBaseInfoMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdBaseInfo record);

    int insertSelective(ValPrdBaseInfo record);

    int insertBasicByBasicInfo(ValPrdBaseInfo record);

    ValPrdBaseInfo selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdBaseInfo record);

    int updateByPrimaryKey(ValPrdBaseInfo record);
}