
package com.joyintech.tams.disclosure.information.report.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportKfqgg001Mapper
 * @since 1.0
 */
@Repository(value="ReportKfqgg001Mapper")
public interface ReportKfqgg001Mapper {
    
    /**
     * 根据产品代码查询公告信息
     * @param params 参数
     * @return ReportFxgg001Dto
     * @see
     * @since 1.0
     */
    Map<String,Object> getProdInfo(Map<String, Object> params);

    List<Map<String,String>> queryKfqjh(Map<String, Object> params);
}
