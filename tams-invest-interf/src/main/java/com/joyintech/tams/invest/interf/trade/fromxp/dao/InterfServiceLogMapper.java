package com.joyintech.tams.invest.interf.trade.fromxp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.interf.trade.common.dto.InterfServiceConfigDto;
import com.joyintech.tams.invest.interf.trade.common.dto.InterfServiceLogDto;

/**
 * 接口服务日志Mapper
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see InterfServiceLogMapper
 * @since 1.0
 */
@Repository
public interface InterfServiceLogMapper {
    

    /**
               *查询满足条件的接口服务日志
     * @param param
     * @return
     */
    List<InterfServiceLogDto> searchInterfServiceLog(InterfServiceLogDto interfServiceLogDto);
    
    /**
     * 更新满足条件的接口服务日志状态
     */
    int updateInterfServiceLogStatus(Map<String,Object> param);
    
    /**
     * 通过状态和id更新接口服务日志状态
     */
    int updateInterfServiceLogStatusByStatusAndId(Map<String,Object> param);
    
    /**
     * 更新接口服务日志
     */
    int updateInterfServiceLog(InterfServiceLogDto interfServiceLogDto);
    
    /**
	     *查询满足条件的接口服务配置
	* @param param
	* @return
	*/
	List<InterfServiceConfigDto> searchInterfServiceConfig(InterfServiceConfigDto interfServiceConfigDto);
}
