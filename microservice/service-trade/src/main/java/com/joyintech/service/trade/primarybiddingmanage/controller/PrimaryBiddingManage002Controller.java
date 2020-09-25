package com.joyintech.service.trade.primarybiddingmanage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.service.trade.primarybiddingmanage.service.PrimaryBiddingManage002Service;

/**
 * 债券一级市场指令汇总确认微服务
 *
 * @author sjj
 * @version 1.0 2019年05月08日
 * @see PrimaryBiddingManage002Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/PrimaryBiddingManage002")
public class PrimaryBiddingManage002Controller {

	/**
     * 微服务service注入
     */
	@Autowired
	private PrimaryBiddingManage002Service primaryBiddingManage002Service;
	
	/**
	 * 获取entity <功能详细描述>
	 * 
	 * @param map
	 *            参数
	 * @return 结果值
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/getEntityByMap")
	public PrimaryBiddingManageDto getEntityByMap(@RequestBody Map<String, Object> map) {
		return primaryBiddingManage002Service.getEntityByMap(map);
	}
	/**
	 * 获取指令状态 <功能详细描述>
	 * 
	 * @param map
	 *            参数
	 * @return 结果值
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/getPrimaryBiddingManageOriginal")
	public PrimaryBiddingManageDto getPrimaryBiddingManageOriginal(@RequestBody Map<String, Object> map) {
		return primaryBiddingManage002Service.getPrimaryBiddingManageOriginal(map);
	}
	/**
	 * 获取交易对手名称 <功能详细描述>
	 *
	 * @param map
	 *            参数
	 * @return 结果值
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/getCounterName")
	public String getCounterName(@RequestBody Map<String, Object> map) {
		return primaryBiddingManage002Service.getCounterName(map);
	}
}
