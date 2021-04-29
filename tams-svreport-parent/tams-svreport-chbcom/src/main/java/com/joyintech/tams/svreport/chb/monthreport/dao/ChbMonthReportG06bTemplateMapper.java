package com.joyintech.tams.svreport.chb.monthreport.dao;

import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportG06bTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMonthReportG06bTemplateMapper {
    int deleteByPrimaryKey(String tempuuid);

    int insert(ChbMonthReportG06bTemplate record);

    int insertSelective(ChbMonthReportG06bTemplate record);

    ChbMonthReportG06bTemplate selectByPrimaryKey(String tempuuid);

    int updateByPrimaryKeySelective(ChbMonthReportG06bTemplate record);

    int updateByPrimaryKey(ChbMonthReportG06bTemplate record);
}