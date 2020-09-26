package com.joyintech.tams.svreport.chb.datacheck.beforesub.dao;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.svreport.chb.datacheck.beforesub.dto.ChbBeforeSubCheckDto;

public interface ChbBeforeSubCheckMapper {
    
    /**
     * 
     * 检查终止登记
     * @return
     * @see
     * @since 1.0
     */
    List<ChbBeforeSubCheckDto> selectProdEndRegCheck(Date checkdate);
    
    List<ChbBeforeSubCheckDto> selectRaiseRegCheck(Date checkdate);
    
    List<ChbBeforeSubCheckDto> selectInvestRegCheck(Date checkdate);
    
    List<ChbBeforeSubCheckDto> selectBotAstRegCheck(Date checkdate);
    
    List<ChbBeforeSubCheckDto> selectProdMonthReportCheck(Date checkdate);
}