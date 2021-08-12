package com.joyintech.tams.svreport.pbc.assetdebtdat.dao;

import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAreaDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetIncomeDatDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface PbcReportAssetIncomeDatDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportAssetIncomeDatDetail record);

    int insertSelective(PbcReportAssetIncomeDatDetail record);

    PbcReportAssetIncomeDatDetail selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportAssetIncomeDatDetail record);

    int updateByPrimaryKey(PbcReportAssetIncomeDatDetail record);

    int deleteByReportUUid(@Param("reportUuid") String reportUuid);

    int insertDataBySelect(@Param("reportUuid") String reportUuid);
    
    List<PbcReportAssetIncomeDatDetail> selectByReportuuid(String reportuuid);
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
    int deleteByReportDate(@Param("reportStartDate") String reportEndDate, @Param("reportEndDate") String reportStartDate, @Param("reporttype") String reporttype, @Param("deptCode") String deptCode);

    List<PbcReportAssetIncomeDatDetail> selectDatDiffByReport(@Param("reportuuid") String reportuuid, @Param("reporttype") String reporttype);

    int deleteByUUIDAndType(Map<String,Object> paramsMap);

    List<PbcReportAssetIncomeDatDetail> selectByDatZipCode(@Param("datZipCode") String datZipCode, @Param("datZipDatType") String datZipDatType);


}