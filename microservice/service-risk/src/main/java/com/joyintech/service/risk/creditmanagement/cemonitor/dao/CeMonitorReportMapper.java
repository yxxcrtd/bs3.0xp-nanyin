package com.joyintech.service.risk.creditmanagement.cemonitor.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.creditmanagement.cemonitor.dto.CreditLinesDealDto;

/**
 * 授信额度监测
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeMonitorReportMapper
 * @since 1.0
 */
@Repository
public interface CeMonitorReportMapper {

	List<CreditLinesDealDto> selectOrgForPage(Map<String, Object> pageParam);
    List<CreditLinesDealDto> selectAssetForPage(Map<String, Object> pageParam);
    List<CreditLinesDealDto> selectAssetGroupForPage(Map<String, Object> pageParam);
}
