package com.joyintech.tams.svreport.pbc.assetdebt.dao;

import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetConfigurationDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PbcReportAssetConfigurationMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportAssetConfigurationDto record);

    int insertSelective(PbcReportAssetConfigurationDto record);

    int insertBasicByBasicInfo(PbcReportAssetConfigurationDto record);

    PbcReportAssetConfigurationDto selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportAssetConfigurationDto record);

    int updateByPrimaryKey(PbcReportAssetConfigurationDto record);

    /**
     * 功能:根据主表uuid查询相关数据
     * @return
     * List<PbcReportAreaDetailDto>
     */
    List<PbcReportAssetConfigurationDto> selectByReportuuid(String reportuuid);

    /**
     * 功能:根据报表类型，报表日期删除明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：zhengxiaolian 2022年1月11日
     * @return
     * int
     */
    int deleteByReportDate(@Param("reportStartDate")String reportEndDate, @Param("reportEndDate")String reportStartDate, @Param("reporttype")String reporttype, @Param("deptCode")String deptCode);
}