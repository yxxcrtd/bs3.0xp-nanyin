package com.joyintech.tams.svreport.chb.monthreportdetail.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbMonthAssetPositiondetail;
import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthPrdPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailAsset;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailProd;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.MstCustomerInfoDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ChbMonthReportDetailAssetMapper {
    List<ChbMonthReportDetailAsset> selectDetailList(Map<String,Object> map);

    List<SelectOptionDto> getAssetCode(Map<String,Object> map);

    int insertBatch(List<ChbMonthAssetPositiondetail> list);

    int deleteDetail(String term);

    List<SelectOptionDto> getCounterList(Map<String,Object> map);

    List<MstCustomerInfoDetail> getCustomerList(Map<String,Object> map);

    List<ChbMonthReportDetailAsset> selectBalanceData(Map<String,Object> map);

    List<ChbMonthReportDetailAsset> selectBalanceDataSubSystem(Map<String,Object> map);



}