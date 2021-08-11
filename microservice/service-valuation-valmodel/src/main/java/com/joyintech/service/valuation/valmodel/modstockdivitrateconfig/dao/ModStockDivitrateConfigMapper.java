package com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.StockQuotationDto;
import com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dto.ModStockDivitrateConfigInfoDto;
import org.springframework.stereotype.Repository;


@Repository
public interface ModStockDivitrateConfigMapper {
	
	List<ModStockDivitrateConfigInfoDto> selectForPage(Map<String, Object> param);
	
	ModStockDivitrateConfigInfoDto selectByPrimaryKey(Map<String, Object> map);
	
	int updateByPrimaryKey(ModStockDivitrateConfigInfoDto record);
	
	int insertSelective(ModStockDivitrateConfigInfoDto record);
	
	int deleteByPrimaryKey(ModStockDivitrateConfigInfoDto record);
	
	int checkByFinprodId(Map<String, Object> param);

	List<SelectOptionDto> selectAssetCodeList(Map<String, Object> map);
}
