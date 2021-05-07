package com.joyintech.tams.report.system.disrelorg.dao;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.svreport.interf.dis.institutiondata.dto.DisAssociatedInstitutions;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DisRelOrgInfo001Mapper {

    List<DisRepCashYield> queryForPage(Map<String, Object> param);

    List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param);

    List<SysDataDict> getSysDataDict(Map<String, Object> param);

    List<Integer> selectPrdFeeData(@Param("dtoList") List<DisRepCashYield> dtoList);

    void insertPrdFeeData(@Param("dtoList") List<DisRepCashYield> dtoList);

    void updateDisAssociatedInstitutions(@Param("dtoList") List<DisAssociatedInstitutions> dtoList);

    List<DisAssociatedInstitutions> queryReportList(Map<String, Object> param);

    List<DisRepCashYield> selectOneDate(Map<String, Object> param);

    List<DisPrdFeeRateInfoErrodataWithBLOBs> selectdisPrdFeeRateErrordata(@Param("datauuid") String datauuid);

    void insertOne(DisAssociatedInstitutions entity);

    List<DisAssociatedInstitutions> selectOne(@Param("relorgId") String relorgId);

    void updateOne(DisAssociatedInstitutions entity);

    void updateOneStatus(@Param("relorgId") String relorgId);
}
