package com.joyintech.tams.svreport.pbc.assetdebt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetIncomeDetail;


@Repository
public interface PbcReportAssetIncomeDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportAssetIncomeDetail record);

    int insertSelective(PbcReportAssetIncomeDetail record);

    PbcReportAssetIncomeDetail selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportAssetIncomeDetail record);

    int updateByPrimaryKey(PbcReportAssetIncomeDetail record);
    
    List<PbcReportAssetIncomeDetail> selectByReportuuid(String reportuuid);
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
    int deleteByReportDate(@Param("reportStartDate")String reportEndDate,@Param("reportEndDate")String reportStartDate,@Param("reporttype")String reporttype,@Param("deptCode")String deptCode);
}