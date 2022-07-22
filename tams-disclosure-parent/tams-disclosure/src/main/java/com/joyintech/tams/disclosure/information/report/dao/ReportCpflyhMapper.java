
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.disclosure.information.report.dto.Report1CpnflDto;
import com.joyintech.tams.disclosure.information.report.dto.Report1FlncpDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportProdfeeDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportCpflyhMapper
 * @since 1.0
 */
@Repository
public interface ReportCpflyhMapper {
    
    /**
     * 查询一产多费信息
     * @param params 参数    
     * @return Report1CpnflDto
     * @see
     * @since 1.0
     */
    Report1CpnflDto query1Cpnfl(Map<String, Object> params);

    /**
     * 查询一费多产信息
     * @param params 参数
     * @return Report1FlncpDto
     * @see
     * @since 1.0
     */
    Report1FlncpDto query1Flncp(Map<String, Object> params);

    /**
     * 查询一产多费费用优惠信息
     * @param params 参数
     * @return ReportProdfeeDto
     * @see
     * @since 1.0
     */
    List<ReportProdfeeDto> qureyFeelist(Map<String, Object> params);

    /**
     * 查询一费多产产品优惠信息
     * @param params 参数
     * @return ReportProdfeeDto
     * @see
     * @since 1.0
     */
    List<ReportProdfeeDto> qureyProdlist(Map<String, Object> params);
}
