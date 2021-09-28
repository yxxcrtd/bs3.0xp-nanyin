/*
 * 文件名：InterfConponentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.trade.fromzg.controller;

import java.util.List;

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
 * 资管接口接收Controller
 * 
 * @author yflv
 * @version 1.0 2021年09月28日
 * @see ZgInterfReceiveController
 * @since 1.0
 */
@RestController
@RequestMapping("/interf/zg")
public class ZgInterfReceiveController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 获取service对象
	 */
	@Autowired
	private InterfServiceLogService interfServiceLogService;

	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public Result<String> receive() {
		try {
			logger.info("接收{}接口数据开始", Constants.INTERF_SYSTEM_HSZG);
			List<InterfServiceLogDto> interfServiceLogDtoList = interfServiceLogService.getRunableLogList(Constants.INTERF_SYSTEM_HSZG);
			if (interfServiceLogDtoList == null || interfServiceLogDtoList.size() <= 0) {
				return new Result<String>().success();
			}
			interfServiceLogService.runInterfServiceLog(interfServiceLogDtoList);
			logger.info("接收{}接口数据结束", Constants.INTERF_SYSTEM_HSZG);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Result<String>().fail();
		}
		return new Result<String>().success();
	}

}
