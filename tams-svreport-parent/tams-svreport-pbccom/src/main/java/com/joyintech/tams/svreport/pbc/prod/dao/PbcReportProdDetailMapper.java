package com.joyintech.tams.svreport.pbc.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.pbc.prod.dto.PbcReportProdDetailDto;

public interface PbcReportProdDetailMapper {
	
    int insert(PbcReportProdDetailDto record);

    /**
     * 功能:根据主表uuid查询相关资管产品明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @param reportuuid
     * @return 
     * List<PbcReportProdDetailDto>
     */
	List<PbcReportProdDetailDto> selectByReportuuid(String reportuuid);
	
	int deleteByReportDate(@Param("reportStartDate")String reportStartDate,@Param("reportEndDate")String reportEndDate,@Param("reporttype")String reporttype);

	int deleteByReportDateDept(@Param("reportStartDate")String reportStartDate,@Param("reportEndDate")String reportEndDate,@Param("reporttype")String reporttype
			,@Param("deptCode")String deptCode);
}