/*
 * 文件名：InterfConponentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.trade.fromxp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.invest.interf.trade.common.Constants;
import com.joyintech.tams.invest.interf.trade.common.Result;
import com.joyintech.tams.invest.interf.trade.common.dto.InterfServiceLogDto;
import com.joyintech.tams.invest.interf.trade.fromxp.service.InterfServiceLogService;

/**
 * 信评接口接收Controller
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see XpInterfReceiveController
 * @since 1.0
 */
@RestController
@RequestMapping("/interf/xp")
public class XpInterfReceiveController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 获取service对象
	 */
	@Autowired
	private InterfServiceLogService interfTradeLogService;

	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public Result<String> receive() {
		try {
			logger.info("接收{}接口数据开始", Constants.INTERF_SYSTEM_XP);
			List<InterfServiceLogDto> interfTradeLogDtoList = this.getLogDtoList();
			if (interfTradeLogDtoList == null || interfTradeLogDtoList.size() <= 0) {
				return new Result<String>().success();
			}
			interfTradeLogService.runInterfTradeLog(interfTradeLogDtoList);
			logger.info("接收{}接口数据结束", Constants.INTERF_SYSTEM_XP);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Result<String>().fail();
		}
		return new Result<String>().success();
	}

	/*
	 * 乐观锁方式实现
	 */
	private List<InterfServiceLogDto> getLogDtoList() {
		List<InterfServiceLogDto> retInterfServiceLogDtoList = new ArrayList<InterfServiceLogDto>();
		InterfServiceLogDto where = new InterfServiceLogDto();
		where.setInterfType(Constants.INTERF_TYPE_RECEIVE);
		where.setInterfStatus(Constants.INTERF_STATUS_NEW);
		where.setInterfSystem(Constants.INTERF_SYSTEM_XP);
		List<InterfServiceLogDto> interfServiceLogDtoList = interfTradeLogService.searchInterfTradeLog(where);
		for (InterfServiceLogDto interfServiceLogDto : interfServiceLogDtoList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("interfSystem", Constants.INTERF_SYSTEM_XP);
			param.put("oldStatus", Constants.INTERF_STATUS_NEW);
			param.put("newStatus", Constants.INTERF_STATUS_PROCESS);
			param.put("id", interfServiceLogDto.getId());
			int result = interfTradeLogService.updateInterfTradeLogStatusByStatusAndId(param);
			if (result >= 1) {
				retInterfServiceLogDtoList.add(interfServiceLogDto);
			}
		}
		return retInterfServiceLogDtoList;
	}

	/*
	 * 分布式锁方式实现
	 * 
	 * private List<InterfServiceLogDto> getLogDtoList2() { //TODO
	 * 获取数据，然后更新数据状态为处理中，添加分布式锁开始 InterfServiceLogDto where = new
	 * InterfServiceLogDto(); where.setInterfType(Constants.INTERF_TYPE_RECEIVE);
	 * where.setInterfStatus(Constants.INTERF_STATUS_NEW);
	 * where.setInterfSystem(Constants.INTERF_SYSTEM_XP); List<InterfServiceLogDto>
	 * interfTradeLogDtoList = interfTradeLogService.searchInterfTradeLog(where);
	 * if(interfTradeLogDtoList==null || interfTradeLogDtoList.size()<=0) { return
	 * null; } List<String> ids =
	 * interfTradeLogDtoList.stream().map(InterfServiceLogDto::getId).collect(
	 * Collectors.toList()); Map<String,Object> param = new
	 * HashMap<String,Object>(); param.put("interfSystem",
	 * Constants.INTERF_SYSTEM_XP); param.put("interfStatus",
	 * Constants.INTERF_STATUS_PROCESS); param.put("ids", ids); int result =
	 * interfTradeLogService.updateInterfTradeLogStatus(param); //TODO
	 * 获取数据，然后更新数据状态为处理中，添加分布式锁结束 return interfTradeLogDtoList ; }
	 */

}
