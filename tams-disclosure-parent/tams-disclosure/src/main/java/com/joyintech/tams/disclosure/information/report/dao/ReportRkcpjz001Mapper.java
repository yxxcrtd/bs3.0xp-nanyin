
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.disclosure.information.report.dto.ReportGrahDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportRkcpjz001Dto;
import com.joyintech.tams.disclosure.information.report.dto.ReportValueDto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportRkcpjz001Mapper
 * @since 1.0
 */
@Repository
public interface ReportRkcpjz001Mapper {

    ReportRkcpjz001Dto getProdInfo(Map<String, Object> searchParam);

    Map<String, Object> getLastValue(Map<String, Object> searchParam);
    //获取小于等于当前最新净值日期的过去30个工作日的单位净值
    List<ReportGrahDto> getValues(Map<String, Object> searchParam);

    List<ReportValueDto> getXgxValueList(Map<String, Object> searchparam);

    List<ReportValueDto> getJzxValueList(Map<String, Object> searchparam);

    List<Date> getValueDayList(Map<String, Object> searchparam);
}
