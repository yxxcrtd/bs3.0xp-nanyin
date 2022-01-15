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
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.service.SvrCommonExportService;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailProdMapper;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailProd;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * 银行月度报表登记service
 *
 * @author yuluan
 * @version 1.0 2019年2月20日
 * @since 1.0
 */
@Service
public class ChbMonthReportDetailProd001Service extends CommonExportService<ChbMonthReportDetailProd> {
	@Autowired
    private ChbMonthReportDetailProdMapper chbMonthReportDetailProdMapper;

    
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
	public Page<ChbMonthReportDetailProd> searchPage(Map<String, Object> pageParam) {
		//将发送状态改为空
		int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
			pageParam.put("prodIds",pageParam.get("prodId").toString().split(","));
		}
		Page<ChbMonthReportDetailProd> page = queryForPage(
				pagenum,legth,()->chbMonthReportDetailProdMapper.selectDetailList(pageParam));
		return page;
	}

	public List<ChbMonthReportDetailProd> selectForExport(Map<String, Object> pageParam) {
		//将发送状态改为空
		if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
			pageParam.put("prodIds",pageParam.get("prodId").toString().split(","));
		}

		if(pageParam.containsKey("keyList")){
			pageParam.put("keysList",
					StringUtil.splitToList(",",(String) pageParam.get("keyList")));
		}
		List<ChbMonthReportDetailProd> list = chbMonthReportDetailProdMapper.selectDetailList(pageParam);
		return list;
	}



	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config(
				"/com/joyintech/tams/svreport/chb/monthreportdetail/controller/conf/prodExport_conf.xml").dataSource(
				DataSourceType.MASERT);
	}

	@Override
	public Page<ChbMonthReportDetailProd> selectPageData(Map<String, Object> selectParam) {
		// TODO Auto-generated method stub
		return searchPage(selectParam);
	}


 	
}
