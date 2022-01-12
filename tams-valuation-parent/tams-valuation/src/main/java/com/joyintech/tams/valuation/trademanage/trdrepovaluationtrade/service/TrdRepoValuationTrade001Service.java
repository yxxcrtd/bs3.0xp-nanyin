/*
 * 文件名：TrdRepoTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.trademanage.trdrepovaluationtrade.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.dto.TrdLendValuationTradeDto;
import com.joyintech.tams.valuation.trademanage.trdrepovaluationtrade.dto.TrdRepoValuationTrade001Dto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class TrdRepoValuationTrade001Service extends CommonExportService<TrdRepoValuationTrade001Dto> {



	/**
	 * 查询
	 * @param pageParam Map
	 * @return Page类
	 * @throws Exception 异常信息
	 */
	public Page<TrdRepoValuationTrade001Dto> findPage(Map<String, Object> pageParam){
		return restTemplate.postForPage(
				ServiceApplication.VALUATION_SERVICE_NAME + "/TrdRepoValuationTrade001/findPage",
				pageParam, TrdRepoValuationTrade001Dto.class);
	}

	/**
	 * 产品代码下拉框
	 * @auther: Administrator
	 * @date: 2021/12/29 17:10
	 * @param:
	 * @return:
	 */
	public Page<SelectOptionDto> findPortfolioId(Map<String, Object> params) {
		return restTemplate.postForPage(
				ServiceApplication.VALUATION_SERVICE_NAME + "/TrdRepoValuationTrade001/findPortfolioId",
				params, SelectOptionDto.class);
	}

	/**
	 * 不分页  导出
	 *
	 * @param params
	 * @return
	 */
	public List<TrdRepoValuationTrade001Dto> queryPageList(Map<String, Object> params) {
		return restTemplate.postForList(
				ServiceApplication.VALUATION_SERVICE_NAME + "/TrdRepoValuationTrade001/queryPageList", params,
				TrdRepoValuationTrade001Dto.class);
	}

}
