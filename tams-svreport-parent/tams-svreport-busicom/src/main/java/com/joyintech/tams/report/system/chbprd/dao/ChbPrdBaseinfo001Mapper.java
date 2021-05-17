package com.joyintech.tams.report.system.chbprd.dao;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.system.chbprd.dto.ChbPrdBaseinfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ChbPrdBaseinfo001Mapper {

    List<ChbPrdBaseinfo> queryForPage(Map<String, Object> param);

    List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param);

    List<SysDataDict> getSysDataDict(Map<String, Object> param);

    List<Integer> selectPrdFeeData(@Param("dtoList") List<DisRepCashYield> dtoList);

    void insertPrdFeeData(@Param("dtoList") List<ChbPrdBaseinfo> dtoList);

    void updateChbPrdBaseinfo(@Param("dtoList") List<ChbPrdBaseinfo> dtoList);

    void deleteChbPrdBaseinfoErrorData(@Param("dtoList") List<ChbPrdBaseinfo> dtoList);

    List<ChbPrdBaseinfo> queryReportList(Map<String, Object> param);

    List<ChbPrdBaseinfo> selectOneDate(Map<String, Object> param);

    List<ChbPrdBaseinfoErrodataWithBLOBs> selectchbPrdBaseinfoErrordata(@Param("dataUuid")String dataUuid);

    void insertOne(ChbPrdBaseinfo entity);

    void updateOne(Map<String, Object> mapOld);

    void deleteOne(@Param("prodId")String prodId);

    void deleteErrorDataOne(@Param("prodId")String prodId);

}
