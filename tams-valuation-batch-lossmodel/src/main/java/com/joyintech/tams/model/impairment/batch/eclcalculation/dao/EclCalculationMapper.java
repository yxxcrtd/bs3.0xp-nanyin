package com.joyintech.tams.model.impairment.batch.eclcalculation.dao;

import com.joyintech.tams.common.base.entity.TrdAssetLoss;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModEclResult;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModLgdConfig;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EclCalculationMapper {

	//1.获取理需要处理的每一笔交易数据（计划做在数据加载批处理中）
	List<ModEclResultProcess> selectModEclResultProcessForDeal(Map<String, Object> params);

	int deleteModEclResult(Map<String, Object> params);
	//查询LGD
	List<ModLgdConfig> selectModLgdConfig(Map<String, Object> params);

	//查询减值资产管理
	List<TrdAssetLoss> queryEclManage(Map<String, Object> params);

	int insertModEclResult(List<ModEclResult> list);
}
