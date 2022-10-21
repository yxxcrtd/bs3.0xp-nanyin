
package com.joyintech.tams.disclosure.busicommon.report.dao;

import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import com.joyintech.tams.common.base.entity.DisProdPlanInfo;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportCommonProdDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportKfqHisBonusDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportOpenPlanDto;
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

    List<ReportKfqHisBonusDto> getHisBonusReportList(Map<String, Object> searchParam);

    List<ReportOpenPlanDto> getOpenPlanList(Map<String, Object> searchParam);

    ReportKfqHisBonusDto getLastBonusInfo(Map<String, Object> searchParam);

    DisProdBonusplan checkGenerateFlag(Map<String, Object> searchParam);

    BigDecimal getUnitValue(Map<String, Object> searchParam);
}
