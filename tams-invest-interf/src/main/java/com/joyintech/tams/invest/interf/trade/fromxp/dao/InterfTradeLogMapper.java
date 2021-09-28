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

import com.joyintech.tams.invest.interf.trade.common.dto.InterfTradeConfigDto;
import com.joyintech.tams.invest.interf.trade.common.dto.InterfTradeLogDto;

/**
 * 交易接口日志Mapper
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see InterfTradeLogMapper
 * @since 1.0
 */
@Repository
public interface InterfTradeLogMapper {
    

    /**
               *查询满足条件的交易接口日志
     * @param param
     * @return
     */
    List<InterfTradeLogDto> searchInterfTradeLog(InterfTradeLogDto interfTradeLogDto);
    
    /**
     * 更新满足条件的交易接口日志状态
     */
    int updateInterfTradeLogStatus(Map<String,Object> param);
    
    /**
     * 通过状态和id更新交易接口日志状态
     */
    int updateInterfTradeLogStatusByStatusAndId(Map<String,Object> param);
    
    /**
	     *查询满足条件的交易接口配置
	* @param param
	* @return
	*/
	List<InterfTradeConfigDto> searchInterfTradeConfig(InterfTradeConfigDto interfTradeConfigDto);
}
