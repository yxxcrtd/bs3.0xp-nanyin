package com.joyintech.service.riskrds.limitmanagement.rskevent.dao;


import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent003Dto;
import org.springframework.stereotype.Repository;

/**
 * 风险事件关联指标参数/详情页面实体数据操作类
 * @author 裴家俊
 * @see RskEvent003Mapper
 * @since 1.0
 */
@Repository
public interface RskEvent003Mapper {
	
	/**
     * @param eventCode 页面参数
     */
	RskEvent003Dto selectEventRelParaByCode(String eventCode);

}
