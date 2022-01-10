package com.joyintech.tams.svreport.pbc.reportdetail.dao;

import com.joyintech.tams.svreport.pbc.reportdetail.dto.PbcMonthReportDetailProd;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcMonthReportDetailProdMapper {

    List<PbcMonthReportDetailProd> selectDetailList(Map<String, Object> map);

    int insertBatch(List<PbcMonthReportDetailProd> list);

    int deleteDetail(String term);

}