/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2021年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.exchange.entrust.etrexchange.hqjj.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.component.business.bond.BusiFinExercisePlanService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.*;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.service.EtrExchangeTradeJj001Service;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTradeQuotation001Dto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.MinPirceDto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.TrdOthCodeInfoDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易所指令交易（债券股票回购基金场内指令交易）
 * @author meifaxin
 * @version 1.0 2021年12月20日
 * @see EtrExchangeTradeJj001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrExchangeTradeJj001")
@RequestMapping("/etrexchange/hqjj/etrExchangeTradeJj001")
@Api(tags = "竞价回购指令下单")
public class EtrExchangeTradeJj001Controller extends BaseController{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPlatformDate iPlatformDate;
	/**
	 * 业务编号组件
	 */
	@Autowired(required=false)
	private BaseGetBusinessId bussGetBussinessIdService;


	@Autowired
	private EtrExchangeTradeJj001Service etrExchangeTradeJj001Service;


	@Autowired
	private BusiFinExercisePlanService busiFinExercisePlanService;

	/**
	 * 查询交易行情信息
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ApiOperation(value="查询交易行情信息",notes = "查询交易行情信息")
	@ResponseBody
	@PostMapping("findTradeQuotationsPage")
	public Message<Page<EtrExchangeTradeQuotationResponse>> findTradeQuotationsPage(@RequestBody EtrExchangeTradeQuotationRequest request){
		try {
			Page<EtrExchangeTradeQuotationResponse> list = etrExchangeTradeJj001Service.findTradeQuotationsPage(request);
			return new Message<Page<EtrExchangeTradeQuotationResponse>>().success().setData(list);
		}catch (Exception e) {
			logger.error("查询指令管理分页数据异常",e);
			return new Message<Page<EtrExchangeTradeQuotationResponse>>().fail().setMessage("查询交易行情信息数据异常");
		}
	}

	/**
	 * 查询交易行情信息
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ApiOperation(value="查询竞价交易行情信息",notes = "查询竞价交易行情信息")
	@ResponseBody
	@PostMapping("findQuotations")
	public Message<EtrExchangeTradeQuotationHqResponse> findQuotations(@RequestBody String secId) {
		try {
			secId = secId.replaceAll("\"","");
			EtrExchangeTradeQuotationHqResponse quotations = etrExchangeTradeJj001Service.findQuotations(secId);
			return new Message<EtrExchangeTradeQuotationHqResponse>().success().setData(quotations);
		}catch (Exception e) {
			logger.error("查询竞价易行情数据异常",e);
			return new Message<EtrExchangeTradeQuotationHqResponse>().fail().setMessage("查询竞价交易行情信息数据异常");
		}
	}

	/**
	 * 查询盘后行情信息
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ApiOperation(value="查询盘后行情信息",notes = "查询盘后行情信息")
	@ResponseBody
	@PostMapping("findAfterHoursPage")
	public Message<Page<EtrExchangeTradeQuotation001Dto>> findAfterHoursPage(@RequestBody EtrExchangeTradeQuotationRequest request){
		try {
			Page<EtrExchangeTradeQuotation001Dto> page = etrExchangeTradeJj001Service.findAfterHoursPage(request);
			return new Message<Page<EtrExchangeTradeQuotation001Dto>>().success().setData(page);
		}catch (Exception e) {
			logger.error("查询指令管理分页数据异常",e);
			return new Message<Page<EtrExchangeTradeQuotation001Dto>>().fail().setMessage("查询盘后行情信息数据异常");
		}
	}

	/**
	 * 查询固定收益行情数据
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ApiOperation(value="查询固定收益行情数据",notes = "查询固定收益行情数据")
	@ResponseBody
	@PostMapping("findFixedIncomePage")
	public Message<Page<EtrExchangeTradeQuotation001Dto>> findFixedIncomePage(@RequestBody EtrExchangeTradeQuotationRequest request){
		try {
			Page<EtrExchangeTradeQuotation001Dto> page = etrExchangeTradeJj001Service.findFixedIncomePage(request);
			return new Message<Page<EtrExchangeTradeQuotation001Dto>>().success().setData(page);
		}catch (Exception e) {
			logger.error("查询指令管理分页数据异常",e);
			return new Message<Page<EtrExchangeTradeQuotation001Dto>>().fail().setMessage("查询固定收益行情数据异常");
		}
	}


	/**
	 * 查询盘后行情信息
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ApiOperation(value="查询盘后行情信息",notes = "查询盘后行情信息")
	@ResponseBody
	@PostMapping("findRepoPage")
	public Page<EtrExchangeTradeQuotation001Dto> findRepoPage(@RequestBody EtrExchangeTradeQuotationRequest request){
		Page<EtrExchangeTradeQuotation001Dto> page = new Page<EtrExchangeTradeQuotation001Dto>();
		return page;
	}

	/***
	 * 初始化行情
	 * @return
	 */
	@RequestMapping(value = "/initHq")
	public String initHq(Model model){
		return "invest/entrust/etrexchangetrade/EtrExchangeTradeHq";
	}

}
	
