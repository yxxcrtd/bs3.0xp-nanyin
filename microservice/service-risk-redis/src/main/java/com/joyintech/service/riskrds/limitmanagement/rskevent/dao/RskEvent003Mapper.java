package com.joyintech.service.riskrds.limitmanagement.rskevent.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent003Dto;

import java.util.List;

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

    // add by zhangrui1 公式配置 start
    /**
     * 查询所有参数值相对不变的参数信息作为下拉选项（当天值不受交易影响或固定值）
     * 
     * @return 下拉选项列表
     */
    List<SelectOptionDto> selectParamList();
    // add by zhangrui1 公式配置 end
}
