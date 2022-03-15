/*
 * 文件名：EtrTradePlaceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：meifaxin
 * 修改时间：2021年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.exchange.entrust.etrexchange.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.background.component.common.pack.common.security.User;
import com.joyintech.background.component.order.exchange.business.dto.*;
import com.joyintech.background.component.order.exchange.common.constant.UnitEnum;
import com.joyintech.background.component.order.exchange.common.dto.EtrBaseDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;
import com.joyintech.background.component.order.exchange.common.service.EtrComponentCommService;
import com.joyintech.background.component.order.exchange.common.service.impl.EtrComponentFactory;
import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.SecPrinSchedule;
import com.joyintech.calculation.bonds.constant.*;
import com.joyintech.calculation.shell.AccurIntCalculator;
import com.joyintech.calculation.shell.AccurIntForSecheduleCalculator;
import com.joyintech.calculation.shell.PriceByYieldCalculator;
import com.joyintech.calculation.shell.YieldByPriceCalculator;
import com.joyintech.calculation.shell.param.*;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrcommanddispatcher.service.EtrCommandDispatcherService;
import com.joyintech.system.base.dao.SysWflBusinessListMapper;
import com.joyintech.tams.business.common.dto.CfetsOprHisInfoRequest;
import com.joyintech.tams.business.common.service.JoyinbusinessCommonService;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.component.business.bond.BusiFinExercisePlanService;
import com.joyintech.tams.component.business.common.dto.FinStockInfoDto;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dao.EtrExchangeTradeCommonMapper;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.FinProductOthCodeDto;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.SubEqutityDto;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.*;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeQuotationRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeQuotationResponse;
import com.joyintech.tams.exchange.entrust.etrexchange.position.service.PositionRequestServiceEtrExchangeTradeSaveRequest;
import com.joyintech.tams.invest.common.asset.dto.AssetGenerateEntry;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.asset.util.AssetCommonUtil;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.position.PositionCashSecData;
import com.joyintech.tams.invest.common.dto.position.PositionCashSecDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.entrust.inter.EtrTradeCommonervice;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.trade.dto.SecInfoModelDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.*;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.joyintech.tams.constant.DictCodeList.委托状态_已撤回;
import static com.joyintech.tams.constant.DictCodeList.数据状态_修改中;
import static com.joyintech.tams.constant.DictCodeListExt3.系统动作_修改;
import static com.joyintech.tams.constant.DictCodeListExt3.系统动作_新建;
import static com.joyintech.tams.invest.common.asset.service.BondAssetCalcService.*;

/**
 * 交易所指令交易（债券股票回购基金场内指令交易）
 *
 * @author meifaxin
 * @version 1.0 2021年12月20日
 * @see EtrExchangeTradeCommon001ValidatorService
 * @since 1.0
 */
@Service
public class EtrExchangeTradeCommonValidatorService implements EtrCommonValidator {

