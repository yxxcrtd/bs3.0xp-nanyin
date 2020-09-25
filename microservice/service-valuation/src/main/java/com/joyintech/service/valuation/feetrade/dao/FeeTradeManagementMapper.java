package com.joyintech.service.valuation.feetrade.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.feetrade.dto.FeeTradeManagementDto;
/**
 * 费用交易管理mapper
 * @version 1.0 20200121
 * @see FeeTradeManagementMapper
 * @since 1.0
 */
@Repository
public interface FeeTradeManagementMapper {
    /**
     * @return
     */
	List<FeeTradeManagementDto> findPage(Map<String,Object> params);
	
    List<SelectOptionDto> getBookSetList(Map<String,Object> params);
    
    List<BigDecimal> getShouldPayAmt(Map<String,Object> params);
    
    List<String> getBaseSubjectNature(Map<String,Object> params);
    
    List<String> getBokDetailId(Map<String,Object> params);
    
    List<Map<String, Object>> getFinProductByBookSetId(String bookSetId);
    
    String getCcyByBokSetId(String bookSetId);
    
    int updateBokDealAccounting(Map<String,Object> params);
    
    int updateStlOrder(Map<String,Object> params);
    
    int deleteBokDealAccounting(Map<String,Object> params);
    
    int deleteStlOrder(Map<String,Object> params);
    
    List<SelectOptionDto> getSubBookSetList(Map<String, Object> params);
}
