package com.joyintech.tams.svreport.chb.monthreport.dao;


import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportG06bDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMonthReportG06bDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(ChbMonthReportG06bDetail record);

    int insertSelective(ChbMonthReportG06bDetail record);

    ChbMonthReportG06bDetail selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(ChbMonthReportG06bDetail record);

    int updateByPrimaryKey(ChbMonthReportG06bDetail record);
}