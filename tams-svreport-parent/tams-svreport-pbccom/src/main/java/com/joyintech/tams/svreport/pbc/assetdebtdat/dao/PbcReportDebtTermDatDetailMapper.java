package com.joyintech.tams.svreport.pbc.assetdebtdat.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportCompanyDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportDebtTermDatDetail;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PbcReportDebtTermDatDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportDebtTermDatDetail record);

    int insertSelective(PbcReportDebtTermDatDetail record);

    PbcReportDebtTermDatDetail selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportDebtTermDatDetail record);

    int updateByPrimaryKey(PbcReportDebtTermDatDetail record);

    int deleteByReportUUid(@Param("reportUuid") String reportUuid);

    int insertDataBySelect(@Param("reportUuid") String reportUuid);
    
    List<PbcReportDebtTermDatDetail> selectByReportuuid(String reportuuid);
    /**
     * 功能:根据报表类型，报表日期删除明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：yuluan 2019年3月12日
     * @param reportDate
     * @param reporttype
     * @return 
     * int
     */
    int deleteByReportDate(@Param("reportStartDate") String reportEndDate, @Param("reportEndDate") String reportStartDate, @Param("reporttype") String reporttype
            , @Param("deptCode") String deptCode);

    int deleteByUUIDAndType(Map<String,Object> paramsMap);

    List<PbcReportDebtTermDatDetail> selectDatDiffByReport(@Param("reportuuid") String reportuuid, @Param("reporttype") String reporttype);


    List<PbcReportDebtTermDatDetail> selectByDatZipCode(@Param("datZipCode") String datZipCode, @Param("datZipDatType") String datZipDatType);

}