	@Autowired
	private EtrExchangeTradeCommon001Service etrExchangeTradeCommon001Service;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidMessage> bussValid(Object value) {
		List<ValidMessage> errorList = new ArrayList<>();
		EtrExchangeTradeSaveRequest dto = (EtrExchangeTradeSaveRequest) value;
		Boolean isNonTradingTime = etrExchangeTradeCommon001Service.isNonTradingTime(dto);
//		AjaxResult webResult = new AjaxResult();
		if (isNonTradingTime) {
			errorList.add(new ValidMessage(this.getMessage("ETR_ERROR_0056")));
//			webResult.setErrorList(errorList);
			return errorList;
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_非交易业务指令平台.getValue())) {
			// add  start
			if (org.apache.commons.lang.StringUtils.equals(dto.getPs(), DictCodeListExt2.交易方向_配股.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_配股指令.getValue());
			} else if (org.apache.commons.lang.StringUtils.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_新股申购指令.getValue());
			} else {//add  end
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_股票竞价指令.getValue());
			}
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_基金竞价指令.getValue());
		}
		if ((dto.getPs().equals(DictCodeListExt3.交易方向_正回购.getValue()) || dto.getPs().equals(DictCodeListExt3.交易方向_逆回购.getValue())) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗债券质押式回购指令.getValue());
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗股票指令.getValue());
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗基金指令.getValue());
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗债券指令.getValue());
		}
		// add start
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_非交易业务指令平台.getValue())) {
			if (org.apache.commons.lang.StringUtils.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_债券回售指令.getValue());
			} else if (org.apache.commons.lang.StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_配债.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_配债指令.getValue());
			} else if (org.apache.commons.lang.StringUtils.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_可转债申购指令.getValue());
			} else if (org.apache.commons.lang.StringUtils.equals(dto.getPs(), DictCodeListExt1.交易方向_债转股.getValue())) {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_债转股指令.getValue());
			} else if (org.apache.commons.lang.StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_质押券入库.getValue()) || org.apache.commons.lang.StringUtils.equals(dto.getPs(), DictCodeListExt3.交易方向_质押券出库.getValue())) {
				/*
				 * else if 画面.委托方向 = 数据字典.交易方向.质押入库        or 质押出库
				 * 指令业务类型     =  数据字典.共通业务类型.标准券出入库指令
				 */
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_标准券出入库指令.getValue());
			}else {
				dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_债券竞价指令.getValue());
			}
		}
		// 债券质押式回购竞价指令
		if ((dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue()) || dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) && dto.getTradePlat().equals(DictCodeList.交易平台_竞价撮合平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_债券质押式回购竞价指令.getValue());
		}
		// 大宗债券质押式回购指令
		if ((dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue()) || dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) && dto.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_大宗债券质押式回购指令.getValue());
		}
		if (dto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && dto.getTradePlat().equals(DictCodeList.交易平台_固定收益平台.getValue())) {
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_上交所固定收益平台现券交易指令.getValue());
		}
		AjaxResult ajax  = etrExchangeTradeCommon001Service.checkSave(dto,null);
		if (ajax != null && !StringUtil.isEmptyList(ajax.getErrorList())) {
			errorList.addAll(ajax.getErrorList());
			return errorList;
		}

		String confirmMsg = "";
		if (org.apache.commons.lang.StringUtils.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
			Map<String, Object> pageParam = new HashMap<String, Object>();
			Date sysDate = DateUtil.platDateFromCache();
			pageParam.put("date", DateUtil.format(sysDate));
			pageParam.put("secId", dto.getStockId());
			String price = etrExchangeTradeCommon001Service.qryMstBondMarketPrice(pageParam);
			if (org.apache.commons.lang.StringUtils.isNotEmpty(price)) {
				BigDecimal bigPrice = new BigDecimal(price);
				if (dto.getEntrustPrice().compareTo(bigPrice.multiply(new BigDecimal(1.02))) > 0) {
					confirmMsg = "当前价格超过上一日平均价的2%，是否继续？";
				}
			}
		}
		if (DictCodeList.交易方向_申购.getValue().equals(dto.getPs()) && DictCodeListExt1.金融产品类型_投管用_股票.getValue().equals(dto.getFinprodType2())) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("finprodId", dto.getFinprodId());//金融产品代码
			List<String> othTypeList = new ArrayList<String>();
			othTypeList.add(DictCodeListExt3.金融产品其他代码类型_新股申购代码.getValue());
			paramMap.put("othTypeList", othTypeList);
			Map<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("finprodId", dto.getFinprodId());//金融产品代码
			paramMap1.put("secAcctId",dto.getSecAcctId());
			paramMap1.put("cDate",DateUtil.getSysDate());
			paramMap1.put("portfolioId",dto.getPortfolioId());
			String othType = DictCodeListExt3.金融产品其他代码类型_新股申购代码.getValue();              //业务类型
			List<FinProductOthCodeDto> codeDtos = etrExchangeTradeCommon001Service.getFinprodOthCodeInfo(paramMap);
			if (codeDtos.size() > 0) {
				othType = codeDtos.get(0).getOthType();
				paramMap1.put("othType", othType);
			}
			List<FinProductQuto> qutos = etrExchangeTradeCommon001Service.getFinProductQutoInfo(paramMap1);
			if (qutos.size() == 0) {
				confirmMsg = "当前资产无申购额度，是否继续？";
			}
		}

		//配股配债权益数量软check
		if (DictCodeListExt2.交易方向_配股.getValue().equals(dto.getPs())||DictCodeListExt3.交易方向_配债.getValue().equals(dto.getPs())) {
			Map<String, Object> param = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("finprodId", dto.getFinprodId());//金融产品代码
			List<String> othTypeList = new ArrayList<String>();
			String othType = "";
			//业务类型
			if(DictCodeListExt2.交易方向_配股.getValue().equals(dto.getPs())){
				othTypeList.add(DictCodeListExt3.金融产品其他代码类型_配股代码.getValue());
				othType=DictCodeListExt3.金融产品其他代码类型_配股代码.getValue();

			}
			if(DictCodeListExt3.交易方向_配债.getValue().equals(dto.getPs())){
				othTypeList.add(DictCodeListExt3.金融产品其他代码类型_配债代码.getValue());
			}
			paramMap.put("othTypeList", othTypeList);
			Map<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("finprodId", dto.getFinprodId());//金融产品代码
			paramMap1.put("secAcctId",dto.getSecAcctId());
			paramMap1.put("cDate",DateUtil.getSysDate());
			paramMap1.put("portfolioId",dto.getPortfolioId());
			List<FinProductOthCodeDto> codeDtos = etrExchangeTradeCommon001Service.getFinprodOthCodeInfo(paramMap);
			if (codeDtos.size() > 0) {
				othType = codeDtos.get(0).getOthType();
				paramMap1.put("othType", othType);
			}
			List<FinProductQuto> qutos = etrExchangeTradeCommon001Service.getFinProductQutoInfo(paramMap1);
			if (qutos.size() == 0) {
				confirmMsg = "当前资产无配股配债额度，是否继续？";
			}

		}

