
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.disclosure.information.report.dto.ReportFxgg001Dto;
import com.joyintech.tams.disclosure.information.report.dto.ReportGrahDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportJzcpjzgg001Mapper
 * @since 1.0
 */
@Repository
public interface ReportJzcpjzgg001Mapper {


    Map<String, Object> getProdInfo(String portnum);

    Map<String, Object> getLastValue(Map<String, Object> params);
    //获取小于等于当前最新净值日期的过去30个工作日的单位净值
    List<ReportGrahDto> getValues(Map<String, Object> searchParam);
}
