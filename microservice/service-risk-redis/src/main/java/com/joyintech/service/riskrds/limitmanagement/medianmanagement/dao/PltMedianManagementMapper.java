package com.joyintech.service.riskrds.limitmanagement.medianmanagement.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.riskrds.limitmanagement.medianmanagement.dto.PltMedianManagementDto;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;

/**
 * 指标参数中间值管理
 * @author zhengjunping
 * @version 1.0 2019年11月19日
 * @see PltMedianManagementMapper
 * @since 1.0
 */
@Repository
public interface PltMedianManagementMapper {

    /**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @see
     * @since 1.0
     */
    List<PltMedianManagementDto> selectForPage(Map<String, Object> pageParam);
    
    List<SuggestDto> selectMedianManagement(Map<String, Object> pageParam);
}

