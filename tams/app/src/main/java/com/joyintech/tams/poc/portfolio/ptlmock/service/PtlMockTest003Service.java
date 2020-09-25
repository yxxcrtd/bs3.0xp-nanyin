package com.joyintech.tams.poc.portfolio.ptlmock.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.PtlMockTest003Dto;

import org.springframework.stereotype.Service;
import java.util.*;

/**
 *  模拟试算
 * @author yjm 
 * @version 1.0 2020/04/13
 * @see PtlMockTest003Service
 * @since 1.0
 */
@Service
public class PtlMockTest003Service extends CommonExportService<PtlMockTest003Dto> {
    /**
     * 根据组合代码获取组合信息
     * @param param
     * @return
     */
    public List<SelectOptionDto> getPtlInfo(Map<String, Object> param) {
        List<SelectOptionDto> list = restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME +"/PtlMockTest/getPtlInfo", param, SelectOptionDto.class);
        return  list;
    }
    
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<PtlMockTest003Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                ServiceApplication.REPORT_SERVICE_NAME + "/Ptlmockoptionalassets/findPage",
                pageParam, PtlMockTest003Dto.class);
    }
    
    /**
     * 资产代码下拉框
     * @param param
     * @return
     */
    public List<SelectOptionDto> getFinprodId(Map<String, Object> param) {
        List<SelectOptionDto> list = restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME +"/Ptlmockoptionalassets/getFinprodId", param, SelectOptionDto.class);
        return list;
    }
}
