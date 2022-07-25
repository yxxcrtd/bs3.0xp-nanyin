
package com.joyintech.tams.disclosure.information.report.dao;
import java.util.Map;

import com.joyintech.tams.disclosure.information.report.dto.ReportFxggDto;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportFxggMapper
 * @since 1.0
 */
@Repository
public interface ReportFxggMapper {
    
    /**
     * 根据产品代码查询公告信息
     * @param params 参数    
     * @return ReportFxgg001Dto
     * @see
     * @since 1.0
     */
    ReportFxggDto queryFxgg(Map<String, Object> params);

}
