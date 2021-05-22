package com.joyintech.tams.report.system.chbemployee.dao;

import com.joyintech.tams.common.base.entity.ChbEmployeeInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.report.system.chbemployee.dto.ChbEmployeeInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbEmployeeInfo001Mapper {

    List<ChbEmployeeInfoDto> queryForPage(Map<String, Object> param);

    void updateChbEmployeeInfo(@Param("dtoList") List<ChbEmployeeInfo> dtoList);

    List<ChbEmployeeInfoDto> queryReportList(@Param("dtoList") List<ChbEmployeeInfoDto> dtoList);

    List<ChbEmployeeInfo> selectOneDate(Map<String, Object> param);

    void insertOne(MstBondCreditRating entity);

    void updateOne(Map<String, Object> param);

    void deleteOne(Map<String, Object> param);

    List<Integer> selectChbMstCounterPartyData(@Param("dtoList") List<ChbMstCounterParty> dtoList);

    void insertChbEmployeeInfoData(@Param("dtoList") List<ChbEmployeeInfo> dtoList);





}
