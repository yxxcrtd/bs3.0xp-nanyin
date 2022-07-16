
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.disclosure.information.report.dto.ReportBclgg001Dto;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportBclgg001Mapper
 * @since 1.0
 */
@Repository
public interface ReportBclgg001Mapper {
    
    /**
     * 根据产品代码查询不成立公告
     * @param params 参数    
     * @return ReportFxgg001Dto
     * @see
     * @since 1.0
     */
    ReportBclgg001Dto queryBclgg(Map<String, Object> params);

}
