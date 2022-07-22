
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.common.base.entity.DisProdPlanInfo;
import com.joyintech.tams.disclosure.information.report.dto.ReportBonusReportDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportCommonProdDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    ReportBonusReportDto getBonusInfo(Map<String, Object> searchParam);

    DisProdPlanInfo getNextOpenPlan(Map<String, Object> searchParam);

    ReportBonusReportDto getLastBonusInfo(Map<String, Object> searchParam);
}
