package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdBenchmarkDetailN;

@Repository
public interface ValPrdBenchmarkDetailNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdBenchmarkDetailN record);

    int insertSelective(ValPrdBenchmarkDetailN record);

    int insertBasicByBasicInfo(ValPrdBenchmarkDetailN record);

    ValPrdBenchmarkDetailN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdBenchmarkDetailN record);

    int updateByPrimaryKey(ValPrdBenchmarkDetailN record);
}