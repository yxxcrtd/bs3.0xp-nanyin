
package com.joyintech.tams.disclosure.busicommon.report.dao;

import com.joyintech.tams.disclosure.busicommon.report.dto.ReportKfqgg001Dto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportMzjz001Dto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportOpenPlanDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportMzjz001Mapper
 * @since 1.0
 */
@Repository(value="ReportMzjz001Mapper")
public interface ReportMzjz001Mapper {
    
    /**
     * 根据产品代码查询公告信息
     * @param params 参数
     * @return ReportFxgg001Dto
     * @see
     * @since 1.0
     */
    ReportMzjz001Dto getProdInfo(Map<String, Object> params);
}
