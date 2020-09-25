package com.joyintech.service.period.trdirsduration.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;

/**
 * 利率互换存续期管理
 *
 * @author renping
 * @version 1.0 2018年8月31日
 * @see TrdIRSDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdIRSDurationMapper {

    List<SelectOptionDto> getPortfolioId(Map<String, Object> param);

    List<SelectOptionDto> getFinprodId(Map<String, Object> param);

    List<TrdIRSDurationDto> findPage(Map<String, Object> param);

    TrdIRSDurationDto findEffectiveData(Map<String, Object> param);

    List<BaseTradeProductDealDto> findEffectiveDeal(Map<String, Object> param);

    List<SelectOptionDto> getBusiCode(Map<String, Object> param);
    
    String findCeTradeIdByWorkflowId(String workflowId);
    
    String selectCounterIdByFinprodId(String finprodId);
}
