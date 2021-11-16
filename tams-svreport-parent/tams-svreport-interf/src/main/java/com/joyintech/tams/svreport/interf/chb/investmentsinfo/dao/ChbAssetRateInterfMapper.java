package com.joyintech.tams.svreport.interf.chb.investmentsinfo.dao;

import com.joyintech.tams.common.base.entity.ChbAssetRate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbAssetRateInterfMapper {

	void insert(ChbAssetRate ChbAssetRate);

	void mergeChbAssetRateData(@Param("list") List<ChbAssetRate> list);
	
	int deleteTodayData();
}
