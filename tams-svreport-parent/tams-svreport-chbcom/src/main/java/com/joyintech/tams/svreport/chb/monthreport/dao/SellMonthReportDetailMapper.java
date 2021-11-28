package com.joyintech.tams.svreport.chb.monthreport.dao;

import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreport.dto.PrdSaletargetDealDetail;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReportDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SellMonthReportDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    ChbMonthReportDetail selectByPrimaryKey(String detailuuid);
    
    List<SellMonthReportDetail> selectByModel(Map<String,Object> map);

    //根据月报主表查询明细表信息
    List<SellMonthReportDetail> selectReportDetail(@Param("reportuuid") String reportuuid);

   List<String> selectAllPropertyCode();
}