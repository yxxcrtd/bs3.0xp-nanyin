package com.joyintech.tams.common.svreport.datacheck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.svreport.datacheck.dto.ChbInterfErrodata;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbInterfErrodataWithBLOBs;

public interface ChbInterfErrodataMapper {
    int deleteByPrimaryKey(String dataid);

    int insert(ChbInterfErrodataWithBLOBs record);

    int insertSelective(ChbInterfErrodataWithBLOBs record);

    ChbInterfErrodataWithBLOBs selectByPrimaryKey(String dataid);

    int updateByPrimaryKeySelective(ChbInterfErrodataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbInterfErrodataWithBLOBs record);

    int updateByPrimaryKey(ChbInterfErrodata record);
    int insertChbInterfErrodata(@Param("newDataList")List<ChbInterfErrodataWithBLOBs> newDataList);
}