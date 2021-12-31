/*
 * 文件名：InvestorsHoldInfoRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.chb.monthreportdetail.service;


import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.svreport.chb.constant.CheckConfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.service.SvrCommonExportService;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreport.service.XlsxToHtml;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailAssetMapper;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailProdMapper;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailAsset;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailProd;
import com.joyintech.tools.collectors.JoyinCollectors;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 银行月度报表登记service
 *
 * @author yuluan
 * @version 1.0 2019年2月20日
 * @since 1.0
 */
@Service
public class ChbMonthReportDetailAsset001Service extends CommonExportService<ChbMonthReportDetailAsset> {
	@Autowired
	private ChbMonthReportDetailAssetMapper chbMonthReportDetailAssetMapper;


	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<ChbMonthReportDetailAsset> searchPage(Map<String, Object> pageParam) {
		//将发送状态改为空
		String iscal= SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ASSETPOSITION_CALWAY);
		pageParam.put("costFlag",iscal);
		int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
		int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
		if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
			pageParam.put("prodIds",pageParam.get("prodId").toString().split(","));
		}
		if(StringUtil.isNotEmpty(pageParam.get("assetCode"))){
			pageParam.put("assetCodes",pageParam.get("assetCode").toString().split(","));
		}
		Page<ChbMonthReportDetailAsset> page = queryForPage(
				pagenum,legth,()->chbMonthReportDetailAssetMapper.selectDetailList(pageParam));
		return page;
	}

	public List<ChbMonthReportDetailAsset> selectForExport(Map<String, Object> pageParam) {
		//将发送状态改为空
		if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
			pageParam.put("prodIds",pageParam.get("prodId").toString().split(","));
		}
		if(StringUtil.isNotEmpty(pageParam.get("assetCode"))){
			pageParam.put("assetCodes",pageParam.get("assetCode").toString().split(","));
		}
		if(pageParam.containsKey("keyList")){
			pageParam.put("keysList",
					StringUtil.splitToList(",",(String) pageParam.get("keyList")));
		}
		List<ChbMonthReportDetailAsset> list = chbMonthReportDetailAssetMapper.selectDetailList(pageParam);
		return list;
	}

	@PostMapping("getAssetCode")
	public List<SelectOptionDto> getAssetCode( Map<String,Object> paramMap) {
		return chbMonthReportDetailAssetMapper.getAssetCode(paramMap);
	}


 	
}
