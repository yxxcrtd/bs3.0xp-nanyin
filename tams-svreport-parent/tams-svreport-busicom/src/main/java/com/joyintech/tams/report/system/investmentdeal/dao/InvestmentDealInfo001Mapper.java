package com.joyintech.tams.report.system.investmentdeal.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbInvestTradeErrordataWithBLOBs;
import com.joyintech.tams.report.system.investmentdeal.dto.InvestmentDealInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InvestmentDealInfo001Mapper {

    List<InvestmentDealInfoDto> queryForPage(Map<String, Object> param);

    List<InvestmentDealInfoDto> queryReportList(Map<String, Object> param);

    InvestmentDealInfoDto selectOneDate(Map<String, Object> param);

    void insertOne(InvestmentDealInfoDto dto);

    void updateOne(InvestmentDealInfoDto dto);

    void deleteErrorDataOne(@Param("tradeCode")String tradeCode);

    ChbInvestTradeErrordataWithBLOBs selectInvestmentDealInfoErrordata(@Param("datauuid")String datauuid);

    List<SelectOptionDto> getProdId(Map<String, Object> param);

    List<SelectOptionDto> getTradeCounter(Map<String, Object> param);




}
