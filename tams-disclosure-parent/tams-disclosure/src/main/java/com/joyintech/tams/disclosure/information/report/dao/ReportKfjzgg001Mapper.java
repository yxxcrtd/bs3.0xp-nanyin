
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.disclosure.information.report.dto.ReportKfjzgg001Dto;
import com.joyintech.tams.disclosure.information.report.dto.ReportOpenPlanDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportValueDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportZqxkfgg001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportKfjzgg001Mapper
 * @since 1.0
 */
@Repository
public interface ReportKfjzgg001Mapper {

    ReportKfjzgg001Dto getProdInfo(Map<String, Object> searchparam);

    /**
     * 后续申购赎回
     * @param searchParam
     * @return
     */
    List<ReportOpenPlanDto> getOpenList(Map<String, Object> searchParam);

    /**
     * 历史申购赎回
     * @param searchParam
     * @return
     */
    List<ReportOpenPlanDto>  getOpenHisList(Map<String, Object> searchParam);

    /**
     * 查询上一开放结束日的净值数据
     * @param searchparam
     * @return
     */
    ReportOpenPlanDto getLastOpenPlan(Map<String, Object> searchparam);
}
