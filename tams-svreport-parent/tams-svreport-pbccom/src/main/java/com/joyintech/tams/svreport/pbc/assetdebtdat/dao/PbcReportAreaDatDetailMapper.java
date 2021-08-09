package com.joyintech.tams.svreport.pbc.assetdebtdat.dao;

import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAreaDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetDebtDatDetailDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能:人行报表-除回购和拆借外贷款分行业、企业规模统计模板mapper表2.2
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Repository
public interface PbcReportAreaDatDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportAreaDatDetailDto record);

    int insertSelective(PbcReportAreaDatDetailDto record);

    PbcReportAreaDatDetailDto selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportAreaDatDetailDto record);

    int updateByPrimaryKey(PbcReportAreaDatDetailDto record);

    /**
     * 功能:根据主表uuid查询相关除回购和拆借外贷款分行业、企业规模明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月5日
     * @param reportuuid
     * @return 
     * List<PbcReportAreaNewDetailDto>
     */
	List<PbcReportAreaDatDetailDto> selectByReportuuid(String reportuuid);
    
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
    int deleteByReportDate(@Param("reportStartDate") String reportEndDate, @Param("reportEndDate") String reportStartDate, @Param("reporttype") String reporttype, @Param("deptCode") String deptCode);

    int deleteByUUIDAndType(Map<String,Object> paramsMap);

    List<PbcReportAreaDatDetailDto> selectDatDiffByReport(@Param("reportuuid") String reportuuid, @Param("reporttype") String reporttype);

    int deleteByReportUUid(@Param("reportUuid") String reportUuid);

    int insertDataBySelect(@Param("reportUuid") String reportUuid);
}