package com.joyintech.tams.svreport.pbc.assetdebtdat.dao;

import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetConfigDatDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcReportAssetConfigDatMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportAssetConfigDatDto record);

    int insertSelective(PbcReportAssetConfigDatDto record);

    int insertBasicByBasicInfo(PbcReportAssetConfigDatDto record);

    PbcReportAssetConfigDatDto selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportAssetConfigDatDto record);

    int updateByPrimaryKey(PbcReportAssetConfigDatDto record);

    /**
     * 功能:根据主表uuid查询相关除回购和拆借外贷款分行业、企业规模明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月5日
     * @param reportuuid
     * @return
     * List<PbcReportAreaNewDetailDto>
     */
    List<PbcReportAssetConfigDatDto> selectByReportuuid(String reportuuid);

    /**
     * 功能:根据报表类型，报表日期删除明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @param reporttype
     * @return
     * int
     */
    int deleteByReportDate(@Param("reportStartDate") String reportEndDate, @Param("reportEndDate") String reportStartDate, @Param("reporttype") String reporttype, @Param("deptCode") String deptCode);

    int deleteByUUIDAndType(Map<String,Object> paramsMap);

    List<PbcReportAssetConfigDatDto> selectDatDiffByReport(@Param("reportuuid") String reportuuid, @Param("reporttype") String reporttype);

    int deleteByReportUUid(@Param("reportUuid") String reportUuid);

    int insertDataBySelect(@Param("reportUuid") String reportUuid);

    List<PbcReportAssetConfigDatDto> selectByDatZipCode(@Param("datZipCode") String datZipCode, @Param("datZipDatType") String datZipDatType);
}