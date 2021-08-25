package com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
import com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.StockQuotationDto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.AssetInfoDto;

import java.util.List;
import java.util.Map;


public interface ModBintreeConfigDtoMapper {
    int deleteByPrimaryKey(String seqno);
    
    int deleteByFinprodId(String finprodId);

    int insert(ModBintreeConfigDto record);

    int insertSelective(ModBintreeConfigDto record);

    ModBintreeConfigDto selectByPrimaryKey(String seqno);
    
    ModBintreeConfigDto selectByFinprodId(String finprodId);
    

    int updateByPrimaryKeySelective(ModBintreeConfigDto record);

    int updateByPrimaryKey(ModBintreeConfigDto record);
    List<ModBintreeConfigDto> selectForPage(Map<String, Object> map);
    int checkIfHashData(String finprodId);

    List<AssetInfoDto> selectAssetNoClassify(AssetInfoDto assetInfoDto);

    List<StockQuotationDto> getStockQuotation(StockQuotationDto assetInfoDto);

    List<SelectOptionDto> getMarketId(Map<String, Object> map);
}