/*
 * 文件名：EtrTradePlaceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrexchangetrade.service;

import com.joyintech.framework.export.CommonExportService;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.MutiptlPortfolioDetail;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.entrust.common.dto.EtrFinProductCommonDto;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.*;

import com.joyintech.workflow.engine.constant.OperationType;

import com.joyintech.workflow.engine.service.BaseProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.util.LoggingUtils;

import java.math.BigDecimal;
import java.util.*;


/**
 * 交易所多组合指令交易
 *
 * @author chendijun
 * @version 1.0 2020年7月1日
 * @see EtrExchangeTradeMulti001Service
 * @since 1.0
 */
@Service
public class EtrExchangeTradeMulti001Service extends CommonExportService<EtrExchangeTradeMulti001Dto>
		implements BaseProcessService<EtrExchangeTradeMulti001Dto> {

	@Autowired
	private EtrCommonService etrCommonService;

	/**
	 * 日期相关
	 */
	@Autowired
	private DateCalService dateCalService;

	@Override
	public void doComplete(EtrExchangeTradeMulti001Dto etrExchangeTradeMulti001Dto, OperationType operationType) {

	}

	/**
	 * 获取多组合数
	 * @param map
	 * @return
	 */
	public Page<SelectOptionDto> selectPortfolioTreeId(Map<String,Object> map) {
		return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/selectPortfolioTreeId", map,
				SelectOptionDto.class);
	}

	/**
	 * 查询多组合明细列表
	 * @param map
	 * @return
	 */
	public List<EtrExchangeTradeMultiPtl001Dto> selectPortfolioTreeDetail(@RequestBody Map<String,Object> map) {
		String portfolioTreeId = (String)map.get("portfolioTreeId");
		List<EtrExchangeTradeMultiPtl001Dto> list = new ArrayList<>();
		if(StringUtil.isNotEmpty(portfolioTreeId)) {
			Date platformDate = DateUtil.platDateFromCache();
			map.put("ptlDate",DateUtil.format(platformDate));
			map.put("userId", LoginUserUtil.getUserName());

			String tradeMarket = (String)map.get("tradeMarket");

			String calId = StringUtil.equals(tradeMarket,DictCodeListExt1.交易场所_上海证券交易所.getValue())?DictCodeList.交易日历_上海交易所日历.getValue():DictCodeList.交易日历_深圳交易所日历.getValue();
			list = restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/selectPortfolioTreeDetail", map,
					EtrExchangeTradeMultiPtl001Dto.class);
			if(!StringUtil.isEmptyList(list)) {
				list.stream().forEach(item->{
					Map<String, Object> paramMap = new HashMap<String,Object>();
					paramMap.put("portfolioId",item.getPortfolioId());
					paramMap.put("vdate",DateUtil.format(platformDate));
					paramMap.put("acctNo",item.getAcctNo());
					paramMap.put("calId",calId);
					paramMap.put("ccy",StringUtil.isNotEmpty(item.getCcy())? item.getCcy():DictCodeList.币种_人民币_CNY.getValue());

					//可用现金
					Map<String, Object> cash = etrCommonService.getProAvailableCash(paramMap);
					if(StringUtil.isNotEmpty(cash)) {
						//组合T日可用现金
						item.setAvailableCashT(StringUtil.isNotEmpty(cash.get("cashT"))?(BigDecimal)cash.get("cashT"): BigDecimal.ZERO);
						//组合T+1日可用现金
						item.setAvailableCashT1(StringUtil.isNotEmpty(cash.get("cashT1"))?(BigDecimal)cash.get("cashT1"): BigDecimal.ZERO);
					}
					if(StringUtil.isNotEmpty(map.get("finprodId"))) {
						EtrSecPositionDto etrSecPositionDto = new EtrSecPositionDto();
						etrSecPositionDto.setFinprodId((String)paramMap.get("finprodId"));
						etrSecPositionDto.setPortfolioId((String)paramMap.get("portfolioId"));
						etrSecPositionDto.setCcy(item.getCcy());

						//组合T日可用持仓
						etrSecPositionDto.setCdate(platformDate);
						item.setAvailablePositionsT(etrCommonService.getAssetAvailablePosition(etrSecPositionDto));

						if(StringUtil.isNotEmpty(calId)){
							List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(platformDate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(platformDate)), 15),calId);
							Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(platformDate)), 1, holidayList, "1");
							etrSecPositionDto.setCdate(tradeDay);
						}
						//组合T+1日可用持仓
						item.setAvailablePositionsT1(etrCommonService.getAssetAvailablePosition(etrSecPositionDto));
					}

				});
			}
		}
		return list != null?list:new ArrayList<EtrExchangeTradeMultiPtl001Dto>();
	}
	/**
	 * 修改/双击查看详情查询信息 <功能详细描述>
	 *
	 * @param entrustId
	 * @return
	 * @see
	 * @since 1.0
	 */
	public EtrExchangeTradeMulti001Dto getEtrExchangeTradeMultiInfo(String entrustId) {
		return restTemplate.postForObject(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/getEtrExchangeTradeMultiInfo",entrustId,
				EtrExchangeTradeMulti001Dto.class);
	}

	/**
	 * 多组合详情
	 *
	 * @param pageParam 页面参数
	 * @return 多组合详情
	 * @see
	 * @since 1.0
	 */
	public EtrExchangeTradeMulti001Dto selectMultDetail(Map<String, Object> pageParam) {
		EtrExchangeTradeMulti001Dto dto = restTemplate.postForObject(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/selectMultDetail",
				pageParam, EtrExchangeTradeMulti001Dto.class);
		if(!StringUtil.isEmptyList(dto.getTradeMultiPtl001Dtos())) {
			Date platformDate = DateUtil.platDateFromCache();
			String calId = StringUtil.equals(dto.getTradeMarket(),DictCodeListExt1.交易场所_上海证券交易所.getValue())?DictCodeList.交易日历_上海交易所日历.getValue():DictCodeList.交易日历_深圳交易所日历.getValue();
			dto.getTradeMultiPtl001Dtos().stream().forEach(item->{
				Map<String, Object> paramMap = new HashMap<String,Object>();
				paramMap.put("portfolioId",item.getPortfolioId());
				paramMap.put("vdate",DateUtil.format(platformDate));
				paramMap.put("acctNo",item.getAcctNo());
				paramMap.put("calId",calId);
				paramMap.put("ccy",StringUtil.isNotEmpty(item.getCcy())? item.getCcy():DictCodeList.币种_人民币_CNY.getValue());

				//可用现金
				Map<String, Object> cash = etrCommonService.getProAvailableCash(paramMap);
				if(StringUtil.isNotEmpty(cash)) {
					//组合T日可用现金
					item.setAvailableCashT(StringUtil.isNotEmpty(cash.get("cashT"))?(BigDecimal)cash.get("cashT"): BigDecimal.ZERO);
					//组合T+1日可用现金
					item.setAvailableCashT1(StringUtil.isNotEmpty(cash.get("cashT1"))?(BigDecimal)cash.get("cashT1"): BigDecimal.ZERO);
				}
				if(StringUtil.isNotEmpty(dto.getFinprodId())) {
					EtrSecPositionDto etrSecPositionDto = new EtrSecPositionDto();
					etrSecPositionDto.setFinprodId((String)paramMap.get("finprodId"));
					etrSecPositionDto.setPortfolioId(item.getPortfolioId());
					etrSecPositionDto.setCcy(item.getCcy());

					//组合T日可用持仓
					etrSecPositionDto.setCdate(platformDate);
					item.setAvailablePositionsT(etrCommonService.getAssetAvailablePosition(etrSecPositionDto));

					if(StringUtil.isNotEmpty(calId)){
						List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(platformDate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(platformDate)), 15),calId);
						Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(platformDate)), 1, holidayList, "1");
						etrSecPositionDto.setCdate(tradeDay);
					}
					//组合T+1日可用持仓
					item.setAvailablePositionsT1(etrCommonService.getAssetAvailablePosition(etrSecPositionDto));
				}

			});
		}
		return dto;
	}
}
