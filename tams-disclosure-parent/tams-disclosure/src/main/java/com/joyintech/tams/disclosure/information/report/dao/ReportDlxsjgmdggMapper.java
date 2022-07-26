
package com.joyintech.tams.disclosure.information.report.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportDlxsjgmdggMapper
 * @since 1.0
 */
@Repository
public interface ReportDlxsjgmdggMapper {
    
    /**
     * 查询代销机构名单
     * @param params 参数    
     * @return String
     * @see
     * @since 1.0
     */
    String queryDlxsjmd(Map<String, Object> params);

    /**
     * 查询存在代销机构数量
     * @param params 参数
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    Integer queryProdAgency(Map<String, Object> params);

}
