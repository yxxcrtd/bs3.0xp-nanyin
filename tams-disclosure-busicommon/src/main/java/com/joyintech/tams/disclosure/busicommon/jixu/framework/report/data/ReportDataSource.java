package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.data;

import java.util.Map;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public abstract class ReportDataSource implements JRDataSource{
	private DataSource dataSource = null;
	private Map<?, ?> parameters = null;
	
	@Override
	public abstract Object getFieldValue(JRField arg0) throws JRException;

	@Override
	public abstract boolean next() throws JRException;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Map<?, ?> getParameters() {
		return parameters;
	}

	public void setParameters(Map<?, ?> parameters) {
		this.parameters = parameters;
	} 

	
}
