package com.joyintech.tams.invest.interf.trade.fromxp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	private InterfServiceLogService interfServiceLogService;

	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public Result<String> receive() {
		try {
			logger.info("接收{}接口数据开始", Constants.INTERF_SYSTEM_XP);
			List<InterfServiceLogDto> interfServiceLogDtoList = interfServiceLogService.getRunableLogList(null,Constants.INTERF_SYSTEM_XP);
			if (interfServiceLogDtoList == null || interfServiceLogDtoList.size() <= 0) {
				return new Result<String>().success();
			}
			interfServiceLogService.runInterfServiceLog(interfServiceLogDtoList);
			logger.info("接收{}接口数据结束", Constants.INTERF_SYSTEM_XP);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Result<String>().fail();
		}
		return new Result<String>().success();
	}
	
	@RequestMapping(value = "/receive/{id}", method = RequestMethod.GET)
	public Result<String> receive(@PathVariable("id") String id) {
		try {
			logger.info("接收{}接口数据开始", Constants.INTERF_SYSTEM_XP);
			List<InterfServiceLogDto> interfServiceLogDtoList = interfServiceLogService.getRunableLogList(id,Constants.INTERF_SYSTEM_XP);
			if (interfServiceLogDtoList == null || interfServiceLogDtoList.size() <= 0) {
				return new Result<String>().success();
			}
			interfServiceLogService.runInterfServiceLog(interfServiceLogDtoList);
			logger.info("接收{}接口数据结束", Constants.INTERF_SYSTEM_XP);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Result<String>().fail();
		}
		return new Result<String>().success();
	}

}
