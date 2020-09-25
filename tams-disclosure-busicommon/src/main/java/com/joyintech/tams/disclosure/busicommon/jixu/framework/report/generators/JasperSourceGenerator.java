package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.generators;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.data.ReportDataSource;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;

public class JasperSourceGenerator implements ReportGenerator{
	public JasperSourceGenerator(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	private DataSource dataSource = null;
	
	public JasperPrint fillReport(ReportInfo report, Map<String, Object> params) throws JRException{
		JasperPrint jp = null;
		String reportFile = report.getRepository() + report.getFolder() + File.separator + report.getUri()+ ".jasper";
		
		if ("EMPTY".equalsIgnoreCase(report.getDatasource())){
			jp = JasperFillManager.fillReport(reportFile, params, new JREmptyDataSource());
		}
		else if ("PROJECT".equalsIgnoreCase(report.getDatasource())){
			Connection connection = null;
			try {
				connection = this.dataSource.getConnection();
				jp = JasperFillManager.fillReport(reportFile, params, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}finally{
				if(null != connection){
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		else {
			try {
				@SuppressWarnings("unchecked")
				Class<ReportDataSource> dsClass = (Class<ReportDataSource>) Class.forName(report.getDatasource());
				ReportDataSource ds = dsClass.newInstance();
				ds.setDataSource(this.getDataSource());
				ds.setParameters(params);
				jp = JasperFillManager.fillReport(reportFile, params, ds);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return jp;
	}
	
	@Override
	public String genReport(ReportInfo report, Map<String, Object> params) throws JRException{
		return null;
	}
	
	@Override
	public void genReport(ReportInfo report, Map<String, Object> params, PrintWriter out) throws JRException{
		JasperPrint jasperPrint = fillReport(report, params);
		if (jasperPrint == null){
			throw new JRException("Fail to fill report");
		}
		else {
			HtmlExporter exporter = new HtmlExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			SimpleHtmlExporterOutput output = new SimpleHtmlExporterOutput(out);
			output.setImageHandler(new WebHtmlResourceHandler("/image?image={0}"));
			exporter.setExporterOutput(output);
			exporter.exportReport();
		}
	}
	
	@Override
	public void genReport(ReportInfo report, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
		JasperPrint jasperPrint = fillReport(report, params);
		if (jasperPrint == null){
			throw new JRException("Fail to fill report");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			HtmlExporter exporter = new HtmlExporter();
			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			SimpleHtmlExporterOutput output = new SimpleHtmlExporterOutput(out);
			output.setImageHandler(new WebHtmlResourceHandler("image?image={0}"));
			exporter.setExporterOutput(output);
			exporter.exportReport();
		}
	}
	
	@Override
	public void exportReport(ReportInfo report, Map<String, Object> params, String type, String dest) throws JRException{
		
		JasperPrint jp = fillReport(report, params);
		
		if (jp == null){
			throw new JRException("Fail to fill report");
		}
		else {
			if ("pdf".equalsIgnoreCase(type)){
				pdf(jp, dest);
			}
			else if ("xlsx".equalsIgnoreCase(type)){
				xlsx(jp, dest);
			}
			else if ("docx".equalsIgnoreCase(type)){
				docx(jp, dest);
			}
			else{
				System.err.println("Export type not supported!");
				throw new JRException("Type Not Support");
			}
		}
	}
	
	private void xlsx(JasperPrint jp, String dest) throws JRException
	{
		long start = System.currentTimeMillis();
		
		File destFile = new File(dest);
		JRXlsxExporter exporter = new JRXlsxExporter();
		
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setOnePagePerSheet(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();

		System.err.println("XLSX creation time : " + (System.currentTimeMillis() - start));
	}
	
	private void docx(JasperPrint jp, String dest) throws JRException
	{
		long start = System.currentTimeMillis();
		
		File destFile = new File(dest);
		JRDocxExporter exporter = new JRDocxExporter();
		
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
		exporter.exportReport();

		System.err.println("Docx creation time : " + (System.currentTimeMillis() - start));
	}
	
	private void pdf(JasperPrint jp, String dest) throws JRException
	{
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToPdfFile(jp,dest);;
		System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}

	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
}
