/*
 * 文件名：InvestorsHoldInfoRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.pbc.reportdetail.service;


import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.pbc.reportdetail.dao.PbcMonthReportDetailProdMapper;
import com.joyintech.tams.svreport.pbc.reportdetail.dto.PbcMonthReportDetailProd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PbcMonthReportDetailProd001Service extends CommonExportService<PbcMonthReportDetailProd> {
	@Autowired
    private PbcMonthReportDetailProdMapper pbcMonthReportDetailProdMapper;

    
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
	public Page<PbcMonthReportDetailProd> searchPage(Map<String, Object> pageParam) {
		//将发送状态改为空
		int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
			pageParam.put("prodIds",pageParam.get("prodId").toString().split(","));
		}

		if(StringUtil.isNotEmpty(pageParam.get("dataType"))){
			pageParam.put("dataTypes",pageParam.get("dataType").toString().split(","));
		}
		Page<PbcMonthReportDetailProd> page = queryForPage(
				pagenum,legth,()->pbcMonthReportDetailProdMapper.selectDetailList(pageParam));
		return page;
	}

	public List<PbcMonthReportDetailProd> selectForExport(Map<String, Object> pageParam) {
		//将发送状态改为空

		if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
			pageParam.put("prodIds",pageParam.get("prodId").toString().split(","));
		}

		if(StringUtil.isNotEmpty(pageParam.get("dataType"))){
			pageParam.put("dataTypes",pageParam.get("dataType").toString().split(","));
		}
		List<PbcMonthReportDetailProd> list = pbcMonthReportDetailProdMapper.selectDetailList(pageParam);
		return list;
	}



	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config(
				"/com/joyintech/tams/svreport/pbc/classic/monthreportdetail/controller/conf/prodExport_conf.xml").dataSource(
				DataSourceType.MASERT);
	}

	@Override
	public Page<PbcMonthReportDetailProd> selectPageData(Map<String, Object> selectParam) {
		return searchPage(selectParam);
	}


 	
}
