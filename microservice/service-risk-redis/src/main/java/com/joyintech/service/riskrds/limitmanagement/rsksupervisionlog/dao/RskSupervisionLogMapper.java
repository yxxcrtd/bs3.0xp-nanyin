package com.joyintech.service.riskrds.limitmanagement.rsksupervisionlog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.riskrds.limitmanagement.rsksupervisionlog.dto.RskSupervisionLogDto;

/**
 * 指标监督日志管理
 *
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionLogMapper
 * @since 1.0
 */
@Repository
public interface RskSupervisionLogMapper {
	  /**
     * 查询界面查询事件
     * @param pageParam 页面参数
     */
    List<RskSupervisionLogDto> selectLog(Map<String, Object> pageParam);
}