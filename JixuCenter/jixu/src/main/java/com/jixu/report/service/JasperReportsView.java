package com.jixu.report.service;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

/**
 * @ClassName DigDataJasperReportsView
 * @author Pan Wei
 * @Date 2016年12月15日 下午10:51:51
 * @Description []
 */

public class JasperReportsView extends JasperReportsMultiFormatView {

	private JasperReport jasperReport;

	public JasperReportsView() {
		super();
	}

	protected JasperPrint fillReport(Map<String, Object> model) throws Exception {
		if (model.containsKey("url")) {
			setUrl(String.valueOf(model.get("url")));
			this.jasperReport = loadReport();
		}

		return super.fillReport(model);
	}

	protected JasperReport getReport() {
		return this.jasperReport;
	}

}
