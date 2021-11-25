
package com.joyintech.tams.svreport.chb.monthreport.service;


import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubCheck;
import com.joyintech.tams.svreport.chb.monthreport.dao.SellMonthReportDetailMapper;
import com.joyintech.tams.svreport.chb.monthreport.dao.SellMonthReportMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReportDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SellMonthReportDelail001Service extends CommonExportService<SellMonthReportDetail> {
	@Autowired
    private SellMonthReportMapper sellMonthReportMapper;
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 导出查询
	 * @param param
	 * @return
	 */
	public List<SellMonthReportDetail> setDataList(Map<String, Object> param) {
		List<SellMonthReport> resultList = new ArrayList<>();
		String keyList = (String) param.get("keyList");
		if (com.joyintech.framework.util.StringUtil.isNotEmpty(keyList)) {
			try {
				resultList = com.joyintech.framework.util.JsonUtils.json2List(keyList, SellMonthReport.class);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		} else {
			param.put("submitDeadline", com.joyintech.framework.util.StringUtil.isNotEmpty(param.get("vdate"))?(param.get("vdate").toString().split(",")):null);
			param.put("sendStatus", com.joyintech.framework.util.StringUtil.isNotEmpty(param.get("mdate"))?(param.get("mdate").toString().split(",")):null);
			param.put("dataStatus", com.joyintech.framework.util.StringUtil.isNotEmpty(param.get("ptlId"))?(param.get("ptlId").toString().split(",")):null);
			param.put("statusDate", com.joyintech.framework.util.StringUtil.isNotEmpty(param.get("assetCode"))?(param.get("assetCode").toString().split(",")):null);
			param.put("submitTime", com.joyintech.framework.util.StringUtil.isNotEmpty(param.get("assetType2"))?(param.get("assetType2").toString().split(",")):null);
			param.put("submitFeedbackStatus", com.joyintech.framework.util.StringUtil.isNotEmpty(param.get("finprodType2"))?(param.get("finprodType2").toString().split(",")):null);
		}
		String reportuuid = resultList.get(0).getReportuuid();
		List<SellMonthReportDetail> resultDetailList = new ArrayList<>();
		resultDetailList = sellMonthReportMapper.setDataList(param);
		return resultDetailList;
	}
 	
}
