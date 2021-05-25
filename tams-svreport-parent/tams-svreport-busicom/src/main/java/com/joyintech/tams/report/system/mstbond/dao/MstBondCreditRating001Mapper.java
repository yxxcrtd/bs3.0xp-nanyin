package com.joyintech.tams.report.system.mstbond.dao;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface MstBondCreditRating001Mapper {

    List<MstBondCreditRating> queryForPage(Map<String, Object> param);

    List<ChbAssetInfo> getoCode(Map<String, Object> param);

    void updateMstBondCreditRating(@Param("dtoList") List<MstBondCreditRating> dtoList);

    List<MstBondCreditRating> queryReportList(@Param("dtoList") List<MstBondCreditRating> dtoList);

    List<MstBondCreditRating> selectOneDate(Map<String, Object> param);

    void insertOne(MstBondCreditRating entity);

    void updateOne(Map<String, Object> param);

    void deleteOne(Map<String, Object> param);

    void deleteErrorDataOne(@Param("counterId")String counterId);

    List<ChbMstCounterPartyErrodataWithBLOBs> selectChbMstCounterPartyErrordata(@Param("datauuid")String datauuid);

    List<SysDataDict> getSysDataDict(Map<String, Object> param);

    List<Integer> selectChbMstCounterPartyData(@Param("dtoList") List<ChbMstCounterParty> dtoList);

    void insertMstBondCreditRatingData(@Param("dtoList") List<MstBondCreditRating> dtoList);





}
