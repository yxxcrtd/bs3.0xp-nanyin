package com.joyintech.tams.report.queryanalysis.prosharecheck.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.queryanalysis.prosharecheck.dto.ProShareCheck001Dto;


@Repository
public interface ProShareCheck001Mapper {
    /**
     * 
     * 页面结果查询
     * @param map 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    List<ProShareCheck001Dto> searchPage(Map<String,Object> map);
}
