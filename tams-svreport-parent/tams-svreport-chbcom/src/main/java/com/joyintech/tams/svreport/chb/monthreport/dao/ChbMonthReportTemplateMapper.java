package com.joyintech.tams.svreport.chb.monthreport.dao;

import java.util.List;

import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportTemplate;

public interface ChbMonthReportTemplateMapper {
    int deleteByPrimaryKey(String tempuuid);

    int insert(ChbMonthReportTemplate record);

    int insertSelective(ChbMonthReportTemplate record);

    ChbMonthReportTemplate selectByPrimaryKey(String tempuuid);
    
    List<ChbMonthReportTemplate> select(String reporttype);

    int updateByPrimaryKeySelective(ChbMonthReportTemplate record);

    int updateByPrimaryKey(ChbMonthReportTemplate record);
}