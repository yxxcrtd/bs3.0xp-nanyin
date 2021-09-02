package com.joyintech.tams.model.impairment.batch.impairmentdataload.dao;

import com.joyintech.tams.common.base.entity.ModAssetGradeConfig;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.*;
import com.joyintech.tams.valuation.common.dto.modelDto.ModRateDataDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ImpairmentDataLoadMapper {

	//获取资产类型
	List<ModAseetMapping> selectModAseetMapping(Map<String, Object> params);
	//获取资产类型评级配置
	List<ModAssetGradeConfig> selectModAssetGradeConfig(Map<String, Object> params);
	//查询买入时点评级结果
	String selectResultDeal(Map<String, Object> params);
	//判断使用的参数是否已存在减值配置使用情况表
	int selectModConfigUseageCount(Map<String, Object> params);

	int insertModConfigUseage(List<ModConfigUseage> listInsert);


	//获取需要当天处理的初始数据列表(买入时点用)
	List<ImpairmentDataLoadStep2ReaderDto> selectModEclResultProcessForDeal(Map<String, Object> params);

	int deleteModEclResultProcess(Map<String, Object> params);

	//查询评级主体发行的所有债券
	List<String> getUnissueSecIdList(Map<String, Object> param);

	//非标外部评级查询-减值模型
	ModRateDataDto selectOutRateData(Map<String,Object> paraMap);
}
