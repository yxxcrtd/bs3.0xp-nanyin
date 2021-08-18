package com.joyintech.service.risk.creditrisklimitmanagement.linesupervision.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linesupervision.dto.LineSupervisionDto;
import org.springframework.stereotype.Repository;

@Repository
public interface LineSupervisionMapper {

    List<LineSupervisionDto> selectForPage(Map<String, Object> param);

    List<SelectOptionDto> getCustomerName(Map<String, Object> map);

    List<LineSupervisionDto> getCreditCustomerMst(Map<String, Object> param);
}