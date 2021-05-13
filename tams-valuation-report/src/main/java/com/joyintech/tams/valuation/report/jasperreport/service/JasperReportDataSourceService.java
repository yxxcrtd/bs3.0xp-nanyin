package com.joyintech.tams.valuation.report.jasperreport.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.valuation.report.common.constant.DateUtil;
import com.joyintech.tams.valuation.report.common.service.TargetIndexService;
import com.joyintech.tams.valuation.report.common.util.Dao;
import com.joyintech.tams.valuation.report.common.util.ReportGlobalConstant;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author lxc@jixunet.com
 * @version $Id$
 */

public class JasperReportDataSourceService implements JRDataSource {
	private Map<String, Object> data = new HashMap<String, Object>();
	int index = -1;
	Map<String, Object> parameters=new HashMap<String, Object>();
	public JasperReportDataSourceService(String prodId, Date startDate, Date endDate,String reportType) throws Exception {
		this.parameters.put("prodId", prodId);
		this.parameters.put("startDate", startDate);
		this.parameters.put("endDate", endDate);
		this.parameters.put("reportType", reportType);
		fetchData();
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	private void fetchData() throws Exception {
		TargetIndexService t = new TargetIndexService();
		String reportType=String.valueOf(parameters.get("reportType"));
		String startDate=String.valueOf(parameters.get("startDate"));
		String endDate=String.valueOf(parameters.get("endDate"));
		String prodId=String.valueOf(parameters.get("prodId"));
		Connection  conn= null;
		try {
			conn =Dao.getConnection(reportType);
			if(ReportGlobalConstant.pAjZ_TWO.equals(reportType)){
				data = t.printTargetNetTypeTbTwo(DateUtil.parse(startDate), DateUtil.parse(endDate), prodId,conn);
			}else if(ReportGlobalConstant.pAzb_TWO.equals(reportType)){
				data = t.printTargetCurrencyTypeTbTwo(DateUtil.parse(startDate), DateUtil.parse(endDate), prodId,conn);
			}
		}finally {
			if(conn!=null){
				conn.close();
			}
		}
	}
	public boolean next() throws JRException {
		index++;
		if (data != null && index < data.size()) {
			return true;
		}
		return false;
	}

	public Object getFieldValue(JRField jrField) throws JRException {
		return data.get(jrField.getName());
	}
}