//		// etr_check_cash_switch 01 提示 02 禁止
//		if (CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH).equals(DictCodeListExt1.是或否_是.getValue())
//				&& (DictCodeList.交易方向_买入.getValue().equals(dto.getPs()) || DictCodeListExt3.交易方向_配债.getValue().equals(dto.getPs())
//				|| DictCodeListExt2.交易方向_配股.getValue().equals(dto.getPs()) || DictCodeList.交易方向_申购.getValue().equals(dto.getPs())
//				|| DictCodeListExt3.交易方向_逆回购.getValue().equals(dto.getPs()))) {
//			Map<String,Object> param = new HashMap<String,Object>();
//			param.put("portfolioId",dto.getPortfolioId());
//			param.put("ccy",DictCodeList.币种_人民币_CNY.getValue());
//			dto.setAccAvailableCash(selectAccAvailableCash(param));
//			if (StringUtil.isNotEmpty(dto.getAccAvailableCash()) && StringUtil.isNotEmpty(dto.getEntrustAmt())) {
//				if (dto.getAccAvailableCash().compareTo(dto.getEntrustAmt()) == -1) {
//					confirmMsg = "组合可用现金不足，是否继续？";
//				}
//			}
//		}
//
//		// etr_check_pos_switch 01 提示 02 禁止
//		if (CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH).equals(DictCodeListExt1.是或否_是.getValue()) && (
//				DictCodeList.交易方向_卖出.getValue().equals(dto.getPs())
//						|| DictCodeList.交易方向_行权_回售.getValue().equals(dto.getPs())
//						|| DictCodeListExt3.交易方向_债转股.getValue().equals(dto.getPs())
//						|| DictCodeListExt3.交易方向_质押券入库.getValue().equals(dto.getPs()))) {
//			Map<String,Object> param = new HashMap<String,Object>();
//			param.put("portfolioId",dto.getPortfolioId());
//			param.put("finprodId",dto.getFinprodId());
//			param.put("ccy",DictCodeList.币种_人民币_CNY.getValue());
//			param.put("secAcctId",dto.getSecAcctId());
//			dto.setAccAvailablePos(jjEtrExchangeTrade001Service.selectAvailableNum(param));
//			if (StringUtil.isNotEmpty(dto.getAccAvailablePos()) && StringUtil.isNotEmpty(dto.getEntrustNum())) {
//				if (dto.getAccAvailablePos().compareTo(dto.getEntrustNum()) == -1) {
//					confirmMsg = "可用持仓不足，是否继续？";
//				}
//			}
//		}
//
//		//正回购、质押券出库
//		// 委托指令组合的可用持仓不足时  01 提醒 02 禁止
//		if (CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH).equals(DictCodeListExt1.是或否_是.getValue()) &&
//				(DictCodeListExt3.交易方向_正回购.getValue().equals(dto.getPs()) || DictCodeListExt3.交易方向_质押券出库.getValue().equals(dto.getPs()))) {
//			Map<String,Object> param = new HashMap<String,Object>();
//			param.put("portfolioId",dto.getPortfolioId());
//			param.put("finprodId","XN_PRODID999");
//			if(null != dto.getSecAcctId()){
//				param.put("secAcctId", dto.getSecAcctId());
//			}
//			param.put("ccy",DictCodeList.币种_人民币_CNY.getValue());
//			dto.setAccAvailablePos(selectAvailableNum(param));
//			if (StringUtil.isNotEmpty(dto.getAccAvailablePos()) && StringUtil.isNotEmpty(dto.getEntrustNum())) {
//				if (dto.getAccAvailablePos().compareTo(dto.getEntrustNum()) == -1) {
//					confirmMsg = "标准券可用持仓不足，是否继续？";
//				}
//			}
//		}

//			if (StringUtil.isNotEmpty(confirmMsg)) {
//				AjaxResult ajaxResult = new AjaxResult();
//				ajaxResult.setData(confirmMsg);
//				return ajaxResult;
//			}
//			return null;
		errorList.add(new ValidMessage(confirmMsg));
		return errorList;
	}
}
