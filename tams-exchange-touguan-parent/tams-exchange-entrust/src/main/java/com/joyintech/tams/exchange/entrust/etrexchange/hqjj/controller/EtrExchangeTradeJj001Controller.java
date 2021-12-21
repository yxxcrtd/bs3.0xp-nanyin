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
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.component.business.bond.BusiFinExercisePlanService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeQuotationRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeQuotationResponse;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeJjSaveRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.FinprodMarketInfoJjRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.service.EtrExchangeTradeJj001Service;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTradeQuotation001Dto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.MinPirceDto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.TrdOthCodeInfoDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
@PageId("JjEtrExchangeTrade001")
@RequestMapping("/etrexchange/hqjj/jjetrExchangeTrade001")
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
	private EtrExchangeTradeJj001Service jjEtrExchangeTrade001Service;


	@Autowired
	private BusiFinExercisePlanService busiFinExercisePlanService;


	public static final String ENTRUST_RECORD_NEW = "new";

	public static final String ENTRUST_RECORD_MODIFY = "modify";



	/**
	 * 查询交易行情信息
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@PostMapping("findTradeQuotationsPage")
	@ApiOperation(value = "查询交易行情信息", notes = "查询交易行情信息")
	public Message<Page<EtrExchangeTradeQuotationResponse>> findTradeQuotationsPage(@RequestBody EtrExchangeTradeQuotationRequest request){
		try {
			Page<EtrExchangeTradeQuotationResponse> list = jjEtrExchangeTrade001Service.findTradeQuotationsPage(request);
			return new Message<Page<EtrExchangeTradeQuotationResponse>>().success().setData(list);
		}catch (Exception e) {
			logger.error("查询指令管理分页数据异常",e);
			return new Message<Page<EtrExchangeTradeQuotationResponse>>().fail().setMessage("查询指令管理分页数据异常");
		}
	}


	/**
	 * 查询盘后行情信息
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findAfterHoursPage")
	public Page<EtrExchangeTradeQuotation001Dto> findAfterHoursPage(){
		return this.jjEtrExchangeTrade001Service.findAfterHoursPage(RequestUtil.getPageParam());
	}

	/**
	 * 查询固定收益行情数据
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findFixedIncomePage")
	public Page<EtrExchangeTradeQuotation001Dto> findFixedIncomePage(){
		return this.jjEtrExchangeTrade001Service.findFixedIncomePage(RequestUtil.getPageParam());
	}


	/**
	 * 查询盘后行情信息
	 * <一句话功能简述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findRepoPage")
	public Page<EtrExchangeTradeQuotation001Dto> findRepoPage(){
		Page<EtrExchangeTradeQuotation001Dto> page = new Page<EtrExchangeTradeQuotation001Dto>();
		return page;
	}



	/**
	 * 保存按钮点击时的check
	 *
	 * @param dto dto
	 * @param result BindingResult
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@ApiOperation(value = "保存提交check", notes = "保存提交check")
	@PostMapping("checkSaveError")
	@ResponseBody
	public Message<AjaxResult> checkSaveError(@RequestBody EtrExchangeTradeJjSaveRequest saveRequest, BindingResult br)
			throws Exception {
		try {
			AjaxResult ajax= jjEtrExchangeTrade001Service.checkSaveErrorMethod(saveRequest, br);
			if(null!=ajax){
				return new Message<AjaxResult>().success().setData(ajax);
			}else{
				return new Message<AjaxResult>().success().setMessage("校验通过");
			}
		}catch (Exception e) {
			logger.error("多组合指令新建保存/提交校验异常",e);
			return new Message<AjaxResult>().fail().setMessage("校验失败");
		}
	}

	/**
	 * 保存/提交
	 *
	 * @param dto
	 *            dto
	 * @param br
	 *            br
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody EtrExchangeTradeJjSaveRequest dto, BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		//业务类型
		String businessType = String.valueOf("");
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())) {
			// add  start
			if (StringUtils.equals(dto.getPs(), DictCodeListExt2.交易方向_配股.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_配股指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_股票竞价指令.getValue();
			} else if (StringUtils.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_新股申购指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_新股申购指令.getValue();
			} else {//add  end
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_股票竞价指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_股票竞价指令.getValue();
			}
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_基金竞价指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_基金竞价指令.getValue();
		}
		if ((dto.getPs().equals(DictCodeListExt3.交易方向_正回购.getValue()) || dto.getPs().equals(DictCodeListExt3.交易方向_逆回购.getValue())) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗债券质押式回购指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_大宗债券质押式回购指令.getValue();
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗股票指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_大宗股票指令.getValue();
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗基金指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_大宗基金指令.getValue();
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗债券指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_大宗债券指令.getValue();
		}
		//add start
		if(dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())){
			if(StringUtils.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())){
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_债券回售指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_债券回售指令.getValue();
			}else if(StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_配债.getValue())){
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_配债指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_配债指令.getValue();
			}else if(StringUtils.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())){
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_可转债申购指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_可转债申购指令.getValue();
			}else if(StringUtils.equals(dto.getPs(), DictCodeListExt1.交易方向_债转股.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_债转股指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_债转股指令.getValue();
			} else if(StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_质押券入库.getValue()) || StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_质押券出库.getValue())){
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_标准券出入库指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_标准券出入库指令.getValue();
			}else{
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_债券竞价指令.getValue());
				businessType = DictCodeListExt3.共通业务类型_债券竞价指令.getValue();
			}
		}
		// 债券质押式回购竞价指令
		if ((dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
				|| dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()))
				&& dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_债券质押式回购竞价指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_债券质押式回购竞价指令.getValue();
		}
		// 大宗债券质押式回购指令
		if ((dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
				|| dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()))
				&& dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗债券质押式回购指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_大宗债券质押式回购指令.getValue();
		}
		if(dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_固定收益平台.getValue())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_上交所固定收益平台现券交易指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_上交所固定收益平台现券交易指令.getValue();
		}
		//add end
		if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
			BussinessCodeParameter param = new BussinessCodeParameter();
			if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())) {
				// add  start
				if (StringUtils.equals(dto.getPs(), DictCodeListExt2.交易方向_配股.getValue())) {
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_配股指令));
				} else if (StringUtils.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_新股申购指令));
				} else {
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_股票竞价指令));
				}
			}
			if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())) {
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_基金竞价指令));
			}
			if ((dto.getPs().equals(DictCodeListExt3.交易方向_正回购.getValue()) || dto.getPs().equals(DictCodeListExt3.交易方向_逆回购.getValue())) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_大宗债券质押式回购指令));
			}
			if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_大宗股票指令));
			}
			if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_大宗基金指令));
			}
			if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_大宗债券指令));
			}
			//add start
			if(dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())){
				if(StringUtils.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())){
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_债券回售指令));
				}else if(StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_配债.getValue())){
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_配债指令));
				}else if(StringUtils.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())){
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_可转债申购指令));
				}else if(StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_债转股.getValue())){
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_债转股指令));
				}else if(StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_质押券入库.getValue()) || StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_质押券出库.getValue())){
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_标准券出入库指令));
				}else{
					param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_债券竞价指令));
				}
			}
			// 债券质押式回购竞价指令
			if ((dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
					|| dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()))
					&& dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())) {
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_债券质押式回购竞价指令));
			}
			// 大宗债券质押式回购指令
			if ((dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
					|| dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()))
					&& dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_大宗债券质押式回购指令));
			}
			if(dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_固定收益平台.getValue())){
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_上交所固定收益平台现券交易指令));
			}
			//add end
			String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(param) : IDGenerator.generate();
			dto.setEntrustId(code);
		}


		// ----------add by zhangrui1 20200703 预占功能 start----------
		if (StringUtil.equals(BussinessConstant.SUBMIT, dto.getOperateType())) {
			String finprodType2 = "";
			if(dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
				finprodType2 =  DictCodeList.平安资产类型_债券.getValue();
			}
			else if(dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
				finprodType2 =  DictCodeList.平安资产类型_基金.getValue();
			}
			else if(dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
				finprodType2 =  DictCodeListExt2.平安资产类型_股票.getValue();
			}
			else if((dto.getPs().equals(DictCodeListExt3.交易方向_正回购.getValue()) || dto.getPs().equals(DictCodeListExt3.交易方向_逆回购.getValue())) ) {
				finprodType2 =  DictCodeList.平安资产类型_质押式回购.getValue();
			}
			//债转股需要走批量接口
			String infCode;
			if(StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_债转股.getValue())) {
				infCode = BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue();
			}
			else {
				infCode = BussinessDictCodeListRds.接口系统ID_预占接口.getValue();
			}
			AjaxResult ar = EntrustRdsCommonAop.doPreOccupy(dto,
					infCode, finprodType2);
			if (StringUtil.isNotEmpty(ar)) {
				return ar;
			}
		}
		// ----------add by zhangrui1 20200703 预占功能 end----------


		/*
		 * if 画面.金融产品类型（投管）.股票 and 画面.交易平台 = 数据字典.交易平台.竞价交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.股票竞价指令
		 *
		 * if 画面.金融产品类型（投管）.基金 and 画面.交易平台 = 数据字典.交易平台.竞价交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.基金竞价指令
		 *
		 * if 画面.委托方向 = （数据字典.交易方向.正回购 or 逆回购） and 画面.交易平台 = 数据字典.交易平台.大宗交易平台
		 * 指令业务类型 = 数据字典.共通业务类型.大宗债券质押式回购指令
		 *
		 * if 画面.金融产品类型（投管）.股票 and 画面.交易平台 = 数据字典.交易平台.大宗交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.大宗股票指令
		 *
		 * if 画面.金融产品类型（投管）.基金 and 画面.交易平台 = 数据字典.交易平台.大宗交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.大宗基金指令
		 *
		 * if 画面.金融产品类型（投管）.债券and 画面.交易平台 = 数据字典.交易平台.大宗交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.大宗债券指令
		 */
		dto.setBusinessTypeName(SysDataUtil.getNameFromDataDict(DictCode.共通业务类型, businessType));
		dto.setInvManagerId(LoginUserUtil.getUserName());// 投资经理
		dto.setCreateUser(LoginUserUtil.getUserName());// 用户名
		dto.setCreateDept(LoginUserUtil.getDeptCode());// 用户部门
		dto.setCreateTime(new Date());// 创建时间
		ProcessParameter<EtrExchangeTradeJjSaveRequest> processParameter = new ProcessParameter<EtrExchangeTradeJjSaveRequest>();
		WorkFlowParameter<EtrExchangeTradeJjSaveRequest> params = jjEtrExchangeTrade001Service.getWorkFlowParameter(dto);
		params.setPageId("EtrExchangeTradeMain");
		//params.setElementId(dto.getElementId());
		params.setBusinessType(businessType);
		params.setBusinessCode(dto.getEntrustId());
		params.setComment(entity -> getMessage("EtrExchangeTrade003.entrustId") + "(" + entity.getEntrustId() + ")"
				+ "-" + "业务类型"+ "(" + entity.getBusinessTypeName() + ")-"
				+ "平台日" + "(" + DateUtil.format(iPlatformDate.getSysDate()) + ")");
		processParameter.setWorkFlowParameter(params);
		String msg ="";// jjEtrExchangeTrade001Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

	@ResponseBody
	@RequestMapping("/selectCounterIdByCounterType")
	public List<SelectOptionDto> selectCounterIdByCounterType(){
		return jjEtrExchangeTrade001Service.selectCounterIdByCounterType(RequestUtil.getPageParam());
	}

	@ResponseBody
	@RequestMapping("/getSecAcctInfo")
	public List<SelectOptionDto> getSecAcctInfo(){
		return jjEtrExchangeTrade001Service.getSecAcctInfo(RequestUtil.getPageParam());
	}

	@ResponseBody
	@RequestMapping("/getFinprodId")
	public List<SelectOptionDto> getFinprodId(){
		return jjEtrExchangeTrade001Service.getFinprodId(RequestUtil.getPageParam());
	}

	@ResponseBody
	@PostMapping("/getFinprodMarketInfo")
	@ApiOperation(value = "查询资产列表", notes = "查询资产列表")
	public Message<Page<SelectOptionDto>> getFinprodMarketInfo(@RequestBody FinprodMarketInfoJjRequest dto){
		try {
			Map<String, Object> map = BeanUtil.object2Map(dto);
			Page<SelectOptionDto> tempList = jjEtrExchangeTrade001Service.getFinprodMarketInfo(map);
			return new Message<Page<SelectOptionDto>>().success().setData(tempList);
		} catch (Exception e) {
			logger.error("查询功能异常", e);
			return new Message<Page<SelectOptionDto>>().fail().setMessage("查询异常");
		}
	}

	@ResponseBody
	@RequestMapping("/getFinprodMarketInfoPosition")
	public Page<SelectOptionDto> getFinprodMarketInfoPosition(){
		return jjEtrExchangeTrade001Service.getFinprodMarketInfoPosition(RequestUtil.getPageParam());
	}


	@ResponseBody
	@RequestMapping("/getPtlSecAcctInfo")
	public List<SelectOptionDto> getPtlSecAcctInfo(){
		return jjEtrExchangeTrade001Service.getPtlSecAcctInfo(RequestUtil.getPageParam());
	}

	/**
	 * 对手方交易商联想文本框初始化。
	 *
	 * @return 联想文本框
	 */
	@ResponseBody
	@RequestMapping("/selectCounterDealer")
	public List<SuggestDto> selectCounterDealer() {
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		return jjEtrExchangeTrade001Service.selectCounterDealer(pageParam);
	}

	/**
	 * 对手方交易员联想文本框初始化。
	 *
	 * @return 联想文本框
	 */
	@ResponseBody
	@RequestMapping("/selectCounterTrader")
	public List<SuggestDto> selectCounterTrader() {
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		return jjEtrExchangeTrade001Service.selectCounterTrader(pageParam);
	}

	/**
	 * 本方交易员联想文本框初始化。
	 *
	 * @return 联想文本框
	 */
	@ResponseBody
	@RequestMapping("/selectTrader")
	public List<SuggestDto> selectTrader() {
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		return jjEtrExchangeTrade001Service.selectTrader(pageParam);
	}

	/**
	 * 查询资产对应组合的可用持仓信息
	 *
	 * @param param
	 * @return 可用持仓
	 * @author dubaojun
	 */
	@ResponseBody
	@RequestMapping("/selectAvailableNum")
	public String selectAvailableNum(@RequestBody Map<String, Object> param){
		BigDecimal availableNum = jjEtrExchangeTrade001Service.selectAvailableNum(param);
		if(availableNum == null) {
			return "0.00";
		}
		return availableNum.setScale(2,RoundingMode.HALF_UP).toString();
	}

	/**
	 * 查询组合对应账户的可用现金信息
	 *
	 * @param param
	 * @return 可用现金
	 * @author dubaojun
	 */
	@ResponseBody
	@RequestMapping("/selectAccAvailableCash")
	public String selectAccAvailableCash(@RequestBody Map<String, Object> param){
		BigDecimal accAvailableCash = jjEtrExchangeTrade001Service.selectAccAvailableCash(param);
		if(accAvailableCash == null) {
			return "0.00";
		}
		return accAvailableCash.setScale(2).toString();
	}

	/**
	 * 查询债券对应的最新面值
	 *
	 * @param param
	 * @return 最新面值
	 * @author dubaojun
	 */
	@ResponseBody
	@RequestMapping("/selectSecFaceValue")
	public BigDecimal selectSecFaceValue(@RequestBody Map<String, Object> param){
		BigDecimal faceValue = jjEtrExchangeTrade001Service.selectSecFaceValue(param);
		if(faceValue == null) {
			return BigDecimal.ZERO;
		}
		return faceValue;
	}

	/**
	 * 根据画面的信息计算价格和到期收益率。<br/>
	 * 如果以净价为基准，则计算全价和到期收益率<br/>
	 * 如果以全价为基准，则计算净价和到期收益率<br/>
	 * 如果以到期收益率为基准，则计算净价和全价
	 *
	 * @param param
	 * @return 计算结果
	 * @author dubaojun
	 */
	@ResponseBody
	@RequestMapping("/calcPriceAndYield")
	public Map<String, BigDecimal> calcPriceAndYield(@RequestBody Map<String, Object> param){
		return jjEtrExchangeTrade001Service.calcPriceAndYield(param);
	}

	/**
	 * 根据画面的信息计算委托金额和委托数量。<br/>
	 * 如果目标类型为“数量”，则根据数量计算金额<br/>
	 * 如果目标类型为“金额”，则根据数量计算数量<br/>
	 *
	 * @param param
	 * @return 计算结果
	 * @author dubaojun
	 */
	@ResponseBody
	@RequestMapping("/calcEntrustNumAndAmt")
	public Map<String, BigDecimal> calcEntrustNumAndAmt(@RequestBody Map<String, Object> param){
		return jjEtrExchangeTrade001Service.calcEntrustNumAndAmt(param);
	}

	/**
	 * 获取最小交易单元
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMinNumStep")
	public Map<String, BigDecimal> getMinNumStep(@RequestBody Map<String, Object> param){
		return jjEtrExchangeTrade001Service.getMinNumStep(param);
	}

	/**
	 * 获取1/最小价差的倍数
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInterfSysMinPriceAll")
	public List<MinPirceDto> getInterfSysMinPriceAll(@RequestBody Map<String, Object> param) {
		List<MinPirceDto> minPirceList = jjEtrExchangeTrade001Service.getInterfSysMinPriceAll(param);
		return minPirceList;
	}

	/***
	 * 取更新时间最新记录.投资目的  指令业务类型 = 数据字典.委托类型.大宗债券指令
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInvAim")
	public String getInvAim() {
		return jjEtrExchangeTrade001Service.getInvAim();
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/selectBondExecPlan")
	public int selectBondExecPlan() throws Exception {
		String param = super.getRequest().getParameter("param");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param", param);
		return jjEtrExchangeTrade001Service.selectBondExecPlan(map);
	}

	/**
	 * 导入
	 *
	 * @param fileName
	 *            导入的文件名
	 * @return 处理结果
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("checkImportData")
	public AjaxResult checkImportData(@RequestBody Map<String,Object> paramMap) throws Exception {
		return jjEtrExchangeTrade001Service.checkImportData(paramMap);
	}

	/**
	 * 折算率如果取不到值，默认100%
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/selectStdsecConverseRatio")
	public BigDecimal selectStdsecConverseRatio(@RequestBody Map<String, Object> param) throws Exception {
		String converseRatio = jjEtrExchangeTrade001Service.selectStdsecConverseRatio(param);
		if (StringUtils.isNotEmpty(converseRatio)) {
			// 画面上乘以100后展示
			return ArithmeticUtil.multiply(new BigDecimal(converseRatio), BigDecimal.valueOf(100));
		}
		return null;
	}

	@RequestMapping(value = "/selectExercisePriceById")
	@ResponseBody
	public BigDecimal selectExercisePriceById(@RequestBody Map<String, Object> param) throws Exception {
		// 查询行权计划
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("finprodId", param.get("finprodId"));
		map.put("psT03", DictCodeList.交易方向_行权_回售.getValue());
		map.put("cashType2", DictCodeListExt1.现金流二级类型_本金.getValue());
		map.put("execType01", DictCodeList.行权状态_未行权.getValue());
		map.put("execType02", DictCodeList.行权状态_已行权.getValue());
		List<FinExercisePlan> lst = busiFinExercisePlanService.getfinExercisePlanByProductId(map);
		if (null != lst && lst.size() > 0) {
			String vdate = param.get("vdate").toString();
			if (StringUtils.isNotEmpty(vdate)) {
				for (FinExercisePlan finExercisePlan : lst) {
					if (StringUtil.isNotEmpty(finExercisePlan.getVdate()) && StringUtil.isNotEmpty(finExercisePlan.getMdate())
							&& finExercisePlan.getVdate().getTime() -  DateUtil.parse(vdate).getTime() <= 0
							&& finExercisePlan.getMdate().getTime() - DateUtil.parse(vdate).getTime() >= 0) {
						return finExercisePlan.getExercisePrice();
					}
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/selectConverseSharePrice")
	@ResponseBody
	public BigDecimal selectConverseSharePrice(@RequestBody Map<String, Object> param) throws Exception {
		return jjEtrExchangeTrade001Service.selectConverseSharePrice(param);
	}

	@RequestMapping(value = "/selectTrdOthPrice")
	@ResponseBody
	public BigDecimal selectOthPrice(@RequestBody Map<String, Object> param) throws Exception {
		TrdOthCodeInfoDto dto = jjEtrExchangeTrade001Service.getTrdOthCodeInfo(param);
		if (null != dto && dto.getDeclaredPrice() != null && dto.getDeclaredPrice().compareTo(BigDecimal.ZERO) > 0) {
			return dto.getDeclaredPrice();
		}
		return null;
	}

	@RequestMapping(value = "/selectExtPs")
	@ResponseBody
	public Map<String, Object> selectExtPs(@RequestBody Map<String, Object> param) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Date sysDate = DateUtil.platDateFromCache();
		param.put("date", DateUtil.format(sysDate));
		List<TrdOthCodeInfoDto> list = jjEtrExchangeTrade001Service.getTrdOthCodeInfos(param);
		if (null != list && list.size() > 0) {
			for (TrdOthCodeInfoDto dto : list) {
				if (!StringUtils.equals(dto.getOthCodeType(), DictCodeListExt3.金融产品其他代码类型_配股代码.getValue())
						&& !StringUtils.equals(dto.getOthCodeType(), DictCodeListExt3.金融产品其他代码类型_配债代码.getValue())) {
					// 只能选择申购，其他委托方向不可选
					String ps = OthTypeMapToPs(dto.getOthCodeType());
					if (StringUtils.equals(ps, DictCodeList.交易方向_申购.getValue())) {
						map.clear();
						map.put(ps, dto.getDeclaredPrice());
						return map;
					} else if (StringUtils.equals(ps, DictCodeListExt3.交易方向_质押券入库.getValue())) {
						BigDecimal converseRatio = selectStdsecConverseRatio(param);
						if (null != converseRatio) {
							map.put(DictCodeListExt3.交易方向_质押券入库.getValue(), converseRatio);
							map.put(DictCodeListExt3.交易方向_质押券出库.getValue(), converseRatio);
						}
					} else {
						map.put(ps, dto.getDeclaredPrice());
					}
				}
			}
		}
		return map;
	}

	/**
	 * 申报类型-》交易方向的映射
	 * @param othCodeType
	 * @return
	 */
	private String OthTypeMapToPs(String othCodeType) {
		if (StringUtils.equals(othCodeType, DictCodeListExt3.金融产品其他代码类型_可转债信用申购代码.getValue())) {
			return DictCodeList.交易方向_申购.getValue();
		}else if(StringUtils.equals(othCodeType, DictCodeListExt3.金融产品其他代码类型_回售代码.getValue())){
			return DictCodeList.交易方向_行权_回售.getValue();
		}else if(StringUtils.equals(othCodeType, DictCodeListExt3.金融产品其他代码类型_可转债转股代码.getValue())){
			return DictCodeListExt1.交易方向_债转股.getValue();
		}else if(StringUtils.equals(othCodeType, DictCodeListExt3.金融产品其他代码类型_质押代码.getValue())){
			return DictCodeListExt3.交易方向_质押券入库.getValue();
		}
		return null;
	}

	//add by chenjiaxing 债券竞价交易计算偏离度 20210907 start
	/**
	 * 1.6计算偏离度
	 *
	 * @param params 画面参数
	 * @return 偏离度
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("calcDeviationDegree")
	@ResponseBody
	public String calcDeviationDegree(@RequestParam Map<String, Object> params) {
		return jjEtrExchangeTrade001Service.calcDeviationDegree(params);
	}
	//add by chenjiaxing 债券竞价交易计算偏离度 20210907 end

	/***
	 * 初始化行情
	 * @return
	 */
	@RequestMapping(value = "/initHq")
	public String initHq(Model model){

		return "invest/entrust/etrexchangetrade/EtrExchangeTradeHq";
	}

	/**
	 * 判断所选产是不是债券并且有没有通过中债sppi测试
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/IsBondAsset")
	public AjaxResult IsBondAsset(){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setData(jjEtrExchangeTrade001Service.IsBondAsset(RequestUtil.getPageParam()));
//        ajaxResult.setData("2");
		return ajaxResult;
	}
}
	
