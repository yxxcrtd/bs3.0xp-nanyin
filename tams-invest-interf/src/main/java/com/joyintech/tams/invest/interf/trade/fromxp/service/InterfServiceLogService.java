/*
 * 文件名：PaActiveRemind001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.trade.fromxp.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.interf.trade.common.Constants;
import com.joyintech.tams.invest.interf.trade.common.InterfThreadPool;
import com.joyintech.tams.invest.interf.trade.common.dto.InterfServiceConfigDto;
import com.joyintech.tams.invest.interf.trade.common.dto.InterfServiceLogDto;
import com.joyintech.tams.invest.interf.trade.common.util.SpringReflectUtils;
import com.joyintech.tams.invest.interf.trade.fromxp.dao.InterfServiceLogMapper;
/**
 * 交易接口日志Service
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see InterfServiceLogService
 * @since 1.0
 */
@Service
public class InterfServiceLogService extends BaseService<InterfServiceLogDto> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final Map<String, InterfServiceConfigDto> CONFIG_MAP = new ConcurrentHashMap<String, InterfServiceConfigDto>();

    /**
     * 获取mapper对象
     */
    @Autowired
    private InterfServiceLogMapper interfTradeLogMapper;
    
    /**
	 *查询满足条件的交易接口日志
	* @param param
	* @return
	*/
	public List<InterfServiceLogDto> searchInterfTradeLog(InterfServiceLogDto interfTradeLogDto){
		return interfTradeLogMapper.searchInterfServiceLog(interfTradeLogDto);
	}
	
	/**
	 * 更新交易接口日志状态
	 * @param param
	 * @return
	 */
	public int updateInterfTradeLogStatus(Map<String,Object> param){
		return interfTradeLogMapper.updateInterfServiceLogStatus(param);
	}
	
	/**
     * 通过状态和id更新交易接口日志状态
     */
	public int updateInterfTradeLogStatusByStatusAndId(Map<String,Object> param) {
    	return interfTradeLogMapper.updateInterfServiceLogStatusByStatusAndId(param);
    }
	
	/**
	 * 运行投管业务逻辑
	 * @param interfTradeLogDtoList
	 */
	public void runInterfTradeLog(List<InterfServiceLogDto> interfTradeLogDtoList){
		InterfThreadPool pool = new InterfThreadPool(Constants.INTERF_THREAD_NUMBER);
    	try {
        	for(InterfServiceLogDto interfTradeLogDto:interfTradeLogDtoList) {
        		if(StringUtil.isEmpty(interfTradeLogDto.getInterfCode())) {
        			logger.warn("接口编码为空，不可调用业务系统，对象：{}",interfTradeLogDto.toString());
        			continue;
        		}
        		RunTask task = new RunTask(interfTradeLogDto);
        		pool.submit(task);
        	}
    	} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			pool.close();
		}
	}
	
	private class RunTask implements Runnable {
    	private InterfServiceLogDto interfTradeLogDto;
    	public RunTask(InterfServiceLogDto interfTradeLogDto) {
    		this.interfTradeLogDto = interfTradeLogDto;
    	}
		@Override
		public void run() {
			try {
				logger.debug("接口日志对象信息:{}",interfTradeLogDto.toString());
				InterfServiceConfigDto interfTradeConfigDto = CONFIG_MAP.get(interfTradeLogDto.getInterfCode());
				if(interfTradeConfigDto==null) {
					InterfServiceConfigDto where = new InterfServiceConfigDto();
					where.setInterfCode(interfTradeLogDto.getInterfCode());
					where.setInterfSystem(interfTradeLogDto.getInterfSystem());
					where.setInterfStatus(Constants.INTERF_CONFIG_STATUS_ENABLE);
					List<InterfServiceConfigDto> interfTradeConfigDtoList = interfTradeLogMapper.searchInterfServiceConfig(where);
					if(interfTradeConfigDtoList==null || interfTradeConfigDtoList.size()<=0) {
						logger.warn("接口编码:{}，没有配置业务处理映射关系或映射关系不可用",interfTradeLogDto.getInterfCode());
						return ;
					}
					interfTradeConfigDto = interfTradeConfigDtoList.get(0);
					CONFIG_MAP.put(interfTradeLogDto.getInterfCode(),interfTradeConfigDto);
				}
				String serviceName = interfTradeConfigDto.getBusinessService();
				String methodName = interfTradeConfigDto.getBusinessMethod();
				if(StringUtil.isEmpty(serviceName)) {
					logger.warn("接口配置:{}，没有配置业务系统服务名",interfTradeConfigDto.getInterfCode());
					return ;
				}
				if(StringUtil.isEmpty(methodName)) {
					methodName = Constants.INTERF_DEFAULT_METHOD;
				}
				Object[] params = {interfTradeLogDto.getId(),interfTradeLogDto.getInterfMsgbody()};
				SpringReflectUtils.springInvokeMethod(serviceName, methodName, params);
			} catch (Exception e) {
				logger.error(e.getMessage());
    		} finally {
    		}
		}
    }

}
