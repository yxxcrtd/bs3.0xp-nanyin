/*
 * 文件名：PaActiveRemind001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
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
	     *查询满足条件的接口服务配置
	* @param param
	* @return
	*/
	List<InterfServiceConfigDto> searchInterfServiceConfig(InterfServiceConfigDto interfServiceConfigDto);
}
