
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.disclosure.information.report.dto.Report1CpnysDto;
import com.joyintech.tams.disclosure.information.report.dto.Report1YsncpDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportProdadjDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportCpystzMapper
 * @since 1.0
 */
@Repository
public interface ReportCpystzMapper {
    
    /**
     * 查询单产品多要素信息
     * @param params 参数    
     * @return Report1CpnflDto
     * @see
     * @since 1.0
     */
    Report1CpnysDto query1Cpnys(Map<String, Object> params);

    /**
     * 查询单要素多产品信息
     * @param params 参数
     * @return Report1FlncpDto
     * @see
     * @since 1.0
     */
    Report1YsncpDto query1Ysncp(Map<String, Object> params);

    /**
     * 查询单产品多要素要素明细信息
     * @param params 参数
     * @return ReportProdfeeDto
     * @see
     * @since 1.0
     */
    List<ReportProdadjDto> qureyFieldlist(Map<String, Object> params);

    /**
     * 查询单要素多产品产品明细信息
     * @param params 参数
     * @return ReportProdfeeDto
     * @see
     * @since 1.0
     */
    List<ReportProdadjDto> qureyProdlist(Map<String, Object> params);
}
