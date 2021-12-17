package com.joyintech.tams.svreport.chb.monthreportdetail.dao;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthAssetPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailProd;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ChbMonthReportDetailProdMapper {

    List<ChbMonthReportDetailProd> selectDetailList(Map<String,Object> map);

    int insertBatch(List<ChbMonthPrdPositiondetail> list);

    int deleteDetail(String term);

}