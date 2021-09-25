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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.interf.trade.common.Constants;
import com.joyintech.tams.invest.interf.trade.common.InterfThreadPool;
import com.joyintech.tams.invest.interf.trade.common.Result;
import com.joyintech.tams.invest.interf.trade.common.dto.InterfTradeLogDto;
import com.joyintech.tams.invest.interf.trade.fromxp.dao.InterfTradeLogMapper;
/**
 * 交易接口日志Service
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see Result
 * @since 1.0
 */
@Service
public class InterfTradeLogService extends BaseService<InterfTradeLogDto> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取mapper对象
     */
    @Autowired
    private InterfTradeLogMapper interfTradeLogMapper;
    
    /**
	 *查询满足条件的交易接口日志
	* @param param
	* @return
	*/
	public List<InterfTradeLogDto> searchInterfTradeLog(InterfTradeLogDto interfTradeLogDto){
		return interfTradeLogMapper.searchInterfTradeLog(interfTradeLogDto);
	}
	
	/**
	 * 更新交易接口日志状态
	 * @param param
	 * @return
	 */
	public int updateInterfTradeLogStatus(Map<String,Object> param){
		return interfTradeLogMapper.updateInterfTradeLogStatus(param);
	}
	
	/**
	 * 运行投管业务逻辑
	 * @param interfTradeLogDtoList
	 */
	public void runInterfTradeLog(List<InterfTradeLogDto> interfTradeLogDtoList){
		InterfThreadPool pool = new InterfThreadPool(Constants.INTERF_THREAD_NUMBER);
    	try {
        	for(InterfTradeLogDto interfTradeLogDto:interfTradeLogDtoList) {
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
    	private InterfTradeLogDto interfTradeLogDto;
    	public RunTask(InterfTradeLogDto interfTradeLogDto) {
    		this.interfTradeLogDto = interfTradeLogDto;
    	}
		@Override
		public void run() {
			try {
				//TODO 调用投管业务系统
				logger.info(interfTradeLogDto.toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
    		} finally {
    		}
		}
    }

}
