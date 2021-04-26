package com.joyintech.tams.report.system.chbmst.dao;

import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ChbMstCounterParty001Mapper {

    List<ChbMstCounterParty> queryForPage(Map<String, Object> param);

    List<ChbMstCounterParty> getCounterId(Map<String, Object> param);

    void updateChbMstCounterParty(@Param("dtoList") List<ChbMstCounterParty> dtoList);

    void deleteChbMstCounterPartyErrorData(@Param("dtoList") List<ChbMstCounterParty> dtoList);

    List<ChbMstCounterParty> queryReportList(Map<String, Object> param);

    List<ChbMstCounterParty> selectOneDate(Map<String, Object> param);

    void insertOne(ChbMstCounterParty entity);

    void updateOne(ChbMstCounterParty chbMstCounterParty);

    void deleteOne(@Param("counterId")String counterId);

    void deleteErrorDataOne(@Param("counterId")String counterId);



    List<SysDataDict> getSysDataDict(Map<String, Object> param);

    List<Integer> selectPrdFeeData(@Param("dtoList") List<DisRepCashYield> dtoList);

    void insertPrdFeeData(@Param("dtoList") List<DisRepCashYield> dtoList);

    List<DisPrdFeeRateInfoErrodataWithBLOBs> selectdisPrdFeeRateErrordata(@Param("datauuid")String datauuid);



}
