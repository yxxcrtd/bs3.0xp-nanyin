
package com.joyintech.service.disclosure.information.report.dao;
import java.util.Date;
import java.util.Map;

import com.joyintech.service.disclosure.information.report.dto.ReportFxgg001Dto;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportFxgg001Mapper
 * @since 1.0
 */
@Repository(value="serviceReportFxgg001Mapper")
public interface ReportFxgg001Mapper {
    
    /**
     * 根据产品代码查询公告信息
     * @param params 参数    
     * @return ReportFxgg001Dto
     * @see
     * @since 1.0
     */
    ReportFxgg001Dto queryFxgg(Map<String, Object> params);

    /**
     * 查询下一个工作日
     * @param params 参数
     * @return ReportFxgg001Dto
     * @see
     * @since 1.0
     */
    Date queryNextWorkDay(Map<String, Object> params);

}
