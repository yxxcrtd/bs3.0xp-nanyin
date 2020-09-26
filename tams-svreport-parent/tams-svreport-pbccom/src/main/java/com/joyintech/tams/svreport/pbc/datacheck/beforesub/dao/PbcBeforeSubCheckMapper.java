package com.joyintech.tams.svreport.pbc.datacheck.beforesub.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.pbc.datacheck.beforesub.dto.PbcBeforeSubCheckDto;


public interface PbcBeforeSubCheckMapper {
    
    List<PbcBeforeSubCheckDto> selectPbcPrdBaseInfoSubCheck(Date checkdate);
    
    List<PbcBeforeSubCheckDto> selectPbcPrdBaseInfoEndSubCheck(Date checkdate);
    
    List<PbcBeforeSubCheckDto> selectPbcPrdRaisingSubCheck(Date checkdate);
    
    List<PbcBeforeSubCheckDto> selectPbcAssetDebt(@Param("enddate")Date enddate,@Param("checkdate")Date checkdate);
    
}