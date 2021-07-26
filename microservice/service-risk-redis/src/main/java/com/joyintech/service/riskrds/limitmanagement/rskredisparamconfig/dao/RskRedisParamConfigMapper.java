package com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.dao;

import com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.dto.RskRedisParamConfigDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * redis参数配置
 *
 * @author 裴家俊
 * @see RskRedisParamConfigMapper
 * @since 1.0
 */
@Repository
public interface RskRedisParamConfigMapper {
	
	 /**
     * 查询界面查询事件
     * @param pageParam 页面参数
     */
    List<RskRedisParamConfigDto> selectForPage(Map<String, Object> pageParam);

    RskRedisParamConfigDto getBasicInfo(@Param("commonBusinessType") String commonBusinessType, @Param("paraCode") String paraCode);

    int getRepeatCount(Map<String, Object> param);

}
