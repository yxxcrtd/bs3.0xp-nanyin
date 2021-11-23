package com.joyintech.tams.svreport.interf.chb.investmentsinfo.dao;

import com.joyintech.tams.common.base.entity.ChbPrdRate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbPrdRateInterfMapper {

	void insert(ChbPrdRate chbPrdRate);

	void mergeChbPrdRateData(@Param("list") List<ChbPrdRate> list);
	
	int deleteTodayData();
}
