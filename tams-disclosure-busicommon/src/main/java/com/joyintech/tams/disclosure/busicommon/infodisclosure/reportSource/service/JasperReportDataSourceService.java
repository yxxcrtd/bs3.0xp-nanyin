package com.joyintech.tams.disclosure.busicommon.infodisclosure.reportSource.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joyintech.framework.util.SpringContextUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JasperReportDataSourceService implements JRDataSource {

	private Map<String, Object> data = new HashMap<String, Object>();
	int index = -1;
	Map<String, Object> parameters=new HashMap<String, Object>();
	
	public void createService() throws Exception {
		new JasperReportDataSourceService("",new Date(1),new Date(1),"");
	}
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
	
	private void fetchData()  {
		
		DisTargetIndexServiceGroup t = new DisTargetIndexServiceGroup();
		String reportType=String.valueOf(parameters.get("reportType"));
		String startDate=String.valueOf(parameters.get("startDate"));
		String endDate=String.valueOf(parameters.get("endDate"));
		String prodId=String.valueOf(parameters.get("prodId"));
		
		Connection  conn = null;
		try {
			JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean(JdbcTemplate.class);
			conn = jdbcTemplate.getDataSource().getConnection();
			data = t.printGS(DateUtils.parseDate(startDate,"yyyy-MM-dd"), DateUtils.parseDate(endDate,"yyyy-MM-dd"), prodId, conn);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		return data.get(jrField.getName());
	}

	@Override
	public boolean next() throws JRException {
		index++;
		if (data != null && index < data.size()) {
			return true;
		}
		return false;
	}
}
