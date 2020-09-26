package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdBenchmarkDetailC;

@Repository
public interface ValPrdBenchmarkDetailCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdBenchmarkDetailC record);

    int insertSelective(ValPrdBenchmarkDetailC record);

    int insertBasicByBasicInfo(ValPrdBenchmarkDetailC record);

    ValPrdBenchmarkDetailC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdBenchmarkDetailC record);

    int updateByPrimaryKey(ValPrdBenchmarkDetailC record);
}