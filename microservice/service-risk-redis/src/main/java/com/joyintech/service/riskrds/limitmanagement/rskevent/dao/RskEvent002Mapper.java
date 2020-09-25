package com.joyintech.service.riskrds.limitmanagement.rskevent.dao;


import org.springframework.stereotype.Repository;

import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent002Dto;

/**
 * 风险事件管理编辑/详情页面实体数据操作类
 *
 * @author wangzhen
 * @see RskEvent001Mapper
 * @since 1.0
 */
@Repository
public interface RskEvent002Mapper {
	
	/**
     * 使用给定的sqlID为联想框检索分页数据。返回类型是Page<SuggestDto>
     *
     * @param eventCode 页面参数
     */
	RskEvent002Dto selectEventDetailInfoByCode(String eventCode);


}
