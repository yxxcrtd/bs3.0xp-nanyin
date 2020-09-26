package com.joyintech.tams.valuation.report.jasperreport.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.report.common.calculation.DataSource.MProd.TbThree.TbThreeCurrencyDataRepository;
import com.joyintech.tams.valuation.report.common.calculation.DataSource.NProd.TbThree.TbThreeNetTypeDataRepository;
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

public class JasperReportDataSourceListService implements JRDataSource {
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	int index = -1;
	Map<String, Object> parameters=new HashMap<String, Object>();
	public JasperReportDataSourceListService(String prodId, Date startDate, Date endDate,String reportType) throws Exception {
		this.parameters.put("prodId", prodId);
		this.parameters.put("startDate", startDate);
		this.parameters.put("endDate", endDate);
		this.parameters.put("reportType", reportType);
		fetchData();
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	private void fetchData() throws Exception {
		String reportType=String.valueOf(parameters.get("reportType"));
		String startDate=String.valueOf(parameters.get("startDate"));
		String endDate=String.valueOf(parameters.get("endDate"));
		String prodId=String.valueOf(parameters.get("prodId"));
		Connection  conn=Dao.getConnection(reportType);
		if(ReportGlobalConstant.pAjZ_THREE.equals(reportType)){
			data = TbThreeNetTypeDataRepository.getNetTypeTbThreeData(DateUtil.parse(startDate), DateUtil.parse(endDate), prodId, conn);
		}else if(ReportGlobalConstant.pAzb_THREE.equals(reportType)){
			data = TbThreeCurrencyDataRepository.getCurrencyTypeTbThreeDate(DateUtil.parse(startDate), DateUtil.parse(endDate), prodId, conn);
		}
		if(conn!=null){
			conn.close();
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
		return data.get(index).get(jrField.getName());
	}
}
