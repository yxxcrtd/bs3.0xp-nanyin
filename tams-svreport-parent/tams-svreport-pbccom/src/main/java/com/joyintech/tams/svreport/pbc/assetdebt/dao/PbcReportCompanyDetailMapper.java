package com.joyintech.tams.svreport.pbc.assetdebt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportCompanyDetailDto;

/**
 * 功能:人行报表-除回购和拆借外贷款按行业、企业模板mapper表2-2
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Repository
public interface PbcReportCompanyDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportCompanyDetailDto record);

    int insertSelective(PbcReportCompanyDetailDto record);

    PbcReportCompanyDetailDto selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportCompanyDetailDto record);

    int updateByPrimaryKey(PbcReportCompanyDetailDto record);

    /**
     * 功能:根据主表uuid查询相关除回购和拆借外贷款按行业、企业明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月5日
     * @param reportuuid
     * @return 
     * List<PbcReportCompanyDetailDto>
     */
	List<PbcReportCompanyDetailDto> selectByReportuuid(String reportuuid);
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
    int deleteByReportDate(@Param("reportStartDate")String reportEndDate,@Param("reportEndDate")String reportStartDate,
    					@Param("reporttype")String reporttype,@Param("deptCode")String deptCode);
}