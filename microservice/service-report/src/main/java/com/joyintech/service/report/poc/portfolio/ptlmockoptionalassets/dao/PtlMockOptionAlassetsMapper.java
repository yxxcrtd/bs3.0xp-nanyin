package com.joyintech.service.report.poc.portfolio.ptlmockoptionalassets.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.portfolio.ptlmockoptionalassets.dto.PtlMockOptionAlassetsDto;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 模拟试算可选资产
 *
 * @author wl
 * @version 1.0 2020/04/19
 * @see PtlMockOptionAlassetsMapper
 * @since 1.0
 */
@Repository
public interface PtlMockOptionAlassetsMapper {

    List<PtlMockOptionAlassetsDto> selectForPage(Map<String, Object> map);
    
    List<SelectOptionDto> getFinprodId(Map<String, Object> map);
}
