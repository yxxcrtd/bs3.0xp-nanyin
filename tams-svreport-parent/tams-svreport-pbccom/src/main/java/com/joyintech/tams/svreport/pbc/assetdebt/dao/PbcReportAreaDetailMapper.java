package com.joyintech.tams.svreport.pbc.assetdebt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAreaDetailDto;

/**
 * 功能:人行报表-除回购和拆借外贷款分行业、企业规模统计模板mapper表2.2
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Repository
public interface PbcReportAreaDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportAreaDetailDto record);

    int insertSelective(PbcReportAreaDetailDto record);

    PbcReportAreaDetailDto selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportAreaDetailDto record);

    int updateByPrimaryKey(PbcReportAreaDetailDto record);

    /**
     * 功能:根据主表uuid查询相关除回购和拆借外贷款分行业、企业规模明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月5日
     * @param reportuuid
     * @return 
     * List<PbcReportAreaDetailDto>
     */
	List<PbcReportAreaDetailDto> selectByReportuuid(String reportuuid);
    
	/**
     * 功能:根据报表类型，报表日期删除明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @param reportDate
     * @param reporttype
     * @return 
     * int
     */
    int deleteByReportDate(@Param("reportStartDate")String reportEndDate,@Param("reportEndDate")String reportStartDate,@Param("reporttype")String reporttype,@Param("deptCode")String deptCode);
}