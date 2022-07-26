
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.disclosure.information.report.dto.ReportZjxsjgDto;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportZjxsjgMapper
 * @since 1.0
 */
@Repository
public interface ReportZjxsjgMapper {
    
    /**
     * 根据产品代码查询增加销售机构信息
     * @param params 参数    
     * @return ReportFxgg001Dto
     * @see
     * @since 1.0
     */
    ReportZjxsjgDto queryZjxsjg(Map<String, Object> params);

}
