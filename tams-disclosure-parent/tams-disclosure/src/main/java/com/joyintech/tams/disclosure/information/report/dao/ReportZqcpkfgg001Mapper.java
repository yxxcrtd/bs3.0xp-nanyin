
package com.joyintech.tams.disclosure.information.report.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportZqcpkfgg001Mapper
 * @since 1.0
 */
@Repository
public interface ReportZqcpkfgg001Mapper {


    Map<String, Object> getProdInfo(String portnum);

    /**
     * 后续申购赎回
     * @param searchParam
     * @return
     */
    List<Map<String, String>> queryOpenList(Map<String, Object> searchParam);

    /**
     * 历史申购赎回
     * @param searchParam
     * @return
     */
    List<Map<String, Object>> queryOpenHisList(Map<String, Object> searchParam);

    Map<String, Object> queryValueMap(Map<String, Object> searchParam);
}
