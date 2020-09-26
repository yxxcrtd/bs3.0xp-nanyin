package com.joyintech.tams.report.queryanalysis.equitysharecheck.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.dto.EquityShareCheck001Dto;

@Repository
public interface EquityShareCheck001Mapper {
    /**
     * 
     * 页面结果查询
     * @param map 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    List<EquityShareCheck001Dto> searchPage(Map<String,Object> map);
    
    
    /**
     * 请求产品结果list <功能详细描述>
     * 
     * @param param 查询条件
     * @return 产品结果list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryPrdNameList(Map<String, Object> param);
    
    /**
     * 请求识别标识结果list <功能详细描述>
     * 
     * @param param 查询条件
     * @return 识别标识结果list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryIndentifyMarkList(Map<String, Object> param);
    
}
