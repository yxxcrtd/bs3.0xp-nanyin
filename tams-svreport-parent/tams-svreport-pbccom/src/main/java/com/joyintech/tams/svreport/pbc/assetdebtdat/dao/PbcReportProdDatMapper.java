package com.joyintech.tams.svreport.pbc.assetdebtdat.dao;

import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PbcReportProdDatMapper {

    int insert(PbcReportProdDatDto record);

    /**
     * 功能:根据主表uuid查询相关资管产品明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @param reportuuid
     * @return
     * List<PbcReportProdDatDto>
     */
	List<PbcReportProdDatDto> selectByReportuuid(String reportuuid);

	int deleteByReportDate(@Param("reportStartDate") String reportStartDate, @Param("reportEndDate") String reportEndDate, @Param("reporttype") String reporttype);

	int deleteByReportDateDept(@Param("reportStartDate") String reportStartDate, @Param("reportEndDate") String reportEndDate, @Param("reporttype") String reporttype
            , @Param("deptCode") String deptCode);
}