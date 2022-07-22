
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.common.base.entity.DisProdPlanInfo;
import com.joyintech.tams.disclosure.information.report.dto.ReportCommonProdDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportKfqHisBonusDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportKfqOpenPlanDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportKfqzjfhgg001Mapper
 * @since 1.0
 */
@Repository
public interface ReportKfqzjfhgg001Mapper {


    ReportCommonProdDto getProdInfo(Map<String,Object> searchParam);

    List<ReportKfqHisBonusDto> getHisBonusReportList(Map<String, Object> searchParam);

    List<ReportKfqOpenPlanDto> getOpenPlanList(Map<String, Object> searchParam);

    ReportKfqOpenPlanDto getLastBonusInfo(Map<String, Object> searchParam);
}
