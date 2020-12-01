/*
 * 文件名：TrdStock001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.stockdeal.service;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.stockdeal.dto.TrdStockDto;
import com.joyintech.tams.invest.trade.stockdeal.dto.TrdStockDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;

import com.joyintech.framework.util.StringUtil;

import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 股票交易Service
 *
 * @author wangpeng
 * @version 1.0 2019年7月29日
 * @see TrdStock001Service
 * @since 1.0
 */

@Service
public class TrdStock001Service extends CommonExportService<TrdStockDto> {

	/**
	 * 股票交易信息
	 *
	 * @param pageParam
	 *            查询参数
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<TrdStockDto> findPage(Map<String, Object> pageParam) {
		// 多选下拉框值处理
		// 组合代码
		Object portfolioIdObj = pageParam.get("portFolioIdList");
		if (portfolioIdObj != null) {
			pageParam.put("portFolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String) portfolioIdObj));
		}
		Object psListObj = pageParam.get("psList");
		if (psListObj != null) {
			pageParam.put("psList", StringUtil.splitToList(GlobalConstant.COMMA, (String) psListObj));
		}
		Object tradpeMarketListObj = pageParam.get("tradeMarketList");
		if (tradpeMarketListObj != null) {
			pageParam.put("tradeMarketList", StringUtil.splitToList(GlobalConstant.COMMA, (String) tradpeMarketListObj));
		}
		Object dealModeListObj = pageParam.get("dealModeList");
		if (dealModeListObj != null) {
			pageParam.put("dealModeList", StringUtil.splitToList(GlobalConstant.COMMA, (String) dealModeListObj));
		}
		Object boardListObj = pageParam.get("boardList");
		if (boardListObj != null) {
			pageParam.put("boardList", StringUtil.splitToList(GlobalConstant.COMMA, (String) boardListObj));
		}
		return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStock/findPage",
				pageParam, TrdStockDto.class);
	}

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/trade/stockdeal/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<TrdStockDto> selectPageData(Map<String, Object> selectParam) {
		return findPage(selectParam);
	}

	/*public String start(ProcessParameter<TrdBondInvestTradeDto> processParameter, TrdBondInvestTradeDto inputDto) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/**
	 * 获取委托代码
	 * @param map 请求参数
	 * @return 联想文本框实体类
	 * @since 1.0
	 */
	public List<SuggestDto> getEntrustIdByType(Map<String,Object> map){
		return restTemplate.postForList(
				ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getEntrustId", map,
				SuggestDto.class);
	}
}
