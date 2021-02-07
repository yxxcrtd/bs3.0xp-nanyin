package com.joyintech.tams.svreport.interf.chb.investmentsinfo.dao;

import com.joyintech.tams.svreport.interf.chb.investmentsinfo.dto.ChbAssetInfoRate;
import com.joyintech.tams.svreport.interf.chb.investmentsinfo.dto.ChbProdInfoRate;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ChbAssetInfoRateInterfMapper {

	/**
	 * @Description 根据更新时间和创建时间查询报价信息
	 * @param updatetime
	 * @return List<ChbPrdPeriodYield>
	 */
	List<ChbAssetInfoRate> selectAssetInfoRateData(@Param("updatetime")Date updatetime, @Param("prodidList")List<String> prodidList);

	void mergeChbAssetInfoRatedData(@Param("list")List<ChbAssetInfoRate> list);


}
