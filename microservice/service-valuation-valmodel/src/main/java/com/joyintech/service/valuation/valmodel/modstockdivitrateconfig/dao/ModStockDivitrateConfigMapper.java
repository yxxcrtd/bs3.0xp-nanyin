package com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dto.ModAseetMappingDto;
import com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dto.ModStockDivitrateConfigInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ModStockDivitrateConfigMapper {
	
	List<ModStockDivitrateConfigInfoDto> selectForPage(Map<String, Object> param);
	
	ModStockDivitrateConfigInfoDto selectByPrimaryKey(Map<String, Object> map);
	
	int updateByPrimaryKey(ModStockDivitrateConfigInfoDto record);
	
	int insertSelective(ModStockDivitrateConfigInfoDto record);
	
	int deleteByPrimaryKey(ModStockDivitrateConfigInfoDto record);
	
	int checkByFinprodId(Map<String, Object> param);

	List<SelectOptionDto> selectAssetCodeList(Map<String, Object> map);

	List<ModAseetMappingDto> selectAssetNoClassify(Map<String, Object> map);
}
