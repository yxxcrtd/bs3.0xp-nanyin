package com.joyintech.tams.svreport.chb.transmonthreport.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthAssetPosition;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthPrdPosition;

public interface ChbTransMonthReportDetailMapper {

    /**
     * 
     * 查询产品头寸
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    List<ChbTransMonthPrdPosition> selectPrdList(ChbTransMonthPrdPosition pos);
    /**
     * 
     * 查询资产头寸
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    List<ChbTransMonthAssetPosition> selectAssetList(ChbTransMonthAssetPosition pos);
    
    /**
     * 查询资管计划底层非标
     * @param pos
     * @return
     */
    List<ChbTransMonthAssetPosition> selectAssetListBottom(ChbTransMonthAssetPosition pos);
   /**
    * 查询所有的下属机构
    * @param pageParam
    * @return
    */
    List<SelectOptionDto> getChbTransOrg(Map<String, Object> pageParam);
}
