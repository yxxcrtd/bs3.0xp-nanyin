package com.joyintech.service.entrust.etrautomanagementaccount.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrautomanagementaccount.dto.EtrAutoManagementAccountDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EtrAutoManagementAccountMapper {

    List<EtrAutoManagementAccountDto> selectForPage(Map<String,Object> map);

    List<SelectOptionDto> getSelectPortfolioId(Map<String, Object> map);
    List<SelectOptionDto> getSelectPortfolioIdModel(Map<String, Object> map);

    List<SuggestDto> getFinprodMarketId(Map<String, Object> map);

    List<SelectOptionDto> getSelectFinprodMarketId(Map<String, Object> map);

    List<SelectOptionDto> finprodMarketIdChange(Map<String, Object> map);

    List<SelectOptionDto> finprodMarketIdType7Change(Map<String, Object> map);

    EtrAutoManagementAccountDto selectByKey(@Param("entrustId") String entrustId);

    List<EtrAutoManagementAccountDto> portfolioIdChange(Map<String,Object> map);

    List<EtrAutoManagementAccountDto> selectAllData(Map<String, Object> pageParam);
    
    List<EtrAutoManagementAccountDto> getSelectPortfolioIdModelForOld(Map<String, Object> map);
    
    EtrAutoManagementAccountDto selectByKeyForOld(@Param("entrustId") String entrustId);
}
