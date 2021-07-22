package com.joyintech.service.risk.rskbotdetailreport.dao;

import com.joyintech.service.risk.rskbotdetailreport.dto.RskValBotDetailReportDto;

import java.util.List;
import java.util.Map;


public interface RskValBotDetailReportMapper {

    List<RskValBotDetailReportDto> queryForPage(Map<String, Object> pageParam);
}
