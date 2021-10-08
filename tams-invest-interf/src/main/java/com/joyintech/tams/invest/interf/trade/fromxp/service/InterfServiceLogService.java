package com.joyintech.tams.invest.interf.trade.fromxp.service;

import java.util.ArrayList;
import java.util.Date;
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
 * 接口服务日志Service
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
    private InterfServiceLogMapper interfServiceLogMapper;
    
    /**
	 *查询满足条件的接口服务日志
	* @param param
	* @return
	*/
	public List<InterfServiceLogDto> searchInterfServiceLog(InterfServiceLogDto interfServiceLogDto){
		return interfServiceLogMapper.searchInterfServiceLog(interfServiceLogDto);
	}
	
	/**
	 * 更新接口服务日志状态
	 * @param param
	 * @return
	 */
	public int updateInterfServiceLogStatus(Map<String,Object> param){
		return interfServiceLogMapper.updateInterfServiceLogStatus(param);
	}
	
	/**
     * 通过状态和id更新接口服务日志状态
     */
	public int updateInterfServiceLogStatusByStatusAndId(Map<String,Object> param) {
    	return interfServiceLogMapper.updateInterfServiceLogStatusByStatusAndId(param);
    }
	
	/**
     * 更新接口服务日志
     */
	public int updateInterfServiceLog(InterfServiceLogDto interfServiceLogDto) {
		return interfServiceLogMapper.updateInterfServiceLog(interfServiceLogDto);
	}
	
	/**
     * 更新接口服务日志
     */
	public int updateInterfServiceLog(String id,boolean success,String executeInfo) {
		InterfServiceLogDto interfServiceLogDto = new InterfServiceLogDto();
		interfServiceLogDto.setId(id);
		if(success) {
			interfServiceLogDto.setNewStatus(Constants.INTERF_STATUS_SUCCESS);
		}else {
			interfServiceLogDto.setNewStatus(Constants.INTERF_STATUS_FAIL);
		}
		interfServiceLogDto.setExecuteInfo(executeInfo);
		return interfServiceLogMapper.updateInterfServiceLog(interfServiceLogDto);
	}
	
	/**
	 * 获取可处理的接口服务日志
	 * @param interfSystem
	 * @return
	 */
	public List<InterfServiceLogDto> getRunableLogList(String id,String interfSystem) {
		List<InterfServiceLogDto> runableLogList = new ArrayList<InterfServiceLogDto>();
		InterfServiceLogDto where = new InterfServiceLogDto();
		if(StringUtil.isNotEmpty(id)) {
			where.setId(id);
		}
		where.setInterfSystem(interfSystem);
		where.setInterfType(Constants.INTERF_TYPE_RECEIVE);
		where.setInterfStatus(Constants.INTERF_STATUS_NEW);
		List<InterfServiceLogDto> interfServiceLogDtoList = this.searchInterfServiceLog(where);
		for (InterfServiceLogDto interfServiceLogDto : interfServiceLogDtoList) {
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("interfSystem", interfSystem);
//			param.put("interfType", Constants.INTERF_TYPE_RECEIVE);
//			param.put("oldStatus", Constants.INTERF_STATUS_NEW);
//			param.put("newStatus", Constants.INTERF_STATUS_PROCESS);
//			param.put("id", interfServiceLogDto.getId());
//			int result = this.updateInterfServiceLogStatusByStatusAndId(param);
			interfServiceLogDto.setNewStatus(Constants.INTERF_STATUS_PROCESS);
			interfServiceLogDto.setInterfStatus(Constants.INTERF_STATUS_NEW);
			int result = this.updateInterfServiceLog(interfServiceLogDto);
			if (result >= 1) {
				runableLogList.add(interfServiceLogDto);
			}
		}
		return runableLogList;
	}
	
	/**
	 * 运行投管业务逻辑
	 * @param interfServiceLogDtoList
	 */
	public void runInterfServiceLog(List<InterfServiceLogDto> interfServiceLogDtoList){
		InterfThreadPool pool = new InterfThreadPool(Constants.INTERF_THREAD_NUMBER);
    	try {
        	for(InterfServiceLogDto interfServiceLogDto:interfServiceLogDtoList) {
        		if(StringUtil.isEmpty(interfServiceLogDto.getInterfCode())) {
        			interfServiceLogDto.setNewStatus(Constants.INTERF_STATUS_FAIL);
        			interfServiceLogDto.setInterfStatus(Constants.INTERF_STATUS_PROCESS);
        			interfServiceLogDto.setExecuteInfo("接口编码为空，不可调用业务系统");
        			this.updateInterfServiceLog(interfServiceLogDto);
        			logger.warn("接口编码为空，不可调用业务系统，对象：{}",interfServiceLogDto.toString());
        			continue;
        		}
        		RunTask task = new RunTask(interfServiceLogDto);
        		pool.submit(task);
        	}
    	} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			pool.close();
		}
	}
	
	/**
	 * 判断缓存是否过期
	 * @param date
	 * @return
	 */
	private boolean cacheInvalid(Date date) {
		if(date==null) {
			return true;
		}
		Date cacheTime = new Date(date.getTime() + 60*1000);
		Date nowTime = new Date();
		if(nowTime.after(cacheTime)) {
			return true;
		}else {
			return false;
		}
	}
	
	private class RunTask implements Runnable {
    	private InterfServiceLogDto interfServiceLogDto;
    	public RunTask(InterfServiceLogDto interfServiceLogDto) {
    		this.interfServiceLogDto = interfServiceLogDto;
    	}
		@Override
		public void run() {
			try {
				logger.debug("接口日志对象信息:{}",interfServiceLogDto.toString());
				InterfServiceConfigDto interfServiceConfigDto = CONFIG_MAP.get(interfServiceLogDto.getInterfCode());
				if(interfServiceConfigDto==null || cacheInvalid(interfServiceConfigDto.getUpdateTime())) {
					InterfServiceConfigDto where = new InterfServiceConfigDto();
					where.setInterfCode(interfServiceLogDto.getInterfCode());
					where.setInterfSystem(interfServiceLogDto.getInterfSystem());
					where.setInterfStatus(Constants.INTERF_CONFIG_STATUS_ENABLE);
					where.setInterfType(Constants.INTERF_TYPE_RECEIVE);
					List<InterfServiceConfigDto> interfServiceConfigDtoList = interfServiceLogMapper.searchInterfServiceConfig(where);
					if(interfServiceConfigDtoList==null || interfServiceConfigDtoList.size()<=0) {
						interfServiceLogDto.setNewStatus(Constants.INTERF_STATUS_FAIL);
						interfServiceLogDto.setInterfStatus(Constants.INTERF_STATUS_PROCESS);
						interfServiceLogDto.setExecuteInfo("接口编码:"+interfServiceLogDto.getInterfCode()+"，没有添加接口服务配置或接口服务配置不可用");
						updateInterfServiceLog(interfServiceLogDto);
						logger.warn("接口编码:{}，没有添加接口服务配置或接口服务配置不可用",interfServiceLogDto.getInterfCode());
						return ;
					}
					interfServiceConfigDto = interfServiceConfigDtoList.get(0);
					interfServiceConfigDto.setUpdateTime(new Date());
					CONFIG_MAP.put(interfServiceLogDto.getInterfCode(),interfServiceConfigDto);
					
				}
				String serviceName = interfServiceConfigDto.getBusinessService();
				String methodName = interfServiceConfigDto.getBusinessMethod();
				if(StringUtil.isEmpty(serviceName)) {
					interfServiceLogDto.setNewStatus(Constants.INTERF_STATUS_FAIL);
					interfServiceLogDto.setInterfStatus(Constants.INTERF_STATUS_PROCESS);
					interfServiceLogDto.setExecuteInfo("接口服务配置:"+interfServiceConfigDto.getInterfCode()+"，没有配置业务系统服务名");
					updateInterfServiceLog(interfServiceLogDto);
					logger.warn("接口服务配置:{}，没有配置业务系统服务名",interfServiceConfigDto.getInterfCode());
					return ;
				}
				if(StringUtil.isEmpty(methodName)) {
					methodName = Constants.INTERF_DEFAULT_METHOD;
				}
				Object[] params = {interfServiceLogDto.getId(),interfServiceLogDto.getInterfMsgbody()};
				SpringReflectUtils.springInvokeMethod(serviceName, methodName, params);
			} catch (Exception e) {
				logger.error(e.getMessage());
    		} finally {
    		}
		}
    }

}
