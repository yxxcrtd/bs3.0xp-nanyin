package com.jixu.report.service;

/** 
 * @ClassName 		JasperHelper
 * @author  		Pan Wei 
 * @Date 			2016年12月18日 下午10:32:36 	
 * @Description		[]  
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joyintech.framework.file.SafelyFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


public class JasperHelper {
	private static final Logger logger = LoggerFactory.getLogger(JasperHelper.class);
	
	public static void prepareReport(JasperReport jasperReport, String type) {
		logger.debug("The method======= prepareReport() start.......................");
		/*
		 * 如果导出的是excel，则需要去掉周围的margin
		 */
		if ("excel".equals(type))
			try {
				Field margin = JRBaseReport.class
						.getDeclaredField("leftMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("topMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("bottomMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				Field pageHeight = JRBaseReport.class
						.getDeclaredField("pageHeight");
				pageHeight.setAccessible(true);
				pageHeight.setInt(jasperReport, 2147483647);
			} catch (Exception exception) {
				logger.error("Error", exception);
			}
	}

	/**
	 * 导出excel
	 */
	public static void exportExcel(JasperPrint jasperPrint, String exportReportFile) throws IOException, JRException {
		String url=exportReportFile+".xlsx";
		File file=new SafelyFile(url);
		file.createNewFile();

		JRXlsxExporter xlsxExporter=new JRXlsxExporter();
        xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
        xlsxExporter.exportReport();
	}

	private static void exportPdf(JasperPrint jasperPrint, String exportReportFile) throws IOException, JRException {
        JasperExportManager.exportReportToPdfFile(jasperPrint, exportReportFile+".pdf");
	}

	/**
	 * 导出html
	 */
	private static void exportHtml(JasperPrint jasperPrint, String exportReportFile) throws IOException, JRException {
        JasperExportManager.exportReportToHtmlFile(jasperPrint, exportReportFile+".html");
	}

	/**
	 * 导出word
	 */
	private static void exportWord(JasperPrint jasperPrint, String exportReportFile) throws JRException, IOException {
		String url=exportReportFile+".doc";
		File file=new SafelyFile(url);
		file.createNewFile();

        JRDocxExporter worldExporter = new JRDocxExporter();
        worldExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        worldExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
        worldExporter.exportReport();
	}

	/**
	 * 
	 * @param type	文件类型
	 * @param sourceReportFile	源文件
	 * @param exportReportFile	导出文件
	 * @param request	request
	 * @param response	response
	 * @param parameters	参数
	 * @param conn	数据库连接
	 */
//	public static void export(int type, String sourceReportFile, String exportReportFile,
//			HttpServletRequest request, HttpServletResponse response,
//			Map<String, Object> parameters, Connection conn) {
//		try {
//			File reportFile =new SafelyFile(sourceReportFile);
//			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
//			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
//			exportHtml(jasperPrint, exportReportFile);
//			exportPdf(jasperPrint, exportReportFile);
//			exportExcel(jasperPrint, exportReportFile);
//			exportWord(jasperPrint, exportReportFile);
//		} catch (Exception e) {
//			logger.error("Error", e);
//		}
//	}
	
	/**
	 * 
	 * @param type	文件类型
	 * @param sourceReportFile	源文件
	 * @param exportReportFile	导出文件
	 * @param request	request
	 * @param response	response
	 * @param parameters	参数
	 * @param conn	数据库连接
	 */
	public static void exportStream(int type, InputStream inputStream, String exportReportFile,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> parameters, Connection conn) {
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
			exportHtml(jasperPrint, exportReportFile);
			exportPdf(jasperPrint, exportReportFile);
			exportExcel(jasperPrint, exportReportFile);
			exportWord(jasperPrint, exportReportFile);
			inputStream.close();
		} catch (Exception e) {
			logger.error("Error", e);
		}
	}
}
