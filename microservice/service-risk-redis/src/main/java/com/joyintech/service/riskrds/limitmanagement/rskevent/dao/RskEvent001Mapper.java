package com.joyintech.service.riskrds.limitmanagement.rskevent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent001Dto;
import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.common.base.entity.RskEventItemRule;
import com.joyintech.tams.common.base.entity.RskEventParaRel;

/**
 * 风险事件管理
 *
 * @author wangzhen
 * @see RskEvent001Mapper
 * @since 1.0
 */
@Repository
public interface RskEvent001Mapper {
	
	 /**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @see
     * @since 1.0
     */
    List<RskEvent001Dto> selectEventList(Map<String, Object> pageParam);
    
    /**
     * 使用给定的sqlID为联想框检索分页数据。返回类型是Page<SuggestDto>
     *
     * @param pageParam 页面参数
     */
    List<SuggestDto> selectMultiOptionEvent(Map<String, Object> pageParam);
    

    int insertBasicByBasicInfo(RskEvent record);


    List<RskEvent> selectByEventName(String eventName);

    List<RskEvent> selectByEventNameNotEqCode(@Param("eventName") String eventName,
                                              @Param("eventCode") String eventCode);
    

    int deleteByEventCode(String eventCode);

    int insertList(List<RskEventItemRule> list);
    


    int insertParaList(List<RskEventParaRel> record);

    int deleteParaByEventCode(String eventCode);

	Integer isExistEventCode(String eventCode);
    
}
