package com.joyintech.tams.report.system.prorate.dao;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
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
public interface ProRateInfo001Mapper {

    List<DisRepCashYield> queryForPage(Map<String, Object> param);

    List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param);

    List<SysDataDict> getSysDataDict(Map<String, Object> param);

    List<Integer> selectPrdFeeData(@Param("dtoList") List<DisRepCashYield> dtoList);

    void insertPrdFeeData(@Param("dtoList") List<DisRepCashYield> dtoList);

    void updateDisRepCashYield(@Param("dtoList") List<DisRepCashYield> dtoList);

    void deleteDisRepCashYieldErrorData(@Param("dtoList") List<DisRepCashYield> dtoList);

    List<DisRepCashYield> queryReportList(Map<String, Object> param);

    List<DisRepCashYield> selectOneDate(Map<String, Object> param);

    List<DisPrdFeeRateInfoErrodataWithBLOBs> selectdisPrdFeeRateErrordata(@Param("datauuid")String datauuid);

    void insertOne(DisRepCashYield entity);

    void updateOne(Map<String, Object> mapOld);

    void deleteOne(@Param("busiId")String busiId, @Param("date")Date date, @Param("cashType2")String cashType2);

    void deleteErrorDataOne(@Param("busiId")String busiId, @Param("date")Date date, @Param("cashType2")String cashType2);

}
