package com.joyintech.tams.svreport.interf.chb.bottomassetposition.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbBotAstPosition;
import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;

public interface ChbBotAstPosInterfMapper {
	void selectByPosdate(Date batchTime);
	
	int deleteByPosdate(Date batchTime);
	
	int deleteByUpdateTime();
	
	int insertBatchList(@Param("listChbBotAstPosition")List<ChbBotAstPosition> listChbBotAstPosition);
	
	int insert(ChbBotAstPosition chbBotAstPosition);
	
	List<Date> isUpdated(Date batchTime);
	
	int deleteBatchList(@Param("listChbBotAstPosition")List<ChbBotAstPosition> listChbBotAstPosition);
	
	void mergeData(@Param("newDataList")List<ChbBotAstPosition> newDataList);

	List<Date> isUpdatedChl(Date batchTime);
}