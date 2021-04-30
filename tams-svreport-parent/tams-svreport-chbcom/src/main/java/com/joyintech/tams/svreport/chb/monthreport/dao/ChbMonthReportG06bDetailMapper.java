package com.joyintech.tams.svreport.chb.monthreport.dao;


import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportG06bDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbMonthReportG06bDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(ChbMonthReportG06bDetail record);

    int insertSelective(ChbMonthReportG06bDetail record);

    ChbMonthReportG06bDetail selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(ChbMonthReportG06bDetail record);

    int updateByPrimaryKey(ChbMonthReportG06bDetail record);

    /**
     * 功能:批量插入明细表
     */
    int insertBatch(@Param("insertList") List<ChbMonthReportG06bDetail> assetMonthReportG06bDetailList);

    /**
     * 功能:批量删除当月G06b风险资本计算表明细表数据
     */
    int deleteByTermId(@Param("termid") String termid, @Param("reportType") String reportType, @Param("reportuuid") String reportuuid);
}