package com.joyintech.tams.svreport.chb.datacheck.beforesub.dao;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.svreport.chb.datacheck.beforesub.dto.ChbBeforeSubCheckDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbBeforeSubCheckMapper {
    
    /**
     * 
     * 检查终止登记
     * @return
     * @see
     * @since 1.0
     */
    List<ChbBeforeSubCheckDto> selectProdEndRegCheck(@Param("checkdate") Date checkdate, @Param("checkKeys") List<String> checkKeys);
    
    List<ChbBeforeSubCheckDto> selectRaiseRegCheck(@Param("checkdate") Date checkdate, @Param("checkKeys") List<String> checkKeys);
    
    List<ChbBeforeSubCheckDto> selectInvestRegCheck(@Param("checkdate") Date checkdate, @Param("checkKeys") List<String> checkKeys);
    
    List<ChbBeforeSubCheckDto> selectBotAstRegCheck(@Param("checkdate") Date checkdate, @Param("checkKeys") List<String> checkKeys);
    
    List<ChbBeforeSubCheckDto> selectProdMonthReportCheck(@Param("checkdate") Date checkdate, @Param("checkKeys") List<String> checkKeys);
}