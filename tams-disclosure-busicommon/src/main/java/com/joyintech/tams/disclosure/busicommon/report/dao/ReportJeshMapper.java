
package com.joyintech.tams.disclosure.busicommon.report.dao;

import com.joyintech.tams.disclosure.busicommon.report.dto.ReportJeshDto;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportJeshMapper
 * @since 1.0
 */
@Repository
public interface ReportJeshMapper {
    
    /**
     * 查询巨额赎回信息
     * @param params 参数    
     * @return Report1CpnflDto
     * @see
     * @since 1.0
     */
    ReportJeshDto queryJesh(Map<String, Object> params);
}
