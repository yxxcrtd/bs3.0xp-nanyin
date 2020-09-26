package com.joyintech.tams.svreport.pbc.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.pbc.report.dto.PbcReportTemplateDto;

/**
 * 功能:人行报表模板Mapper
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年3月5日
 * @修改记录：
 */
public interface PbcReportTemplateMapper {
    int deleteByPrimaryKey(String tempuuid);

    int insert(PbcReportTemplateDto record);

    int insertSelective(PbcReportTemplateDto record);

    PbcReportTemplateDto selectByPrimaryKey(String tempuuid);

    int updateByPrimaryKeySelective(PbcReportTemplateDto record);

    int updateByPrimaryKey(PbcReportTemplateDto record);
    
    List<PbcReportTemplateDto> selectByReporttype(@Param("reporttype")String reporttype,@Param("status")String status);
}